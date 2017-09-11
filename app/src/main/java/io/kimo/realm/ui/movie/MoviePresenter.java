package io.kimo.realm.ui.movie;

import android.support.annotation.NonNull;

import io.kimo.realm.data.local.SharedPreferencesHelper;
import io.kimo.realm.data.model.Movie;
import io.kimo.realm.data.repository.realm.MovieRealmRepository;
import io.kimo.realm.ui.base.BaseContract;
import io.kimo.realm.ui.base.BasePresenter;
import io.realm.RealmResults;

public class MoviePresenter extends BasePresenter<MovieContract.ViewModel> implements MovieContract.Presenter {

    private static final String TAG = "MoviePresenter";
    private final MovieRealmRepository mMovieRealmRepository;

    private RealmResults<Movie> mMoviesResults;

    public MoviePresenter(
            @NonNull MovieContract.ViewModel viewModel,
            @NonNull SharedPreferencesHelper sharedPreferencesHelper,
            @NonNull MovieRealmRepository movieRealmRepository) {
        super(viewModel, sharedPreferencesHelper);
        mMovieRealmRepository = movieRealmRepository;
    }

    @Override
    public void createView() {
        super.createView();

        mViewModel.setState(BaseContract.ViewModel.STATE_LOADING);
        mMoviesResults = mMovieRealmRepository.all();
        mMoviesResults.addChangeListener(movies -> {
            Movie movie = movies.first(null);
            mViewModel.setMovie(movie);
        });
    }

    @Override
    public void destroyView() {
        super.destroyView();
        mMoviesResults.removeAllChangeListeners();
        mMovieRealmRepository.getRealmInstance().close();
    }
}
