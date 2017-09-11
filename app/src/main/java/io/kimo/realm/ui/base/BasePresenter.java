package io.kimo.realm.ui.base;

import android.support.annotation.NonNull;
import android.util.Log;

import io.kimo.realm.BuildConfig;
import io.kimo.realm.data.local.SharedPreferencesHelper;

public abstract class BasePresenter<VM extends BaseContract.ViewModel> implements BaseContract.Presenter {

    private static final String TAG = "BasePresenter";

    @NonNull protected final VM mViewModel;
    @NonNull protected final SharedPreferencesHelper mSharedPreferencesHelper;

    public BasePresenter(@NonNull VM viewModel, @NonNull SharedPreferencesHelper sharedPreferencesHelper) {
        mViewModel = viewModel;
        mSharedPreferencesHelper = sharedPreferencesHelper;
    }

    @Override
    public void createView() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "createView");
        }
    }

    @Override
    public void destroyView() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "destroyView");
        }
    }
}
