package io.kimo.realm.data;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.kimo.realm.data.local.ParserHelper;
import io.kimo.realm.data.local.SharedPreferencesHelper;
import io.kimo.realm.data.model.Movie;
import io.kimo.realm.data.model.User;
import io.kimo.realm.data.repository.realm.MovieRealmRepository;
import io.kimo.realm.data.repository.realm.UserRealmRepository;
import io.realm.Realm;

public class DataManager {

    private ParserHelper mParserHelper;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    private boolean mSyncedFirstJson = false;
    private boolean mSyncedSecondJson = false;

    public DataManager(@NonNull SharedPreferencesHelper sharedPreferencesHelper,
                       @NonNull ParserHelper parserHelper) {
        mSharedPreferencesHelper = sharedPreferencesHelper;
        mParserHelper = parserHelper;
    }

    public void performInitialSync() {
        if (!mSharedPreferencesHelper.didInitialSync()) {
            if (!mSyncedFirstJson) loadDataFromFirstJson();
            if (!mSyncedSecondJson)loadDataFromSecondJson();
        }
    }

    private void loadDataFromSecondJson() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                //loading for demo
                Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                Movie movie = mParserHelper.parseLocalMovieJsonFile();
                Realm realm = Realm.getDefaultInstance();

                MovieRealmRepository movieRealmRepository = new MovieRealmRepository(realm, Movie.class);
                movieRealmRepository.add(movie);

                mSyncedSecondJson = true;
                updateInitialSyncStatus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadDataFromFirstJson() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                //loading for demo
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                List<User> users = mParserHelper.parseLocalUsersJsonFile();
                Realm realm = Realm.getDefaultInstance();
                UserRealmRepository userRealmRepository = new UserRealmRepository(realm, User.class);
                for (User user : users) {
                    userRealmRepository.add(user);
                }
                mSyncedFirstJson = true;
                updateInitialSyncStatus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateInitialSyncStatus() {
        mSharedPreferencesHelper.setDidInitialSync(mSyncedFirstJson && mSyncedSecondJson);
    }

    public SharedPreferencesHelper getSharedPreferencesHelper() {
        return mSharedPreferencesHelper;
    }
}
