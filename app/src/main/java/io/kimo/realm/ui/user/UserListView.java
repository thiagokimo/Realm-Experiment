package io.kimo.realm.ui.user;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.kimo.realm.databinding.ListLayoutBinding;
import io.kimo.realm.ui.base.BaseView;

public class UserListView extends BaseView<UserListContract.Presenter, UserListContract.ViewModel, ListLayoutBinding> implements UserListContract.View{

    @Override
    protected ListLayoutBinding inflateDataBinding(LayoutInflater inflater, ViewGroup container) {
        ListLayoutBinding binding = ListLayoutBinding.inflate(inflater, container, false);
        configureRecyclerView(binding);
        return binding;
    }

    private void configureRecyclerView(ListLayoutBinding binding) {
        RecyclerView recyclerView = binding.list;

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mViewModel.getAdater());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
