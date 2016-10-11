package com.highspace.hs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.highspace.hs.R;
import com.highspace.hs.bean.AddressMessage;

import java.util.List;

/**
 * Created by wenyue on 2016/10/10.
 */

public class AddressAdapter extends ArrayAdapter<AddressMessage> {
    private Context mContext;
    private List<AddressMessage> messageList;
    public AddressAdapter(Context context, List<AddressMessage> datds) {
        super(context, -1, datds);
        messageList = datds;
        mContext = context;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AddressMessage message = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.address_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.na = (TextView) view.findViewById(R.id.name);
            viewHolder.pn = (TextView) view.findViewById(R.id.number);
            viewHolder.ad = (TextView) view.findViewById(R.id.ads);
            view.setTag(viewHolder);

        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.na.setText(message.getName());
        viewHolder.pn.setText(message.getPhoneNumber());
        viewHolder.ad.setText(message.getAds());
        return view;
    }
    class ViewHolder{
        TextView na;
        TextView pn;
        TextView ad;
    }
}
