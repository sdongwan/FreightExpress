package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.highspace.hs.R;


public class WelcomeActivity extends Activity {
    private View mView;
    private ImageView mImageView;
    private Bitmap target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //AppAplication.getInstance().addActivity(this);


        mView = findViewById(R.id.weclome_activity_black_view);
        mImageView = (ImageView) findViewById(R.id.welcome_activity_img_iv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_welcome);
        // target = adjustBitmap();
        mImageView.setImageBitmap(bitmap);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.black_view_phla_anim);
        mView.startAnimation(animation);


        Runnable r = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, GuideActivity.class);
                startActivity(intent);
                finish();
            }
        };
        new Handler().postDelayed(r, 3000);// 1秒后关闭，并跳转到主页面
    }
/*
    @NonNull
    private Bitmap adjustBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_welcome);
        Bitmap target = Bitmap.createScaledBitmap(bitmap, MobleUtil.getScreenWith(this), MobleUtil.getScreenHeight(this), true);
        Canvas canvas = new Canvas(target);
        canvas.drawBitmap(bitmap, null, new Rect(0, 0, target.getWidth(), target.getHeight()), null);
        return target;
    }
    */

    @Override
    protected void onStop() {
        super.onStop();
        if (target != null && !target.isRecycled()) {
            target.recycle();
            target = null;
        }
        System.gc();

    }
}
