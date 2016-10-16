package com.highspace.hs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.util.SharedPreferencesUtil;

public class RegisterActivity extends Activity {
    private EditText mPhoneNumEt;
    private EditText mPassWordEt;
    private EditText mConfirmEt;
    private Button mCommitBtn;
    private ImageView mBackIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPassWordEt = (EditText) findViewById(R.id.register_password_et);
        mPhoneNumEt = (EditText) findViewById(R.id.register_phonenum_et);
        mConfirmEt = (EditText) findViewById(R.id.register_comfirm_password_et);
        mCommitBtn = (Button) findViewById(R.id.register_commit_btn);
        mBackIV = (ImageView) findViewById(R.id.register_activity_back_iv);
        mBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = mPhoneNumEt.getText().toString();
                String password = mPassWordEt.getText().toString();
                if (phoneNum == "" || password == "") {
                    Toast.makeText(RegisterActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferencesUtil.saveStringData("userInfo", phoneNum, "phoneNum", RegisterActivity.this);
                SharedPreferencesUtil.saveStringData("userInfo", password, "password", RegisterActivity.this);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
