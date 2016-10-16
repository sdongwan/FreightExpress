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
import android.widget.LinearLayout;

import com.highspace.hs.MainActivity;
import com.highspace.hs.R;
import com.highspace.hs.adapter.GuideAdapter;
import com.highspace.hs.app.AppAplication;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager mViewPager;
    private GuideAdapter mGuideAdapter;
    private ArrayList<View> mViews;
    private Button mButton;

    private ImageView mGuide3iv;

    private LinearLayout mLinearLayout;

    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //AppAplication.getInstance().addActivity(this);

        initView();
        initEvent();
    }

    private void initEvent() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 3; i++) {
                    ImageView imageView = (ImageView) mLinearLayout.getChildAt(i);
                    imageView.setImageResource(i == position ? R.mipmap.icon_page_indicator_focused : R.mipmap.icon_page_indicator_unfocused);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViews = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.guide_activity_vp);
        mLinearLayout = (LinearLayout) findViewById(R.id.guide_activity_circle_ll);
/*
   LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.CENTER);
 */

        for (int j = 0; j < 3; j++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 0, 0, 0);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(j == 0 ? R.mipmap.icon_page_indicator_focused : R.mipmap.icon_page_indicator_unfocused);
            mLinearLayout.addView(imageView);
        }
        // mLinearLayout.addView(linearLayout);

        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_guide1);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_guide2);
        bitmap3 = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_guide3);

        ImageView imageView1 = new ImageView(this);
        imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView1.setImageBitmap(bitmap1);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mViews.add(imageView1);


        ImageView imageView2 = new ImageView(this);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView2.setImageBitmap(bitmap2);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mViews.add(imageView2);

        View view = LayoutInflater.from(this).inflate(R.layout.item_guide_activity_vp, null);
        mButton = (Button) view.findViewById(R.id.item_guide_activity_btn);
        mGuide3iv = (ImageView) view.findViewById(R.id.item_guide_activity_iv);
        mGuide3iv.setImageBitmap(bitmap3);
        mGuide3iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mViews.add(view);
        mGuideAdapter = new GuideAdapter(mViews);
        mViewPager.setAdapter(mGuideAdapter);


    }

/*
    private Bitmap adjustBitmap(Bitmap bitmap) {
        Bitmap target = Bitmap.createScaledBitmap(bitmap, MobleUtil.getScreenWith(this), MobleUtil.getScreenHeight(this), false);
        Canvas canvas = new Canvas(target);
        canvas.drawBitmap(bitmap, null, new Rect(0, 0, target.getWidth(), target.getHeight()), null);
        return target;
    }
    */

    @Override
    protected void onStop() {
        super.onStop();
        if (bitmap1 != null && !bitmap1.isRecycled()) {
            bitmap1.recycle();
            bitmap1 = null;
        }
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            bitmap2.recycle();
            bitmap2 = null;
        }
        if (bitmap3 == null && !bitmap3.isRecycled()) {
            bitmap3.recycle();
            bitmap3 = null;
        }
        System.gc();
    }
}
