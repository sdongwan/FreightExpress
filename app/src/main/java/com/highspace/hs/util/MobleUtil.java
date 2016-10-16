package com.highspace.hs.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/9/28.
 */

public class MobleUtil {
    /*
        public static int getScreenWith(Context context) {

            WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics outMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(outMetrics);
            int screenWidth = outMetrics.widthPixels;

            return screenWidth;

        }
        */
    public static int getScreenWith(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = activity.getWindowManager();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(dm);
        }


        //int screenHeight = dm.heightPixels;
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = activity.getWindowManager();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        return dm.heightPixels;
    }


}
