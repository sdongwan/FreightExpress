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

    private String pageNames[] = new String[]{"货主登陆", "车主登陆"};


    private Button mMiMaLoginBtn;
    private Button mMessageLoginBtn;


   // private Context context;


    private ArrayList<View> mPageViews;

    private LayoutInflater mLayoutInflater;




    private OnMiMaLoginBtnOnClickListener onMiMaLoginBtnOnClickListener;
    private OnMessageLoginBtnOnClickListener onMessageLoginBtnOnClickListener;

    public void setOnMessageLoginBtnOnClickListener(OnMessageLoginBtnOnClickListener onMessageLoginBtnOnClickListener) {
        this.onMessageLoginBtnOnClickListener = onMessageLoginBtnOnClickListener;
    }

    public void setOnMiMaLoginBtnOnClickListener(OnMiMaLoginBtnOnClickListener onMiMaLoginBtnOnClickListener) {
        this.onMiMaLoginBtnOnClickListener = onMiMaLoginBtnOnClickListener;
    }

    public UserLoginAdapter(Context context) {
        //this.context = context;
        mPageViews = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
        initContent();

    }


    private void initContent() {
        View mimaView = mLayoutInflater.inflate(R.layout.activity_login_mima, null);
        View yanzhengmaView = mLayoutInflater.inflate(R.layout.activity_login_message, null);

        mMiMaLoginBtn = (Button) mimaView.findViewById(R.id.login_mima_loginbtn);
        mMessageLoginBtn = (Button) yanzhengmaView.findViewById(R.id.login_message_login_btn);


        mPageViews.add(mimaView);
        //mPageViews.add(mimaView);
        mPageViews.add(yanzhengmaView);


        mMiMaLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMiMaLoginBtnOnClickListener != null) {

                    onMiMaLoginBtnOnClickListener.miMaLoginBtnOnClick(v);
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

    public interface OnMiMaLoginBtnOnClickListener {

        public void miMaLoginBtnOnClick(View view);

    }

    public interface OnMessageLoginBtnOnClickListener {

        public void messageloginBtnOnClick(View view);
    }
}
