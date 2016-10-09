package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.highspace.hs.MainActivity;
import com.highspace.hs.R;
import com.highspace.hs.adapter.UserLoginAdapter;

public class UserLoginActivity extends Activity {

    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;

    private Button mMiMaLoginBtn;
    private Button mMessageLoginBtn;


    private View mMiMaLayoutView;
    private View mMessageLayoutView;

    private UserLoginAdapter mUserLoginAdapter;


    private ImageView mLoginBackImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initEvents();

    }

    private void initEvents() {


        mLoginBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent();
                intent.putExtra("flag", true);
                intent.putExtra("loginName", "AS");
                setResult(RESULT_OK, intent);
                */
                finish();


            }
        });


        mUserLoginAdapter.setOnMessageLoginBtnOnClickListener(new UserLoginAdapter.OnMessageLoginBtnOnClickListener() {
            @Override
            public void messageloginBtnOnClick(View view) {
                finish();
                startActivity(new Intent(UserLoginActivity.this, MainActivity.class));
                // TODO: 2016/9/26  

            }
        });

        mUserLoginAdapter.setOnMiMaLoginBtnOnClickListener(new UserLoginAdapter.OnMiMaLoginBtnOnClickListener() {
            @Override
            public void miMaLoginBtnOnClick(View view) {
                finish();
                startActivity(new Intent(UserLoginActivity.this, MainActivity.class));
                // TODO: 2016/9/26  

            }
        });


    }

    private void initViews() {
        mLoginBackImageView = (ImageView) findViewById(R.id.login_activity_back_iv);

        mMiMaLayoutView = LayoutInflater.from(this).inflate(R.layout.activity_login_mima, null);
        mMiMaLoginBtn = (Button) mMiMaLayoutView.findViewById(R.id.login_mima_loginbtn);

        mMessageLayoutView = LayoutInflater.from(this).inflate(R.layout.activity_login_message, null);
        mMessageLoginBtn = (Button) mMessageLayoutView.findViewById(R.id.login_message_login_btn);

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.login_activity_tabs);
        mViewPager = (ViewPager) findViewById(R.id.login_activity_vp);
        mUserLoginAdapter = new UserLoginAdapter(this);

        mViewPager.setAdapter(mUserLoginAdapter);
        mPagerSlidingTabStrip.setViewPager(mViewPager);


    }
}
