package com.highspace.hs.util;

import android.content.Context;
import android.graphics.Bitmap;


import com.highspace.hs.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/9/27.
 */

public class ImageLoadUtil {
    private static ImageLoader sImageLoader;

    private ImageLoadUtil() {


    }

    public static ImageLoader getImageLoader(Context context) {
        if (sImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (sImageLoader == null) {
                    sImageLoader = ImageLoader.getInstance();
                    sImageLoader.init(initImageLoader(context));
                }
            }
        }

        return sImageLoader;
    }


    public static ImageLoaderConfiguration initImageLoader(Context context) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher) // resource or drawable
                //.resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(0)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                // .imageScaleType(IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                //.displayer(new SimpleBitmapDisplayer()) // default
                //.handler(new Handler()) // default
                .build();


        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                //.memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                //.diskCacheExtraOptions(480, 800, null)
                //.threadPriority(Thread.NORM_PRIORITY - 2) // default
                //.tasksProcessingOrder(QueueProcessingType.FIFO) // default
                //.denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                //.diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                //.diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                //.imageDownloader(new BaseImageDownloader(context)) // default
                //.defaultDisplayImageOptions(options) // default
                //.writeDebugLogs()
                .build();

        return config;
    }


/*
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(res, resId, options) ;
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.de(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
*/
}
