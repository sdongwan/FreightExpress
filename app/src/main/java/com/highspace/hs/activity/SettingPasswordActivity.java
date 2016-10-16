package com.highspace.hs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.highspace.hs.R;

public class SettingPasswordActivity extends Activity {

    private EditText mOldPassEt;
    private EditText mNewPassEt;
    private EditText mRepearPassEt;

    private Button mCommitBtn;

    private ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_password);
        initView();
        initEvent();
    }

    private void initEvent() {
        mCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mOldPassEt = (EditText) findViewById(R.id.setting_password_oldpassword_et);
        mNewPassEt = (EditText) findViewById(R.id.setting_password_newpassword_et);
        mRepearPassEt = (EditText) findViewById(R.id.setting_password_repeatpassword_et);
        mBackIv = (ImageView) findViewById(R.id.setting_password_back_iv);
        mCommitBtn = (Button) findViewById(R.id.setting_password_commit_btn);
    }

}
