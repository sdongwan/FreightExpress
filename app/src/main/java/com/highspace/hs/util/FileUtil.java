package com.highspace.hs.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by nicholas on 1/18/15.
 * 获取文件路径
 */
public class FileUtil {

    Uri contentUri;

    static boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

    public FileUtil() {

    }

    public void setContentUri(Uri uri) {
        this.contentUri = uri;
    }

    public Uri getContentUri() {
        return contentUri;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(Context context, Uri uri) {
        Log.v(" File -",
                "Authority: " + uri.getAuthority() +
                        ", Fragment: " + uri.getFragment() +
                        ", Port: " + uri.getPort() +
                        ", Query: " + uri.getQuery() +
                        ", Scheme: " + uri.getScheme() +
                        ", Host: " + uri.getHost() +
                        ", Segments: " + uri.getPathSegments().toString()
        );

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            return getRealPathFromUriKitKatPlus(context, uri);
        } else {
            return getRealPathFromUriMinusKitKat(context, uri);
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String getRealPathFromUriKitKatPlus(Context context, Uri uri) {
        Cursor cursor = null;
        String wholeId = DocumentsContract.getDocumentId(uri);
        String id = wholeId.split(":")[1];

        try {
            String proj[] = {MediaStore.Images.Media.DATA};
            String sel = MediaStore.Images.Media._ID + "=?";
            cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    proj, sel, new String[]{id}, null);
            int column_index = cursor.getColumnIndexOrThrow(proj[0]);
            String filePath = "";
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(column_index);
            }
            cursor.close();
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    private static String getRealPathFromUriMinusKitKat(Context context, Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        System.out.println(picturePath);
        return picturePath;
    }

    public static void saveBitmapExtenalPath(Bitmap bitmap, String fileName) {


        String path = Environment.getExternalStorageDirectory().toString();
        Log.e("lujing", path);

        //ff.png是将要存储的图片的名称
        File file = new File(path, fileName);

        //从资源文件中选择一张图片作为将要写入的源文件
        // Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ss);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static void saveBitmapIntenalCatchPath(Bitmap bitmap, String fileName) {

        //得到外部存储卡的路径
        String path = Environment.getDownloadCacheDirectory().toString();
        Log.e("lujing", path);

        //ff.png是将要存储的图片的名称
        File file = new File(path, fileName);

        //从资源文件中选择一张图片作为将要写入的源文件
        // Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ss);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static Bitmap getBitmapFromIntenerPath(String fileName) {

        String path = Environment.getExternalStorageDirectory().toString() + fileName;
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }


}