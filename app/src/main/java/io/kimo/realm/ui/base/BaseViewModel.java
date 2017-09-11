package io.kimo.realm.ui.base;

import android.content.Context;
import android.databinding.BaseObservable;

import io.kimo.realm.ui.user.UserListContract;

public class BaseViewModel extends BaseObservable implements BaseContract.ViewModel {
    protected Context mContext;
    private @State int mState = UserListContract.ViewModel.STATE_LOADING;

    public BaseViewModel(Context context) {
        mContext = context;
    }

    @Override
    public void setState(int state) {
        mState = state;
        notifyChange();
    }

    @Override
    public int getState() {
        return mState;
    }
}
