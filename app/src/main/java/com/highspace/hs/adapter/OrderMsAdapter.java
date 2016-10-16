package com.highspace.hs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenyue on 2016/10/11.
 */

public class OrderMsAdapter extends FragmentPagerAdapter{
    private String [] titleArray = new String[]{"待处理", "运输中", "已完成"};
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public OrderMsAdapter(FragmentManager fm,  List<Fragment> list) {
        super(fm);
        fragmentList = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArray[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size() > 0 ? fragmentList.size() : 0;
    }
}
