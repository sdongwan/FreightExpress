package com.highspace.hs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/11.
 * tab适配器
 */
public class TabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragmentsList;

    public TabAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
        super(fm);
        this.mFragmentsList = fragmentsList;


    }


    @Override
    public int getCount() {
        return mFragmentsList.size() > 0 ? mFragmentsList.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return position < mFragmentsList.size() ? mFragmentsList.get(position) : null;
    }
}
