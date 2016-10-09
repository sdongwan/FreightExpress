package com.highspace.hs.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.highspace.hs.R;
import com.highspace.hs.adapter.MyOrderApater;
import com.highspace.hs.db.GoodsContenProvider;

public class MyOrderActivity extends Activity {

    private ListView mMyOrderLv;
    // private MyContentObserver myContentObserver;
    private MyOrderApater mMyOrderApater;

    private ImageView mImgBack;

    private LinearLayout mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initView();
        initEvent();

    }


    private void initEvent() {

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Cursor cursor = getContentResolver().query(GoodsContenProvider.URI_Goods_ALL, null, null, null, null);
                /*
        myContentObserver = new MyContentObserver(null, cursor);

        cursor.registerContentObserver(myContentObserver);
        */

        mMyOrderApater = new MyOrderApater(this, cursor, 0);

        mMyOrderLv.setAdapter(mMyOrderApater);
        mMyOrderLv.setEmptyView(mEmptyView);


    }

    private void initView() {
        mMyOrderLv = (ListView) findViewById(R.id.my_order_lv);
        mImgBack = (ImageView) findViewById(R.id.my_order_back_iv);
        mEmptyView = (LinearLayout) findViewById(R.id.my_order_empty_view);
    }
}
