package com.highspace.hs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.highspace.hs.R;
import com.highspace.hs.adapter.HomeCarouselAdapter;
import com.highspace.hs.util.ImageLoadUtil;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;


//查看照片activity
public class ImageShowActivity extends Activity {
    private ViewPager mImgShowVp;
    private ImageView mBackImg;

    private PhotoViewAttacher mAttacher;

    private ArrayList<ImageView> mImgslist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initViews();
        initEvents();
    }

    private void initEvents() {
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initViews() {
        mImgShowVp = (ViewPager) findViewById(R.id.image_activity_vp);
        mBackImg = (ImageView) findViewById(R.id.image_activity_back_iv);

        mImgslist = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ImageLoadUtil.getImageLoader(this).displayImage("http://www.sdongwan.top/images/a.png", imageView);
            mAttacher = new PhotoViewAttacher(imageView);
            mImgslist.add(imageView);
        }

        mImgShowVp.setAdapter(new HomeCarouselAdapter(mImgslist));// TODO: 2016/9/27
    }


}
