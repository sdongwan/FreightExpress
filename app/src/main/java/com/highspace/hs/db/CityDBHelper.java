package com.highspace.hs.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.highspace.hs.bean.City;
import com.highspace.hs.bean.Country;
import com.highspace.hs.bean.Province;
import com.highspace.hs.util.MobleStorageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/9/16.
 */
public class CityDBHelper {
    private SQLiteDatabase mDB;

    public static CityDBHelper newInstance(Context context) {

        return new CityDBHelper(context);
    }

    private CityDBHelper(Context context) {
        copyDataBase(context);
        mDB = getDBFromLocalFile(context);

    }

    public void copyDataBase(Context context) {
        // 每个应用都有一个数据库目录，他位于 /data/data/yourpackagename/databases/目录下
        String packageName = context.getPackageName();// xml中配置的:"com.ttdevs.citydata"
        String dbName = "ChinaCity.db";
        String dbPath = null;
        if (MobleStorageUtil.isExternalStorageReadable() && MobleStorageUtil.isExternalStorageWritable()) { // sdcard
            dbPath = Environment.getExternalStorageDirectory() + File.separator + dbName;
        } else { // local
            dbPath = "/data/data/" + packageName + "/databases/" + dbName;
        }
        File dbFile = new File(dbPath);
        if (dbFile.exists()) {
            dbFile.delete();
        }
        try {
            dbFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        try {
            InputStream is = context.getResources().getAssets().open(dbName);
            OutputStream os = new FileOutputStream(dbPath);

            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            os.flush();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Country getProVincesFromDB() {
        Cursor cursor = mDB.query("china_provinces_code", new String[]{"name"}, null, null, null, null, null);
        List<Province> provinces = new ArrayList<>();
        while (cursor.moveToNext()) {
            Province province = new Province();
            province.setProvinceName(cursor.getString(cursor.getColumnIndex("name")));
            provinces.add(province);
        }
        Country country = new Country();
        country.setCountry(provinces);
        cursor.close();
        return country;

    }

    public Province getCitysFromDB(String provinceName) {
        Cursor c = mDB.rawQuery("select county from china_city_code where province = ?", new String[]{provinceName + ""});
        List<City> citys = new ArrayList<>();
        while (c.moveToNext()) {
            City city = new City();
            city.setCityName(c.getString(c.getColumnIndex("county")));
            citys.add(city);
        }
        Province province = new Province();
        province.setCities(citys);
        c.close();
        return province;

    }

    /*
        public void readDataBaseFromSDCard(Context context) {
            String dbPath = "/data/data/" + context.getPackageName() + "/databases/" + "ChinaCity.mDB";
            File dbFile = new File(dbPath);
            SQLiteDatabase mDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            StringBuffer sb = new StringBuffer();
            Cursor cursor = mDB.rawQuery("select * from china_provinces_code", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                sb.append(id + ":" + name + " \n");
            }
            System.out.println(sb.toString());
        }
    */
    public SQLiteDatabase getDBFromLocalFile(Context context) {
        //dbPath = Environment.getExternalStorageDirectory() + File.separator + dbName;
//        String dbPath = "/data/data/" + context.getPackageName() + "/databases/" + "ChinaCity.mDB";
        String dbPath = Environment.getExternalStorageDirectory() + File.separator + "ChinaCity.db";

        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

        return db;
    }
/*
    public SQLiteDatabase getDbFromAssets(Context context){
        AssetManager assetManager=context.getResources().getAssets();
        assetManager.



    }
*/

}


