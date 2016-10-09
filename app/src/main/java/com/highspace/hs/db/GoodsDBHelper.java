package com.highspace.hs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.highspace.hs.bean.Goods;

/**
 * Created by Administrator on 2016/10/3.
 */

public class GoodsDBHelper {
    private static GoodsDBHelper mGoodsDBHelper;
    public static SQLiteDatabase mDB;

    public static GoodsDBHelper newInstance(Context context) {
        if (mGoodsDBHelper == null) {
            synchronized (GoodsDBHelper.class) {
                if (mGoodsDBHelper == null) {
                    mGoodsDBHelper = new GoodsDBHelper(context);
                }
            }
        }
        return mGoodsDBHelper;
    }

    private GoodsDBHelper(Context context) {
        mDB = new GoodsInfoSQLiteHelper(context).getWritableDatabase();

    }


    public static void saveGoodsInfo(Goods goods) {
        if (goods == null || mDB == null) {
            return;
        }
        ContentValues values = new ContentValues();
        values.put("startPlace", goods.getStartPlace());
        values.put("destination", goods.getDestination());
        values.put("weight", goods.getWeight());
        values.put("volume", goods.getVolume());
        values.put("decribe", goods.getDecribe());
        values.put("phoneNum", goods.getPhoneNum());
        values.put("remark", goods.getRemark());
        values.put("date", goods.getDate());



        mDB.insert("goods_info", null, values);


    }

/*
    public Goods getGoodsInfo(int goodsId) {
        if (mDB == null) {
            return null;
        }
        Cursor c = mDB.rawQuery("select * from goods_info where goodsId=?", new String[]{String.valueOf(goodsId)});
        if (c.moveToFirst()) {
            try {
                String startPlace = String.valueOf(c.getString(c.getColumnIndex("startPlace")));
                String destination = String.valueOf(c.getString(c.getColumnIndex("destination")));
                String weight = String.valueOf(c.getString(c.getColumnIndex("weight")));
                String volume = String.valueOf(c.getString(c.getColumnIndex("volume")));
                String decribe = String.valueOf(c.getString(c.getColumnIndex("decribe")));
                String phoneNum = String.valueOf(c.getString(c.getColumnIndex("phoneNum")));
                String remark = String.valueOf(c.getString(c.getColumnIndex("remark")));
                String date = String.valueOf(c.getString(c.getColumnIndex("date")));
                c.close();
                return new Goods(startPlace, destination, weight, volume, decribe, phoneNum, remark);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public List<Goods> getGoodsInfo() {
        if (mDB == null) {
            return null;
        }
        Cursor c = mDB.query("goods_info", null, null, null, null, null, null, null);
        List<Goods> goods = new ArrayList<>();
        Goods goods1;
        while (c.moveToNext()) {
            try {
                String startPlace = String.valueOf(c.getString(c.getColumnIndex("startPlace")));
                String destination = String.valueOf(c.getString(c.getColumnIndex("destination")));
                String weight = String.valueOf(c.getString(c.getColumnIndex("weight")));
                String volume = String.valueOf(c.getString(c.getColumnIndex("volume")));
                String decribe = String.valueOf(c.getString(c.getColumnIndex("decribe")));
                String phoneNum = String.valueOf(c.getString(c.getColumnIndex("phoneNum")));
                String remark = String.valueOf(c.getString(c.getColumnIndex("remark")));
                String date = String.valueOf(c.getString(c.getColumnIndex("date")));

                c.close();
                goods1 = new Goods(startPlace, destination, weight, volume, decribe, phoneNum, remark);
                goods.add(goods1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return goods;

    }
*/

}
