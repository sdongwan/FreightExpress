package com.highspace.hs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.highspace.hs.R;


//我的财务activity
public class MyCaiWuActivity extends Activity {

    private ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_caiwu);
        initViews();

        initEvents();


    }

    private void initEvents() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initViews() {
        mBackIv = (ImageView) findViewById(R.id.my_caiwu_back_iv);


    }
}
