package com.example.joe_pc.joeweather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by Joe_PC on 2017/3/20.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private Fragment mFragment;

    public FragmentAdapter(FragmentManager fm, Fragment fragment) {
        super(fm);
        mFragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
