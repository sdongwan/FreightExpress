package com.highspace.hs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.highspace.hs.R;
import com.highspace.hs.util.ImageLoadUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/19.
 * 物流适配器
 */
public class WuLiuAdapter extends BaseAdapter {
    private ArrayList<String> mItems;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public WuLiuAdapter(Context mContext, ArrayList<String> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
        mLayoutInflater = LayoutInflater.from(mContext);
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_wuliu_info, null);
            viewHolder=new ViewHolder();
            viewHolder.mNameTV = (TextView) convertView.findViewById(R.id.item_wuliu_info_companyname_tv);
            viewHolder.mJianjieTv = (TextView) convertView.findViewById(R.id.item_wuliu_info_inroduce_tv);
            viewHolder.mImg = (ImageView) convertView.findViewById(R.id.item_wuliu_info_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mNameTV.setText("XXX物流有限公司");
        viewHolder.mJianjieTv.setText("广东省江门市XXXXX");
        ImageLoadUtil.getImageLoader(mContext).displayImage("http://www.sdongwan.top/images/a.png", viewHolder.mImg);
        // TODO: 2016/9/27 图片大小设置

        return convertView;

    }

    class ViewHolder {

        TextView mNameTV;
        TextView mJianjieTv;
        ImageView mImg;
    }


}
