package com.highspace.hs.adapter;

import android.content.Context;
<<<<<<< HEAD
=======
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.highspace.hs.R;

import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721

/**
 * Created by Administrator on 2016/9/20.
 * 我的地址适配器
 */


<<<<<<< HEAD
public class UserAdressAdapter extends PagerAdapter {

    private String titlenames[] = new String[]{"发货地址", "目的地址"};
    private LayoutInflater mLayoutInflater;

    private ArrayList<ImageView> mImageViews;

    private Context mContext;

    public UserAdressAdapter(Context mContext) {

        //mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        initImages();
    }
=======
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


>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721


    @Override
    public int getCount() {
<<<<<<< HEAD
        return titlenames.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
=======
        return mList.size() >0  ? mList.size():0;
    }

   /* @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }*/
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721

    @Override
    public CharSequence getPageTitle(int position) {
        return titlenames[position];
    }

    @Override
<<<<<<< HEAD
=======
    public Fragment getItem(int position) {
        return mList.get(position);
    }

   /* @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position).getView());
        return mList.get(position);
    }*/
    /*@Override
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageViews.get(position));
        return mImageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViews.get(position));

<<<<<<< HEAD
    }


    private void initImages() {
=======
    }*/


   /* private void initImages() {
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721

        mImageViews = new ArrayList<>();
        for (int i = 0; i < titlenames.length; i++) {

            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(R.mipmap.ic_launcher);
            mImageViews.add(imageView);


        }


<<<<<<< HEAD
    }
=======
    }*/
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
}
