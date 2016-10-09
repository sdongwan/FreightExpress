package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.adapter.WuLiuAdapter;
import com.highspace.hs.util.DensityUtil;
import com.highspace.hs.util.ImageLoadUtil;
import com.highspace.hs.util.MobleUtil;
import com.highspace.hs.view.GradationScrollView;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;


//物流详情activity
public class WuLiuContentActivity extends Activity implements GradationScrollView.ScrollViewListener {

    private ImageView mWuliuBigImageView;
    private ImageView mChakantupianImageView;

    private ImageView mCollectImageView;

    private ImageView mBackIv;
    private RelativeLayout mBigImageRL;

    private ListView mWuliucontentLv;

    private ArrayList<String> mDatas;


    private RelativeLayout mWuliucontentTitleBar;


    private RelativeLayout mWuliubigImageRl;

    private GradationScrollView mScrollView;

    private static int sHeight = 0;

    private LinearLayout mContactCompanyLl;

    private LinearLayout mXiadanLl;

    private TextView mXiadanTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuliu_content);
        initView();
        initEvent();


    }

    private void initEvent() {
/*
        mWuliuBigImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WuLiuContentActivity.this, "image", Toast.LENGTH_SHORT).show();
            }
        });
        */
        mXiadanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WuLiuContentActivity.this, ReleaseGoodsActivity.class));
            }

        });
        mScrollView.smoothScrollBy(0, 20);

        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mChakantupianImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(WuLiuContentActivity.this, "chakanimage", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WuLiuContentActivity.this, ImageShowActivity.class));
            }
        });


        mCollectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WuLiuContentActivity.this, "添加收藏", Toast.LENGTH_SHORT).show();
            }
        });


        mWuliucontentLv.setAdapter(new WuLiuAdapter(this, mDatas));
/*
        mBigImageRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WuLiuContentActivity.this, ImageShowActivity.class));
            }
        });
*/

        mContactCompanyLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WuLiuContentActivity.this, "dianhua", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 10086));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        mXiadanLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WuLiuContentActivity.this, "xiadan", Toast.LENGTH_SHORT).show();
            }
        });
        ImageLoadUtil.getImageLoader(this).loadImage("http://www.sdongwan.top/images/a.png", new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                Bitmap target = Bitmap.createBitmap(MobleUtil.getScreenWith(WuLiuContentActivity.this), DensityUtil.dp2px(WuLiuContentActivity.this, 260), loadedImage.getConfig());
                Canvas canvas = new Canvas(target);
                canvas.drawBitmap(loadedImage, null, new Rect(0, 0, target.getWidth(), target.getHeight()), null);
                mWuliuBigImageView.setImageBitmap(target);

            }
        });
        // TODO: 2016/9/27 图片
    }

    private void initView() {
        mXiadanTv = (TextView) findViewById(R.id.wuliu_content_xiadan);

        mWuliuBigImageView = (ImageView) findViewById(R.id.wuliu_big_imageview);
        mBackIv = (ImageView) findViewById(R.id.wuliucontentactivity_back);
        mCollectImageView = (ImageView) findViewById(R.id.wuliucontentactivity_collect);

        mXiadanLl = (LinearLayout) findViewById(R.id.xiadan_ll);
        mContactCompanyLl = (LinearLayout) findViewById(R.id.contactcompany_ll);

        mScrollView = (GradationScrollView) findViewById(R.id.scrollView);
        mWuliubigImageRl = (RelativeLayout) findViewById(R.id.wuliucontentactivity_big_imag_rl);
        mWuliucontentTitleBar = (RelativeLayout) findViewById(R.id.wuliucontent_titlebar_rl);
        mWuliucontentLv = (ListView) findViewById(R.id.wuliucontent_listview);

        mWuliuBigImageView = (ImageView) findViewById(R.id.wuliu_big_imageview);
        mChakantupianImageView = (ImageView) findViewById(R.id.wuliucontentactivity_lookimg);
        mBigImageRL = (RelativeLayout) findViewById(R.id.wuliucontentactivity_big_imag_rl);


        mDatas = new ArrayList<>();
        mDatas.add("A");
        mDatas.add("B");
        mDatas.add("C");
        mDatas.add("CD");
        mDatas.add("E");
        initListeners();
    }

    private void initListeners() {

        ViewTreeObserver vto = mWuliubigImageRl.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                sHeight = mWuliubigImageRl.getHeight();
                //sHeight = 10;


                mScrollView.setmScrollViewListener(WuLiuContentActivity.this);
            }
        });
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            mWuliucontentTitleBar.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));
        } else if (y > 0 && y <= sHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / sHeight;
            float alpha = (255 * scale);
            // textView.setTextColor(Color.argb((int) alpha, 255,255,255));
            mWuliucontentTitleBar.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
        } else {    //滑动到banner下面设置普通颜色
            mWuliucontentTitleBar.setBackgroundColor(Color.argb((int) 255, 0, 0, 0));
        }
    }
}
