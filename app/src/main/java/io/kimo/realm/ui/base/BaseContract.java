package io.kimo.realm.ui.base;

import android.databinding.Observable;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface BaseContract {
    interface View<P extends Presenter, VM extends ViewModel>{
        void setPresenter(P presenter);
        void setViewModel(VM viewModel);
    }

    interface Presenter {
        void createView();
        void destroyView();
    }

    interface ViewModel extends Observable {
        int STATE_LOADING = 0;
        int STATE_ERROR = 1;
        int STATE_NORMAL = 2;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef(value = {STATE_LOADING, STATE_ERROR, STATE_NORMAL})
        @interface State {}

        void setState(@State int state);
        @State int getState();
    }
}
