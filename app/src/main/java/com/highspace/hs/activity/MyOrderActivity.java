package com.highspace.hs.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.highspace.hs.R;
import com.highspace.hs.adapter.OrderMsAdapter;
import com.highspace.hs.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends FragmentActivity {

    //private ListView mMyOrderLv;
    // private MyContentObserver myContentObserver;
    //private MyOrderApater mMyOrderApater;
    private ViewPager myOrderVp;
    private ImageView mImgBack;
    private PagerSlidingTabStrip myOrderPagerTab;
    private List<Fragment> myOrderFragmentList = new ArrayList<Fragment>();
    private FragmentManager myOrderFm = getSupportFragmentManager();

    //private LinearLayout mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initFragList();
        initView();
        initEvent();

    }

    private void initFragList() {
        OrderFragment orderFm1 = new OrderFragment();
        OrderFragment orderFm2 = new OrderFragment();
        OrderFragment orderFm3 = new OrderFragment();
        myOrderFragmentList.add(orderFm1);
        myOrderFragmentList.add(orderFm2);
        myOrderFragmentList.add(orderFm3);
    }


    private void initEvent() {

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myOrderVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                myOrderVp.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        /*Cursor cursor = getContentResolver().query(GoodsContenProvider.URI_Goods_ALL, null, null, null, null);
                /*
        myContentObserver = new MyContentObserver(null, cursor);

        cursor.registerContentObserver(myContentObserver);


        mMyOrderApater = new MyOrderApater(this, cursor, 0);

        mMyOrderLv.setAdapter(mMyOrderApater);
        mMyOrderLv.setEmptyView(mEmptyView);*/

    }

    private void initView() {
        //mMyOrderLv = (ListView) findViewById(R.id.my_order_lv);
        mImgBack = (ImageView) findViewById(R.id.my_order_back_iv);
        myOrderVp = (ViewPager) findViewById(R.id.my_order_vp);
        myOrderPagerTab = (PagerSlidingTabStrip) findViewById(R.id.my_order_tabs);
        OrderMsAdapter odAdapter = new OrderMsAdapter(myOrderFm, myOrderFragmentList);
        myOrderVp.setAdapter(odAdapter);
        myOrderPagerTab.setViewPager(myOrderVp);
        myOrderVp.setCurrentItem(0);

        //mEmptyView = (LinearLayout) findViewById(R.id.my_order_empty_view);
    }
}
