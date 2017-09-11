package io.kimo.realm.ui.user;

import android.support.annotation.NonNull;

import java.util.List;

import io.kimo.realm.data.model.User;
import io.kimo.realm.ui.base.BaseContract;

public interface UserListContract {
    interface View extends BaseContract.View<Presenter, ViewModel> {}
    interface Presenter extends BaseContract.Presenter {
        void onRetryClicked();
    }

    interface ViewModel extends BaseContract.ViewModel {
        void setErrorReason(@NonNull String errorReason);
        String getErrorReason();

        void showUsers(List<User>users);

        void setAdater(UserListAdapter adapter);
        UserListAdapter getAdater();
    }
}
