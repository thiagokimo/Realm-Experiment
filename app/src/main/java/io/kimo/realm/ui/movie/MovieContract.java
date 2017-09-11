package io.kimo.realm.ui.movie;

import android.support.annotation.Nullable;

import io.kimo.realm.data.model.Movie;
import io.kimo.realm.ui.base.BaseContract;

public interface MovieContract {
    interface View extends BaseContract.View<Presenter, ViewModel> {}
    interface Presenter extends BaseContract.Presenter {}

    interface ViewModel extends BaseContract.ViewModel {
        int PROPERTY_COVER = 3;

        void setMovie(@Nullable Movie movie);

        String getTitle();
        String getGenre();
        String getDirector();
        String getActors();
        String getPlot();
        String getCoverUrl();
    }
}
