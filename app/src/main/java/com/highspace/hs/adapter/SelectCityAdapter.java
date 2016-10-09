package com.highspace.hs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.highspace.hs.R;
import com.highspace.hs.bean.City;
import com.highspace.hs.bean.Province;


import java.util.List;


/**
 * Created by Administrator on 2016/9/16.
 * popwindow适配器
 */


public class SelectCityAdapter<T> extends BaseAdapter {

    //private Context context;
    private LayoutInflater mLayoutInflater;
    private List<T> mList;
    private int mType;
    private int mSelect = 0;

    public SelectCityAdapter(Context context, List<T> mList, int mType) {
        this.mList = mList;
        this.mType = mType;
        // this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mType == 0 ? ((Province) mList.get(position)).getProvinceName() : ((City) mList.get(position)).getCityName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = mLayoutInflater.inflate(R.layout.item_selector, null);

        TextView textView = (TextView) view.findViewById(R.id.item_selector_tv);
        textView.setText(mType == 0 ? ((Province) mList.get(position)).getProvinceName() : ((City) mList.get(position)).getCityName());

        if (position == mSelect && mType == 0) {
            view.setBackgroundResource(R.color.listview_item_pressed);
        } else if (mType == 0 && position != mSelect) {
            view.setBackgroundResource(R.color.listview_item_unpressed);
        }

        return view;
    }

}
