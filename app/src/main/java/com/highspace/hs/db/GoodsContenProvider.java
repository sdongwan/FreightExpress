package com.highspace.hs.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/10/4.
 */

public class GoodsContenProvider extends ContentProvider {
    private GoodsInfoSQLiteHelper mGoodsInfoSqLiteHelper;

    private GoodsDBHelper mGoodsDBHelper;
    //private SQLiteDatabase mDB;
    private static UriMatcher uriMatcher;
    public static final String AUTHRIORY = "com.highspace.hs";
    public static final Uri URI_Goods_ALL = Uri.parse("content://" + AUTHRIORY + "/goods");
    private static final int SIMGLE_PARAM = 1;
    private static final int MUTIL_PARAM = 2;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHRIORY, "goods/#", SIMGLE_PARAM);
        uriMatcher.addURI(AUTHRIORY, "goods", MUTIL_PARAM);
    }


    @Override
    public boolean onCreate() {
        mGoodsInfoSqLiteHelper = new GoodsInfoSQLiteHelper(getContext());
        //mDB = mGoodsInfoSqLiteHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mGoodsInfoSqLiteHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case MUTIL_PARAM:
                return db.query("goods_info", projection, selection, selectionArgs, null, null, sortOrder);
            case SIMGLE_PARAM:
                long id = ContentUris.parseId(uri); // 取得跟在URI后面的数字
                String where = "_id = " + id;
                if (null != selection && !"".equals(selection.trim())) {
                    where += " and " + selection;
                }
                return db.query("goods_info", projection, where, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("this is unkown mUri:" + uri);
        }

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MUTIL_PARAM:
                return "com.highspace.hs/goods";
            case SIMGLE_PARAM:
                return "com.highspace.hs/goods/#";
            default:
                throw new IllegalArgumentException("this is unkown mUri:" + uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mGoodsInfoSqLiteHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case MUTIL_PARAM:
            case SIMGLE_PARAM:
                // 若主键值是自增长的id值则返回值为主键值，否则为行号，但行号并不是RecNo列
                long id = db.insert("goods_info", null, values);
                Uri insertUri = ContentUris.withAppendedId(uri, id);
                return insertUri;
            default:
                throw new IllegalArgumentException("this is unkown mUri:" + uri);
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mGoodsInfoSqLiteHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case MUTIL_PARAM:
                return db.delete("goods_info", selection, selectionArgs); // 删除所有记录
            case SIMGLE_PARAM:
                long id = ContentUris.parseId(uri); // 取得跟在URI后面的数字
                Log.i("provider", String.valueOf(id));
                String where = "_id = " + id;
                if (null != selection && !"".equals(selection.trim())) {
                    where += " and " + selection;
                }
                return db.delete("goods_info", where, selectionArgs);
            default:
                throw new IllegalArgumentException("this is unkown mUri:" + uri);
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mGoodsInfoSqLiteHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case MUTIL_PARAM:
                return db.update("goods_info", values, selection, selectionArgs); // 更新所有记录
            case SIMGLE_PARAM:
                long id = ContentUris.parseId(uri); // 取得跟在URI后面的数字
                String where = "_id = " + id;
                if (null != selection && !"".equals(selection.trim())) {
                    where += " and " + selection;
                }
                return db.update("goods_info", values, where, selectionArgs);
            default:
                throw new IllegalArgumentException("this is unkown mUri:" + uri);
        }
    }
}
