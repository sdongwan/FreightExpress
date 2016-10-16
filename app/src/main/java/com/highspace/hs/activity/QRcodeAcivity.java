package com.highspace.hs.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.util.FileUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class QrcodeAcivity extends Activity {
    private ImageView mBackIv;
    private ImageView mDownloadIv;
    private ImageView mQrcodeIv;
    private Bitmap mBitmap;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_acivity);
        initView();
        initEvent();
    }

    private void initEvent() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    Toast.makeText(QrcodeAcivity.this, "图片下载成功", Toast.LENGTH_SHORT).show();
                }

            }
        };

        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mDownloadIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/10/12
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        FileUtil.saveBitmapExtenalPath(mBitmap, "qrcode.png");
                        mHandler.sendEmptyMessage(0);
                    }
                });

            }
        });


    }

    private void initView() {
        mBackIv = (ImageView) findViewById(R.id.qrcode_back_iv);
        mDownloadIv = (ImageView) findViewById(R.id.qrcode_download_iv);
        mQrcodeIv = (ImageView) findViewById(R.id.qrcode_qrcode_iv);

        mBitmap = CodeUtils.createImage("货运快线", 250, 250, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        mQrcodeIv.setImageBitmap(mBitmap);


    }


}
