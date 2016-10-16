package com.highspace.hs.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.highspace.hs.R;

/**
 * Created by wenyue on 2016/10/10.
 */

public class AddressAdapter extends CursorAdapter {
    private Context mContext;

    /*public AddressAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }*/

    public AddressAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = LayoutInflater.from(context).inflate(R.layout.address_list_item, parent, false);
        viewHolder.na = (TextView) view.findViewById(R.id.name);
        viewHolder.pn = (TextView) view.findViewById(R.id.number);
        viewHolder.ad = (TextView) view.findViewById(R.id.ads);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.na.setText(cursor.getString(cursor.getColumnIndex("addressName")));
        viewHolder.pn.setText(cursor.getString(cursor.getColumnIndex("phoneNumber")));
        viewHolder.ad.setText(cursor.getString(cursor.getColumnIndex("addressLocation")));
    }


   /* @NonNull
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
    }*/
    class ViewHolder{
        TextView na;
        TextView pn;
        TextView ad;
    }
}
