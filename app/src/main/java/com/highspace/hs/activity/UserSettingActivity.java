package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.highspace.hs.MainActivity;
import com.highspace.hs.R;
import com.highspace.hs.util.SharedPreferencesUtil;
import com.soundcloud.android.crop.Crop;

public class UserSettingActivity extends Activity {

    private RelativeLayout mSettingMiMaRL;
    private RelativeLayout mSettingTouxiangRL;
    private RelativeLayout mSettingClearRL;
    private RelativeLayout mSettingUpdateRL;
    private RelativeLayout mSettingQrcodeRL;

    private Button mExitBtn;

    private ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
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

        mSettingClearRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSettingMiMaRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserSettingActivity.this, SettingPasswordActivity.class));
            }
        });
        mSettingTouxiangRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crop.pickImage(UserSettingActivity.this);
            }
        });
        mSettingUpdateRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/10/12
//SharedPreferencesUtil.saveBooleanData("loginFlag", true, "isLogin", getActivity());
                SharedPreferencesUtil.saveBooleanData("loginFlag", false, "isLogin", UserSettingActivity.this);
                finish();

            }
        });

        mSettingQrcodeRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserSettingActivity.this, QrcodeAcivity.class));
            }
        });
    }

    private void initView() {
        mSettingMiMaRL = (RelativeLayout) findViewById(R.id.user_setting_password_rl);
        mSettingClearRL = (RelativeLayout) findViewById(R.id.user_setting_clear_rl);
        mSettingTouxiangRL = (RelativeLayout) findViewById(R.id.user_setting_touxiang_rl);
        mSettingUpdateRL = (RelativeLayout) findViewById(R.id.user_setting_update_rl);
        mSettingQrcodeRL = (RelativeLayout) findViewById(R.id.user_setting_qrcode_rl);
        mExitBtn = (Button) findViewById(R.id.user_setting_exit_btn);
        mBackIv = (ImageView) findViewById(R.id.user_setting_back_iv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        MainActivity.pMyFragment.onActivityResult(requestCode, resultCode, data);

    }
}
