package io.kimo.realm;

import android.app.Application;

import io.kimo.realm.data.DataManager;
import io.kimo.realm.data.local.ParserHelper;
import io.kimo.realm.data.local.SharedPreferencesHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private DataManager mDataManager;
    private Realm mRealm;

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();
        initDataManager();

        mDataManager.performInitialSync();
    }

    private void initDataManager() {
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(this);
        ParserHelper parserHelper = new ParserHelper(this);
        mDataManager = new DataManager(sharedPreferencesHelper, parserHelper);
    }

    private void initRealm() {
        Realm.init(this);
        Realm.setDefaultConfiguration(
                new RealmConfiguration.Builder()
                        .name("realm.db")
                        .build()
        );
        mRealm = Realm.getDefaultInstance();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
