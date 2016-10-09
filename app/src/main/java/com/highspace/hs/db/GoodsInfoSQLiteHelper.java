package com.highspace.hs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/10/6.
 */

public class GoodsInfoSQLiteHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "goods.mDB";
    /*
      private String startPlace;
    private String destination;
    private String weight;
    private String volume;//体积
    private String decribe;
    private String phoneNum;
    private String remark;//备注
    private int goodsId;
    private String date;
     */
    //SQL语句，创建表
    private final static String CREATE_CACHE_TABLE = "create table goods_info(" +
            "_id integer primary key autoincrement," +
            "startPlace text," +
            "destination text," +
            "weight text," +
            "volume text," +
            "decribe text," +
            "phoneNum text," +
            "remark text," +
            "date text" +
            ")";

    public GoodsInfoSQLiteHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CACHE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
