package com.highspace.hs.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/1.
 */

public class SharedPreferencesUtil {
    /**
     * @param spName  SharedPreferences的名字
     * @param data    保存的数据的值
     * @param key     保存数据的键
     * @param context 上下文环境
     */
    public static void saveStringData(String spName, String data, String key, Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.commit();

    }

    /**
     * @param spName      SharedPreferences的名字
     * @param defaultData 默认的值
     * @param key         保存数据的键
     * @param context     上下文环境
     * @return
     */
    public static String getStringData(String spName, String defaultData, String key, Context context) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        String data = sp.getString(key, defaultData);
        return data;
    }

    /**
     * @param spName  SharedPreferences的名字
     * @param data    保存的数据的值
     * @param key     保存数据的键
     * @param context 上下文环境
     */

    public static void saveBooleanData(String spName, boolean data, String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }


    /**
     * @param spName      SharedPreferences的名字
     * @param defaultData 默认的值
     * @param key         保存数据的键
     * @param context     上下文环境
     */
    public static boolean getBooleanData(String spName, boolean defaultData, String key, Context context) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        boolean data = sp.getBoolean(key, defaultData);
        return data;
    }


}
