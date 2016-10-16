package com.highspace.hs.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.highspace.hs.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/23.
 * 登陆界面适配器
 */


public class UserLoginAdapter extends PagerAdapter {

    private String pageNames[] = new String[]{"密码登陆", "短信登陆"};


    private Button mPasswordLoginBtn;
    private Button mMessageLoginBtn;

    private Button mMessageRegisterBtn;
    private Button mPasswordRegisterBtn;


    // private Context context;


    private ArrayList<View> mPageViews;

    private LayoutInflater mLayoutInflater;


    private OnPasswordLoginBtnOnClickListener onPasswordLoginBtnOnClickListener;
    private OnMessageLoginBtnOnClickListener onMessageLoginBtnOnClickListener;

    private OnMessageRegisterBtnOnClickListener onMessageRegisterBtnOnClickListener;
    private OnPasswordRegisterBtnOnClickListener onPasswordRegisterBtnOnClickListener;

    public void setOnMessageLoginBtnOnClickListener(OnMessageLoginBtnOnClickListener onMessageLoginBtnOnClickListener) {
        this.onMessageLoginBtnOnClickListener = onMessageLoginBtnOnClickListener;
    }

    public void setOnPasswordLoginBtnOnClickListener(OnPasswordLoginBtnOnClickListener onPasswordLoginBtnOnClickListener) {
        this.onPasswordLoginBtnOnClickListener = onPasswordLoginBtnOnClickListener;
    }

    public void setOnPasswordRegisterBtnOnClickListener(OnPasswordRegisterBtnOnClickListener onPasswordRegisterBtnOnClickListener) {
        this.onPasswordRegisterBtnOnClickListener = onPasswordRegisterBtnOnClickListener;
    }

    public void setOnMessageRegisterBtnOnClickListener(OnMessageRegisterBtnOnClickListener onMessageRegisterBtnOnClickListener) {
        this.onMessageRegisterBtnOnClickListener = onMessageRegisterBtnOnClickListener;
    }

    public UserLoginAdapter(Context context) {
        //this.context = context;
        mPageViews = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
        initContent();

    }


    private void initContent() {
        View passwordView = mLayoutInflater.inflate(R.layout.activity_login_mima, null);
        View messageView = mLayoutInflater.inflate(R.layout.activity_login_message, null);

        mPasswordLoginBtn = (Button) passwordView.findViewById(R.id.login_mima_loginbtn);
        mMessageLoginBtn = (Button) messageView.findViewById(R.id.login_message_login_btn);

        mMessageRegisterBtn = (Button) messageView.findViewById(R.id.login_message_register_btn);
        mPasswordRegisterBtn = (Button) passwordView.findViewById(R.id.login_mima_register_btn);


        mPageViews.add(passwordView);
        //mPageViews.add(passwordView);
        mPageViews.add(messageView);


        mPasswordLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPasswordLoginBtnOnClickListener != null) {

                    onPasswordLoginBtnOnClickListener.passwordLoginBtnOnClick(v);
                }

                // context.startActivity(new Intent(context, MainActivity.class));


                // TODO: 2016/9/26

            }
        });

        mMessageLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMessageLoginBtnOnClickListener != null) {
                    onMessageLoginBtnOnClickListener.messageloginBtnOnClick(v);
                }

                // context.startActivity(new Intent(context, MainActivity.class));

                // finish();
                // TODO: 2016/9/26
            }
        });

        mPasswordRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPasswordRegisterBtnOnClickListener != null) {
                    onPasswordRegisterBtnOnClickListener.passwordRegisterBtnOnclick(v);
                }
            }
        });

        mMessageRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMessageRegisterBtnOnClickListener != null) {
                    onMessageRegisterBtnOnClickListener.messageRegisterBtnOnclick(v);
                }
            }
        });
    }

    @Override

    public int getCount() {
        return pageNames.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageNames[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mPageViews.get(position));
        return mPageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mPageViews.get(position));
    }

    public interface OnPasswordLoginBtnOnClickListener {

        public void passwordLoginBtnOnClick(View view);

    }

    public interface OnPasswordRegisterBtnOnClickListener {
        public void passwordRegisterBtnOnclick(View view);
    }

    public interface OnMessageLoginBtnOnClickListener {

        public void messageloginBtnOnClick(View view);
    }

    public interface OnMessageRegisterBtnOnClickListener {
        public void messageRegisterBtnOnclick(View view);
    }


}
