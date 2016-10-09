package com.highspace.hs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.highspace.hs.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/26.
 */
public class SelectSortAdapter extends BaseAdapter {
    private ArrayList<String> mItems;
    private LayoutInflater mLayoutInflater;

    public SelectSortAdapter(Context context, ArrayList<String> mItems) {
        this.mItems = mItems;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = mLayoutInflater.inflate(R.layout.item_selector, null);
        TextView textView = (TextView) itemView.findViewById(R.id.item_selector_tv);
        textView.setText(mItems.get(position));
        return itemView;
    }
}
