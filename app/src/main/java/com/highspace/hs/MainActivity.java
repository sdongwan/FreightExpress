package com.highspace.hs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.highspace.hs.fragment.HomeFragment;
import com.highspace.hs.fragment.MyFragment;
import com.highspace.hs.fragment.WuliuFragment;
import com.highspace.hs.util.DoubleClickExitHelper;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioButton mHomeTabRbt;//
    private RadioButton mMyTabRbt;
    private RadioButton mWuliuTabRbt;
    private RadioButton mWaybillTabRbt;
    private RadioGroup mTabBar;

    private ArrayList<Fragment> mTabFragmentsList;
    private ArrayList<RadioButton> mTabRbtsList;

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private FragmentTransaction mFragmentTransaction;



    private WuliuFragment mWuliuFragment;


    public static MyFragment pMyFragment;
    public static HomeFragment pHomeFragment;
    //  private WayBillFragment wayBillFragment;

    public static Context mContext;


    // public static Uri pUri;


    // private Handler handler;

    DoubleClickExitHelper mDoubleClick = new DoubleClickExitHelper(this);
    //  private ViewPager mTabVP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initViews();
        initEvents();


    }

    private void initEvents() {

        mTabBar.setOnCheckedChangeListener(this);

/*
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                //Bundle bundle = new Bundle();
                //bundle.putParcelable("pUri", Crop.getOutput(result));
                //pMyFragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.main_framelayout, pMyFragment);
                mFragmentTransaction.commit();
            }
        };
        */

/*
        mTabVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // mTabRbtsList.get(position).setChecked(false);
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mTabRbtsList.size(); i++) {
                    mTabRbtsList.get(position).setChecked(false);
                }

                mTabRbtsList.get(position).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });*/
    }

    private void initViews() {
        mTabRbtsList = new ArrayList<>();
        mTabFragmentsList = new ArrayList<>();

        mTabBar = (RadioGroup) findViewById(R.id.tab_bar);
        // mTabVP = (ViewPager) findViewById(R.id.main_vp);
        mMyTabRbt = (RadioButton) findViewById(R.id.main_activity_tab_my_rbt);
        mHomeTabRbt = (RadioButton) findViewById(R.id.main_activity_tab_home_rbt);
        mWuliuTabRbt = (RadioButton) findViewById(R.id.main_activity_tab_wuliu_rbt);
        // mWaybillTabRbt = (RadioButton) findViewById(R.id.tab_waybill);


        pHomeFragment = HomeFragment.newInstance();
        mWuliuFragment = WuliuFragment.newInstance();
        // wayBillFragment = WayBillFragment.newInstance();
        pMyFragment = MyFragment.newInstance();


        mTabFragmentsList.add(pHomeFragment);
        mTabFragmentsList.add(mWuliuFragment);
        // mTabFragmentsList.add(wayBillFragment);
        mTabFragmentsList.add(pMyFragment);


        mTabRbtsList.add(mHomeTabRbt);
        mTabRbtsList.add(mWuliuTabRbt);
        mTabRbtsList.add(mWaybillTabRbt);
        mTabRbtsList.add(mMyTabRbt);
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.main_activity_framelayout, pHomeFragment);
        mFragmentTransaction.commit();

        // mTabVP.setAdapter(new TabAdapter(getSupportFragmentManager(), mTabFragmentsList));


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        switch (checkedId) {
            case R.id.main_activity_tab_home_rbt:
                mFragmentTransaction.replace(R.id.main_activity_framelayout, pHomeFragment);

                break;
            case R.id.main_activity_tab_wuliu_rbt:

                mFragmentTransaction.replace(R.id.main_activity_framelayout, mWuliuFragment);

                break;
/*
            case R.id.tab_waybill:

                mFragmentTransaction.replace(R.id.main_framelayout, wayBillFragment);

                break;
                */

            case R.id.main_activity_tab_my_rbt:

                mFragmentTransaction.replace(R.id.main_activity_framelayout, pMyFragment);

                break;

            default:

                break;

        }
        mFragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences("locate_flag", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return mDoubleClick.onKeyDown(keyCode, event);
        }

        return super.onKeyDown(keyCode, event);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        pMyFragment.onActivityResult(requestCode, resultCode, result);
        pHomeFragment.onActivityResult(requestCode, resultCode, result);


    }



    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        //pMyFragment.startActivityForResult(intent,requestCode);
       // pHomeFragment.startActivityForResult(intent,requestCode);
    }



}
