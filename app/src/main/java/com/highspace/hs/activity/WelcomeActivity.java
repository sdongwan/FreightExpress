package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.highspace.hs.R;




public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
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
