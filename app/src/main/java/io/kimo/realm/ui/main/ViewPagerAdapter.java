package io.kimo.realm.ui.main;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.kimo.realm.R;
import io.kimo.realm.util.ViewBuilder;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int PAGE_MOVIE = 0;
    int PAGE_LIST = 1;

    private Context mContext;

    public ViewPagerAdapter(@NonNull Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == PAGE_LIST) {
            return ViewBuilder.buildUserListView(mContext);
        } else if (position == PAGE_MOVIE) {
            return ViewBuilder.buildMovieView(mContext);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == PAGE_LIST) {
            return mContext.getString(R.string.list);
        } else if (position == PAGE_MOVIE) {
            return mContext.getString(R.string.movie);
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
