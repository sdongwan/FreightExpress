package com.highspace.hs.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 * 轮播器的适配器
 */
public class HomeCarouselAdapter extends PagerAdapter {

    private List<ImageView> mImageViews;


    public HomeCarouselAdapter(List<ImageView> mImageViews) {
        this.mImageViews = mImageViews;


    }


    @Override
    public int getCount() {
        return mImageViews.size() > 0 ? mImageViews.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageViews.get(position));
        return mImageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //  super.destroyItem(container, position, object);
        container.removeView(mImageViews.get(position));
    }
}
