package com.highspace.hs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.highspace.hs.R;

public class MyCommentActivity extends Activity {

    private ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        initView();
        initEvent();
    }

    private void initEvent() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mBackIv = (ImageView) findViewById(R.id.my_comment_back_iv);
    }



}
