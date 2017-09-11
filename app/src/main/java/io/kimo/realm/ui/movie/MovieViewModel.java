package io.kimo.realm.ui.movie;

import android.content.Context;
import android.support.annotation.Nullable;

import io.kimo.realm.data.model.Movie;
import io.kimo.realm.ui.base.BaseViewModel;

public class MovieViewModel extends BaseViewModel implements MovieContract.ViewModel {
    private Movie mMovie;

    public MovieViewModel(Context context) {
        super(context);
    }

    @Override
    public void setMovie(@Nullable Movie movie) {
        if (movie == null) {
            setState(STATE_LOADING);
        } else {
            mMovie = movie;
            setState(STATE_NORMAL);
            notifyPropertyChanged(PROPERTY_COVER);
        }
    }

    @Override
    public String getTitle() {
        if (mMovie == null) return "";
        return mMovie.getTitle();
    }

    @Override
    public String getGenre() {
        if (mMovie == null) return "";
        return mMovie.getGenre();
    }

    @Override
    public String getDirector() {
        if (mMovie == null) return "";
        return mMovie.getDirector();
    }

    @Override
    public String getActors() {
        if (mMovie == null) return "";
        return mMovie.getActors();
    }

    @Override
    public String getPlot() {
        if (mMovie == null) return "";
        return mMovie.getPlot();
    }

    @Override
    public String getCoverUrl() {
        return mMovie.getPosterUrl();
    }
}
