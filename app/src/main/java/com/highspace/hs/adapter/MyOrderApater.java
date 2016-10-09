package com.highspace.hs.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.highspace.hs.R;

/**
 * Created by Administrator on 2016/10/7.
 */

public class MyOrderApater extends CursorAdapter {

    public MyOrderApater(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_order, null);
        viewHolder.mMyOrderImg = (ImageView) view.findViewById(R.id.my_order_img_iv);
        viewHolder.mMyOrderStart = (TextView) view.findViewById(R.id.my_order_start_tv);
        viewHolder.mMyOrderDest = (TextView) view.findViewById(R.id.my_order_dest_tv);
        viewHolder.mMyOrderdate = (TextView) view.findViewById(R.id.my_order_date_tv);
        viewHolder.mMyOrderweight = (TextView) view.findViewById(R.id.my_order_weight_tv);
        view.setTag(viewHolder);
        return view;
    }

    /**
     * + "startPlace" + " text, "
     * + "destination" + " text, "
     * + "weight" + " text, "
     * + "volume" + " text, "
     * + "decribe" + " text, "
     * + "phoneNum" + " TEXT, "
     * + "remark" + " TEXT,"
     * + "date" + "text,"
     * + "goodsId" + "integer"
     *
     * @param view
     * @param context
     * @param cursor
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            Toast.makeText(context, "dsfsd", Toast.LENGTH_SHORT).show();
            return;
        }
        /*
                Log.e("startplace", cursor.getString(cursor.getColumnIndex("startPlace")));
        Log.e("destination", cursor.getString(cursor.getColumnIndex("destination")));
        Log.e("volume", cursor.getString(cursor.getColumnIndex("volume")));
        Log.e("decribe", cursor.getString(cursor.getColumnIndex("decribe")));
        Log.e("phoneNum", cursor.getString(cursor.getColumnIndex("phoneNum")));
        Log.e("weight", cursor.getString(cursor.getColumnIndex("weight")));
        Log.e("remark", cursor.getString(cursor.getColumnIndex("remark")));
        Log.e("date", cursor.getString(cursor.getColumnIndex("date")));
  //Log.e("date", cursor.getString(cursor.getColumnIndex("date")));
         */


        viewHolder.mMyOrderStart.setText(cursor.getString(cursor.getColumnIndex("startPlace")) + "");
        viewHolder.mMyOrderDest.setText(cursor.getString(cursor.getColumnIndex("destination")) + "");
        viewHolder.mMyOrderdate.setText("发布时间：" + cursor.getString(cursor.getColumnIndex("date")));
        viewHolder.mMyOrderweight.setText("重量：" + cursor.getString(cursor.getColumnIndex("weight")));


    }

    class ViewHolder {
        ImageView mMyOrderImg;
        TextView mMyOrderStart;
        TextView mMyOrderDest;
        TextView mMyOrderdate;
        TextView mMyOrderweight;
    }

}
