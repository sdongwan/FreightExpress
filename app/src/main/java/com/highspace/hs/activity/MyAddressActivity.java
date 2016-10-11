package com.highspace.hs.activity;

import android.app.Activity;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.highspace.hs.R;
import com.highspace.hs.adapter.UserAdressAdapter;
<<<<<<< HEAD

//我的地址activity
public class MyAddressActivity extends Activity {
=======
import com.highspace.hs.fragment.FirstPagerFragment;
import com.highspace.hs.fragment.SecondPagerFragment;

import java.util.ArrayList;
import java.util.List;

//我的地址activity
public class MyAddressActivity extends FragmentActivity {
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721

    private ViewPager mMyAddressVp;
    private ImageView mBackIv;

    private PagerSlidingTabStrip mMyAddressPagerTabStrip;
<<<<<<< HEAD
=======
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    private FragmentManager mFragmentManager = getSupportFragmentManager();

>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
<<<<<<< HEAD
=======
       // manager = getFragmentManager();
        initFragmentList();
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
        initViews();
        initEvents();
    }

<<<<<<< HEAD
=======
    private void initFragmentList() {
        Fragment firstFragment = new FirstPagerFragment();
        Fragment secondFragment = new SecondPagerFragment();
        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
    }

>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
    private void initEvents() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

<<<<<<< HEAD
=======
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

>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
    }

    private void initViews() {
        mMyAddressPagerTabStrip = (PagerSlidingTabStrip) findViewById(R.id.my_address_tabs);
        mMyAddressVp = (ViewPager) findViewById(R.id.my_address_vp);
<<<<<<< HEAD
        mMyAddressVp.setAdapter(new UserAdressAdapter(this));
=======
        UserAdressAdapter adressAdapter = new UserAdressAdapter(mFragmentManager, fragmentList);
        mMyAddressVp.setAdapter(adressAdapter);
        mMyAddressVp.setCurrentItem(0);
>>>>>>> 433f09f3fb0000d00d2aef21fb4f6e8c07784721
        mMyAddressPagerTabStrip.setViewPager(mMyAddressVp);
        mBackIv = (ImageView) findViewById(R.id.my_address_back_iv);

    }
}
