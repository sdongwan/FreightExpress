package com.highspace.hs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.highspace.hs.R;
import com.highspace.hs.adapter.UserAdressAdapter;

//我的地址activity
public class MyAddressActivity extends Activity {

    private ViewPager mMyAddressVp;
    private ImageView mBackIv;

    private PagerSlidingTabStrip mMyAddressPagerTabStrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        initViews();
        initEvents();
    }

    private void initEvents() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initViews() {
        mMyAddressPagerTabStrip = (PagerSlidingTabStrip) findViewById(R.id.my_address_tabs);
        mMyAddressVp = (ViewPager) findViewById(R.id.my_address_vp);
        mMyAddressVp.setAdapter(new UserAdressAdapter(this));
        mMyAddressPagerTabStrip.setViewPager(mMyAddressVp);
        mBackIv = (ImageView) findViewById(R.id.my_address_back_iv);

    }
}
