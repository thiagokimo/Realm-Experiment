package io.kimo.realm.util;

import android.content.Context;
import android.support.annotation.NonNull;

import io.kimo.realm.App;
import io.kimo.realm.data.model.Movie;
import io.kimo.realm.data.model.User;
import io.kimo.realm.data.repository.realm.MovieRealmRepository;
import io.kimo.realm.data.repository.realm.UserRealmRepository;
import io.kimo.realm.ui.movie.MoviePresenter;
import io.kimo.realm.ui.movie.MovieView;
import io.kimo.realm.ui.movie.MovieViewModel;
import io.kimo.realm.ui.user.UserListAdapter;
import io.kimo.realm.ui.user.UserListPresenter;
import io.kimo.realm.ui.user.UserListView;
import io.kimo.realm.ui.user.UserListViewModel;
import io.realm.Realm;

public class ViewBuilder {
    public static UserListView buildUserListView(@NonNull Context context) {
        UserListViewModel vm = new UserListViewModel(context);
        vm.setAdater(new UserListAdapter());
        UserListPresenter p = new UserListPresenter(
                vm,
                ((App)context).getDataManager().getSharedPreferencesHelper(),
                new UserRealmRepository(Realm.getDefaultInstance(), User.class)
        );
        UserListView v = new UserListView();
        v.setPresenter(p);
        v.setViewModel(vm);
        return v;
    }

    public static MovieView buildMovieView(@NonNull Context context) {
        MovieViewModel vm = new MovieViewModel(context);
        MoviePresenter p = new MoviePresenter(
                vm,
                ((App)context).getDataManager().getSharedPreferencesHelper(),
                new MovieRealmRepository(Realm.getDefaultInstance(), Movie.class)
        );
        MovieView v = new MovieView();
        v.setPresenter(p);
        v.setViewModel(vm);
        return v;
    }
}
