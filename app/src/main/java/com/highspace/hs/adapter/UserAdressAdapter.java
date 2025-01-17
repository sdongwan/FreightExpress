package com.highspace.hs.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.highspace.hs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 * 我的地址适配器
 */


public class UserAdressAdapter extends FragmentPagerAdapter {

    private String titlenames[] = new String[]{"发货地址", "目的地址"};
    private List<Fragment> mList = new ArrayList<Fragment>();
    //private LayoutInflater mLayoutInflater;

    //private ArrayList<ImageView> mImageViews;

    private Context mContext;
    public UserAdressAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        mList = list;
    }




    @Override
    public int getCount() {
        return mList.size() >0  ? mList.size():0;
    }

   /* @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }*/

    @Override
    public CharSequence getPageTitle(int position) {
        return titlenames[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

   /* @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position).getView());
        return mList.get(position);
    }*/
    /*@Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageViews.get(position));
        return mImageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViews.get(position));

    }*/


   /* private void initImages() {

        mImageViews = new ArrayList<>();
        for (int i = 0; i < titlenames.length; i++) {

            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(R.mipmap.ic_launcher);
            mImageViews.add(imageView);


        }


    }*/
}
