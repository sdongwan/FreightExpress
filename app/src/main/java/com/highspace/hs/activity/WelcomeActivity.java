package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
<<<<<<< HEAD
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.highspace.hs.R;
import com.highspace.hs.util.AnimationUtil;


public class WelcomeActivity extends Activity {
private View mView;
=======

import com.highspace.hs.R;




public class WelcomeActivity extends Activity {

>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
<<<<<<< HEAD
        mView=findViewById(R.id.weclome_activity_black_view);
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.black_view_phla_anim);
        mView.startAnimation(animation);

=======
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
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
}
