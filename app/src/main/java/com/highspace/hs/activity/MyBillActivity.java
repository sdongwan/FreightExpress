package com.highspace.hs.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.highspace.hs.R;
import com.highspace.hs.fragment.WayBillFragment;


//我的账单
public class MyBillActivity extends FragmentActivity {


    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bill);
        initviews();
    }

    private void initviews() {

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.mybillactivity_empty_view, new WayBillFragment()).commit();

    }
}
