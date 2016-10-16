package com.highspace.hs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wenyue on 2016/10/13.
 */

public class AddressInfoSQLiteHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_ADDRESS = "create table address("
            +"_id integer primary key autoincrement,"
            +"addressName text,"
            +"phoneNumber text,"
            +"addressLocation text)";

    public static final String CREATE_END = "create table end("
            +"_id integer primary key autoincrement,"
            +"addressName text,"
            +"phoneNumber text,"
            +"addressLocation text)";

    private AddressInfoSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    private static AddressInfoSQLiteHelper mHelper;
    public static AddressInfoSQLiteHelper getmHelperInstance(Context c, String n, SQLiteDatabase.CursorFactory f, int v){

        if (mHelper == null){
            mHelper = new AddressInfoSQLiteHelper(c, n , f, v);

        }
        return  mHelper;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ADDRESS);
        db.execSQL(CREATE_END);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
