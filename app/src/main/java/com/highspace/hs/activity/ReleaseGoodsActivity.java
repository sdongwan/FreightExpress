package com.highspace.hs.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.db.GoodsContenProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

//发布货源
public class ReleaseGoodsActivity extends Activity {

    private ImageView mBackIV;
    private EditText mGoodsStartET;
    private EditText mGoodsDestET;
    private EditText mGoodsWeightET;
    private EditText mGoodsRemarkET;
    private EditText mGoodsPhoneNumET;
    private EditText mGoodsVulmeET;
    private EditText mGoodsDescibeET;

    private LinearLayout mRealeaseGoodsInfoLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_goods);
        initView();
        initEvent();


    }

    private void initEvent() {
        mBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRealeaseGoodsInfoLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGoodsInfo();
                Toast.makeText(ReleaseGoodsActivity.this, "货源发布成功", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(ReleaseGoodsActivity.this, MyGoodsActivity.class));
            }
        });

    }

    private void saveGoodsInfo() {
        /*
        Goods goods = new Goods();
        goods.setStartPlace(mGoodsStartET.getText().toString());
        goods.setDestination(mGoodsDestET.getText().toString());
        goods.setPhoneNum(mGoodsPhoneNumET.getText().toString());
        goods.setRemark(mGoodsRemarkET.getText().toString());
        goods.setDecribe(mGoodsDescibeET.getText().toString());
        goods.setWeight(mGoodsWeightET.getText().toString());
        goods.setDate();
              "startPlace text," +
            "destination text," +
            "weight text," +
            "volume text," +
            "decribe text," +
            "phoneNum text," +
            "remark text," +
            "date text," +
          //  "goodsId integer" +
        */
        //goods.setVolume(mGoodsVulmeET.getText().toString());
        ContentValues values = new ContentValues();
        values.put("startPlace", mGoodsStartET.getText().toString());
        values.put("destination", mGoodsDestET.getText().toString());
        values.put("weight", mGoodsWeightET.getText().toString() + "t");
        values.put("volume", mGoodsVulmeET.getText().toString() + " ");
        values.put("decribe", mGoodsDescibeET.getText().toString() + " ");
        values.put("phoneNum", mGoodsPhoneNumET.getText().toString() + " ");
        values.put("remark", mGoodsRemarkET.getText().toString() + " ");
        values.put("date", getDate());
        getContentResolver().insert(GoodsContenProvider.URI_Goods_ALL, values);
        //GoodsDBHelper.saveGoodsInfo(goods);
    }

    public String getDate() {
        Date date2 = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        return simpleDateFormat.format(date2);
    }

    private void initView() {
        mBackIV = (ImageView) findViewById(R.id.release_goods_back_iv);
        mGoodsStartET = (EditText) findViewById(R.id.release_goods_start_et);
        mGoodsDestET = (EditText) findViewById(R.id.release_goods_dest_et);
        mGoodsWeightET = (EditText) findViewById(R.id.release_goods_weight_et);
        mGoodsRemarkET = (EditText) findViewById(R.id.release_goods_remark_et);
        mGoodsPhoneNumET = (EditText) findViewById(R.id.release_goods_phonenum_et);
        mGoodsVulmeET = (EditText) findViewById(R.id.release_goods_vlume_et);
        mGoodsDescibeET = (EditText) findViewById(R.id.release_goods_decribe_et);
        mRealeaseGoodsInfoLL = (LinearLayout) findViewById(R.id.release_goods_release_ll);
    }


}
