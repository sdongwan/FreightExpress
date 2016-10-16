package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.highspace.hs.MainActivity;
import com.highspace.hs.R;
import com.highspace.hs.adapter.UserLoginAdapter;
import com.highspace.hs.util.SharedPreferencesUtil;

public class UserLoginActivity extends Activity {

    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;

    private Button mMiMaLoginBtn;
    private Button mMessageLoginBtn;

    private View mMiMaLayoutView;
    private View mMessageLayoutView;

    private EditText mPasswordPhoneNumEt;
    private EditText mPasswordPasswordEt;

    private UserLoginAdapter mUserLoginAdapter;


    private ImageView mLoginBackImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();

    }

    private void initEvent() {


        mLoginBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });


        mUserLoginAdapter.setOnMessageLoginBtnOnClickListener(new UserLoginAdapter.OnMessageLoginBtnOnClickListener() {
            @Override
            public void messageloginBtnOnClick(View view) {

                startActivity(new Intent(UserLoginActivity.this, MainActivity.class));
                // TODO: 2016/9/26
                finish();

            }
        });
        mUserLoginAdapter.setOnMessageRegisterBtnOnClickListener(new UserLoginAdapter.OnMessageRegisterBtnOnClickListener() {
            @Override
            public void messageRegisterBtnOnclick(View view) {
                // TODO: 2016/10/13
            }
        });

        mUserLoginAdapter.setOnPasswordLoginBtnOnClickListener(new UserLoginAdapter.OnPasswordLoginBtnOnClickListener() {
            @Override
            public void passwordLoginBtnOnClick(View view) {

                //startActivity(new Intent(UserLoginActivity.this, MainActivity.class));
                // TODO: 2016/9/26
                String phoneNum = mPasswordPhoneNumEt.getText().toString();
                String password = mPasswordPasswordEt.getText().toString();
                if (phoneNum == "" || password == "") {
                    Toast.makeText(UserLoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    //return;
                } else {

                    //  SharedPreferencesUtil.saveStringData("userInfo", phoneNum, "phoneNum", RegisterActivity.this);
                    //SharedPreferencesUtil.saveStringData("userInfo", password, "password", RegisterActivity.this);
                    //phoneNum= SharedPreferencesUtil.getStringData("userInfo","")
                    // if (phoneNum.equals("10086") && password.equals("huoyunkuaixian")) {
                    Intent data = new Intent();
                    data.putExtra("phoneNum", phoneNum);
                    data.putExtra("password", password);

                    SharedPreferencesUtil.saveStringData("loginInfo", phoneNum, "phoneNum", UserLoginActivity.this);
                    SharedPreferencesUtil.saveStringData("loginInfo", password, "password", UserLoginActivity.this);

                    UserLoginActivity.this.setResult(RESULT_OK, data);
                     UserLoginActivity.this.finish();
                    //finishActivity(8888);


                    // }else{
                    //Toast.makeText(UserLoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();

                    // }


                }


            }
        });
        mUserLoginAdapter.setOnPasswordRegisterBtnOnClickListener(new UserLoginAdapter.OnPasswordRegisterBtnOnClickListener() {
            @Override
            public void passwordRegisterBtnOnclick(View view) {
                // TODO: 2016/10/13
                startActivity(new Intent(UserLoginActivity.this, RegisterActivity.class));

            }
        });


    }

    private void initView() {
        mLoginBackImageView = (ImageView) findViewById(R.id.login_activity_back_iv);

        mMiMaLayoutView = LayoutInflater.from(this).inflate(R.layout.activity_login_mima, null);
        mMiMaLoginBtn = (Button) mMiMaLayoutView.findViewById(R.id.login_mima_loginbtn);
        mPasswordPhoneNumEt = (EditText) mMiMaLayoutView.findViewById(R.id.login_mima_phonenumber_et);
        mPasswordPasswordEt = (EditText) mMiMaLayoutView.findViewById(R.id.login_mima_mima_et);

        mMessageLayoutView = LayoutInflater.from(this).inflate(R.layout.activity_login_message, null);
        mMessageLoginBtn = (Button) mMessageLayoutView.findViewById(R.id.login_message_login_btn);

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.login_activity_tabs);
        mViewPager = (ViewPager) findViewById(R.id.login_activity_vp);
        mUserLoginAdapter = new UserLoginAdapter(this);

        mViewPager.setAdapter(mUserLoginAdapter);
        mPagerSlidingTabStrip.setViewPager(mViewPager);


    }
}
