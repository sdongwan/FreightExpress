package com.highspace.hs.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.highspace.hs.R;
import com.highspace.hs.MainActivity;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;


//扫码activity
public class SaoMaActivity extends FragmentActivity {

    private static final int REQUEST_PHOTO_IMAGE = 1667;
    private Button mOpenPhotobtn;
    private ImageView mCamerBackIV;
    private ImageView mCamerLightIV;
    private boolean isLightOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saoma);
        initViews();
        initEvents();


    }

    private void initEvents() {
        mCamerBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCamerLightIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLightOn) {
                    mCamerLightIV.setBackgroundResource(R.mipmap.icon_flashlight_on);
                    CodeUtils.isLightEnable(true);
                    isLightOn = true;

                } else {
                    mCamerLightIV.setBackgroundResource(R.mipmap.icon_flashlight_off);
                    CodeUtils.isLightEnable(false);
                    isLightOn = false;


                }
            }
        });


        mOpenPhotobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PHOTO_IMAGE);

            }
        });

        //setContentView(R.layout.activity_second);
        /**
         * 执行扫面Fragment的初始化操作
         */
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.activity_saoma_camera);


        /**
         * 二维码解析回调函数
         */
        CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                bundle.putString(CodeUtils.RESULT_STRING, result);
                resultIntent.putExtras(bundle);
                SaoMaActivity.this.setResult(RESULT_OK, resultIntent);
                SaoMaActivity.this.finish();
            }

            @Override
            public void onAnalyzeFailed() {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
                bundle.putString(CodeUtils.RESULT_STRING, "");
                resultIntent.putExtras(bundle);
                SaoMaActivity.this.setResult(RESULT_OK, resultIntent);
                SaoMaActivity.this.finish();
            }
        };

        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.saoma_activity_mycontainer_fl, captureFragment).commit();

    }

    private void initViews() {
        mOpenPhotobtn = (Button) findViewById(R.id.saoma_activity_openphoto_btn);
        mCamerBackIV = (ImageView) findViewById(R.id.saoma_activity_back_iv);
        mCamerLightIV = (ImageView) findViewById(R.id.saoma_activity_light_iv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (MainActivity.mHomeFragment != null) {
            MainActivity.mHomeFragment.onActivityResult(requestCode, resultCode, data);
            finish();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
      //  CodeUtils.isLightEnable(false);
       // isLightOn = false;
    }


}
