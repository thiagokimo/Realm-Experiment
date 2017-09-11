package io.kimo.realm.ui.movie;

import android.databinding.Observable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import io.kimo.realm.databinding.MovieLayoutBinding;
import io.kimo.realm.ui.base.BaseView;

public class MovieView extends BaseView<MovieContract.Presenter, MovieContract.ViewModel, MovieLayoutBinding> implements MovieContract.View {

    private Observable.OnPropertyChangedCallback mCoverUrlListener = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if (i == MovieContract.ViewModel.PROPERTY_COVER) {
                String coverUrl = mViewModel.getCoverUrl();

                if (!TextUtils.isEmpty(coverUrl) && mBinding != null) {
                    Picasso.with(getContext()).load(coverUrl).into(mBinding.cover);
                }
            }
        }
    };

    @Override
    protected MovieLayoutBinding inflateDataBinding(LayoutInflater inflater, ViewGroup container) {
        MovieLayoutBinding binding = MovieLayoutBinding.inflate(inflater, container, false);
        configureCover();
        return binding;
    }

    private void configureCover() {
        mViewModel.addOnPropertyChangedCallback(mCoverUrlListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModel.removeOnPropertyChangedCallback(mCoverUrlListener);
    }
}
