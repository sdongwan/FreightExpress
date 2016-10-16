package com.highspace.hs.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/18.
 */
public class AppAplication extends Application {

    public static Context pApplicationContext;
    public static boolean loginFlag = false;
    private static AppAplication appAplication;
    private ArrayList<Activity> mActivityList=new ArrayList<>();

    public static AppAplication getInstance() {
        if (appAplication == null) {
            appAplication = new AppAplication();
        }
        return appAplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        pApplicationContext = this;
       // mActivityList = new ArrayList<>();


    }

    // add Activity
    public void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    public void exitApplication() {
        try {
            for (Activity activity : mActivityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }

    }


    public static Context getContext() {

        return pApplicationContext;
    }
}
