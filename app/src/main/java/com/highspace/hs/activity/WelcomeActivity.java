package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.highspace.hs.R;
import com.highspace.hs.util.AnimationUtil;


public class WelcomeActivity extends Activity {
private View mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mView=findViewById(R.id.weclome_activity_black_view);
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.black_view_phla_anim);
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
}
