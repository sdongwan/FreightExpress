package com.highspace.hs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.highspace.hs.R;

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
            viewHolder = new ViewHolder();
            viewHolder.companyNameTV = (TextView) convertView.findViewById(R.id.item_wuliu_company_name_tv);

            viewHolder.img = (ImageView) convertView.findViewById(R.id.item_wuliu_info_iv);
            viewHolder.address = (TextView) convertView.findViewById(R.id.item_wuliu_address_tv);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*
         viewHolder.companyNameTV.setText("");
        viewHolder.destTv.setText("");
        viewHolder.startTv.setText("");
         */


        // TODO: 2016/9/27 图片大小设置   ImageLoadUtil.getImageLoader(mContext).displayImage("http://www.sdongwan.top/images/a.png", viewHolder.img);
        viewHolder.img.setImageResource(R.mipmap.icon_no_img);

        return convertView;

    }

    class ViewHolder {

        TextView companyNameTV;
        //TextView startTv;
        //TextView destTv;
        ImageView img;
        TextView address;

    }


}
