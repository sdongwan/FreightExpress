package com.highspace.hs.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/8.
 */

public class GuideAdapter extends PagerAdapter {


    private ArrayList<View> mViews;

    public GuideAdapter(ArrayList<View> mViews) {
        this.mViews = mViews;
    }

    @Override
    public int getCount() {
        return mViews.size() > 0 ? mViews.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //  super.destroyItem(container, position, object);
        container.removeView(mViews.get(position));
    }
}
