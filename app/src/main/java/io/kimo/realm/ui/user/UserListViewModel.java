package io.kimo.realm.ui.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.kimo.realm.data.model.User;
import io.kimo.realm.ui.base.BaseViewModel;

public class UserListViewModel extends BaseViewModel implements UserListContract.ViewModel {
    private static final String TAG = "UserListViewModel";
    private String mErrorReason = "";

    private UserListAdapter mAdapter;

    public UserListViewModel(Context context) {
        super(context);
    }

    @Override
    public void setErrorReason(@NonNull String errorReason) {
        Log.d(TAG, "setErrorReason: "+errorReason);
        mErrorReason = errorReason;
        setState(STATE_ERROR);
    }

    @Override
    public String getErrorReason() {
        return mErrorReason;
    }

    @Override
    public void showUsers(List<User> users) {
        if (users.isEmpty()) {
            setState(STATE_LOADING);
        } else {
            mAdapter.setUsers(users);
            setState(STATE_NORMAL);
        }
    }

    @Override
    public void setAdater(UserListAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public UserListAdapter getAdater() {
        return mAdapter;
    }
}
