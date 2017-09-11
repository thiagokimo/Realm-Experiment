package io.kimo.realm.ui.user;

import android.support.annotation.NonNull;

import io.kimo.realm.data.Repository;
import io.kimo.realm.data.local.SharedPreferencesHelper;
import io.kimo.realm.data.model.User;
import io.kimo.realm.data.repository.realm.UserRealmRepository;
import io.kimo.realm.ui.base.BasePresenter;
import io.realm.RealmResults;

public class UserListPresenter extends BasePresenter<UserListContract.ViewModel> implements UserListContract.Presenter{
    private UserRealmRepository mUserRepository;
    private RealmResults<User> mUsers;

    public UserListPresenter(
            @NonNull UserListContract.ViewModel viewModel,
            @NonNull SharedPreferencesHelper sharedPreferencesHelper,
            @NonNull Repository<User> userRepository) {
        super(viewModel, sharedPreferencesHelper);
        mUserRepository = (UserRealmRepository) userRepository;
    }

    @Override
    public void onRetryClicked() {}

    @Override
    public void createView() {
        super.createView();

        loadUsers();
    }

    private void loadUsers() {
        mViewModel.setState(UserListContract.ViewModel.STATE_LOADING);
        mUsers = mUserRepository.all();
        mUsers.addChangeListener(mViewModel::showUsers);
        mViewModel.showUsers(mUsers);
    }

    @Override
    public void destroyView() {
        super.destroyView();
        mUsers.removeAllChangeListeners();
        mUserRepository.getRealmInstance().close();
    }
}
