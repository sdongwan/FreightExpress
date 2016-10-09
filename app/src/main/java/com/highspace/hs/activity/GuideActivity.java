package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.highspace.hs.MainActivity;
import com.highspace.hs.adapter.GuideAdapter;

import java.util.ArrayList;

import com.highspace.hs.R;

public class GuideActivity extends Activity {
    private ViewPager mViewPager;
    private GuideAdapter mGuideAdapter;
    private ArrayList<View> mViews;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initEvent();
    }

    private void initEvent() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
            }
        });
    }

    private void initView() {
        mViews = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.guide_activity_vp);


        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_guide1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_guide2);

        ImageView imageView1 = new ImageView(this);
        imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView1.setImageBitmap(bitmap1);
        mViews.add(imageView1);

        ImageView imageView2 = new ImageView(this);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView2.setImageBitmap(bitmap2);
        mViews.add(imageView2);

        View view = LayoutInflater.from(this).inflate(R.layout.item_guide_activity_vp, null);
        mButton = (Button) view.findViewById(R.id.item_guide_activity_btn);
        mViews.add(view);
        mGuideAdapter = new GuideAdapter(mViews);
        mViewPager.setAdapter(mGuideAdapter);

    }
}
