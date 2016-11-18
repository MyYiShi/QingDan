package com.example.administrator.yisihqingdan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Yishi on 2016/10/27.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;

    public MainFragmentPagerAdapter(FragmentManager fm , List<Fragment> fragments) {
        super(fm);
        this.fragments =fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
