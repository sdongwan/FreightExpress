package com.highspace.hs.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.highspace.hs.R;
import com.highspace.hs.adapter.MyGoodsInfoAdapter;
import com.highspace.hs.db.GoodsContenProvider;

public class MyGoodsActivity extends Activity {
    private ListView mGoodsInfoLv;
    // private MyContentObserver myContentObserver;
    private MyGoodsInfoAdapter mGoodsInfoAdapter;

    private ImageView mImgBack;

    private LinearLayout mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goods);
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

        mGoodsInfoAdapter = new MyGoodsInfoAdapter(this, cursor, 0);
        mGoodsInfoLv.setAdapter(mGoodsInfoAdapter);
        mGoodsInfoLv.setEmptyView(mEmptyView);


    }

    private void initView() {
        mGoodsInfoLv = (ListView) findViewById(R.id.my_goods_info_lv);
        mImgBack = (ImageView) findViewById(R.id.my_goods_back_iv);
        mEmptyView = (LinearLayout) findViewById(R.id.my_goods_empty_view);
    }
}
