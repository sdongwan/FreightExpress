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
 * Created by Administrator on 2016/10/5.
 */

public class MyGoodsInfoAdapter extends CursorAdapter {
    public MyGoodsInfoAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_goods_info, null);
        viewHolder.mMyGoodsImg = (ImageView) view.findViewById(R.id.my_goods_info_img_iv);
        viewHolder.mMyGoodsStart = (TextView) view.findViewById(R.id.my_goods_info_start_tv);
        viewHolder.mMyGoodsDest = (TextView) view.findViewById(R.id.my_goods_info_dest_tv);
        viewHolder.mMyGoodsdate = (TextView) view.findViewById(R.id.my_goods_info_date_tv);
        viewHolder.mMyGoodsweight = (TextView) view.findViewById(R.id.my_goods_info_weight_tv);
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


        viewHolder.mMyGoodsStart.setText(cursor.getString(cursor.getColumnIndex("startPlace")) + "");
        viewHolder.mMyGoodsDest.setText(cursor.getString(cursor.getColumnIndex("destination")) + "");
        viewHolder.mMyGoodsdate.setText("发布时间：" + cursor.getString(cursor.getColumnIndex("date")));
        viewHolder.mMyGoodsweight.setText("重量：" + cursor.getString(cursor.getColumnIndex("weight")));


    }

    class ViewHolder {
        ImageView mMyGoodsImg;
        TextView mMyGoodsStart;
        TextView mMyGoodsDest;
        TextView mMyGoodsdate;
        TextView mMyGoodsweight;
    }


}
