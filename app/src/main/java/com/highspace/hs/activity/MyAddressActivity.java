package com.highspace.hs.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.highspace.hs.R;
import com.highspace.hs.adapter.UserAdressAdapter;
import com.highspace.hs.fragment.FirstPagerFragment;
import com.highspace.hs.fragment.SecondPagerFragment;

import java.util.ArrayList;
import java.util.List;

//我的地址activity
public class MyAddressActivity extends FragmentActivity {

    private ViewPager mMyAddressVp;
    private ImageView mBackIv;

    private PagerSlidingTabStrip mMyAddressPagerTabStrip;
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    private FragmentManager mFragmentManager = getSupportFragmentManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        // manager = getFragmentManager();
        initFragmentList();
        initViews();
        initEvents();
    }
    private void initFragmentList() {
        Fragment firstFragment = new FirstPagerFragment();
        Fragment secondFragment = new SecondPagerFragment();
        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
    }

    private void initEvents() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mMyAddressVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mMyAddressVp.setCurrentItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initViews() {
        mMyAddressPagerTabStrip = (PagerSlidingTabStrip) findViewById(R.id.my_address_tabs);
        mMyAddressVp = (ViewPager) findViewById(R.id.my_address_vp);
        UserAdressAdapter adressAdapter = new UserAdressAdapter(mFragmentManager, fragmentList);
        mMyAddressVp.setAdapter(adressAdapter);
        mMyAddressVp.setCurrentItem(0);
        mMyAddressPagerTabStrip.setViewPager(mMyAddressVp);
        mBackIv = (ImageView) findViewById(R.id.my_address_back_iv);

    }
}
