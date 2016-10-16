package com.highspace.hs.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.db.GoodsContenProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//发布货源
public class ReleaseGoodsActivity extends Activity {

    private ImageView mBackIV;
    private EditText mGoodsStartTv;
    private EditText mGoodsDestTv;
    private EditText mGoodsWeightET;
    private EditText mGoodsRemarkET;
    private EditText mGoodsPhoneNumET;
    private EditText mGoodsVulmeET;
    private EditText mGoodsDescibeET;

    private Button mReleaseGoodsBtn;

    private TextView mDateTv;

    private Calendar calendar;
    private Dialog dialog;
    private int year, month, day;
    private Button fabu;


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

        mReleaseGoodsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGoodsInfo();
                Toast.makeText(ReleaseGoodsActivity.this, "货源发布成功", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(ReleaseGoodsActivity.this, MyGoodsActivity.class));
            }
        });
        mDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(ReleaseGoodsActivity.this);
                LayoutInflater inflater = LayoutInflater.from(ReleaseGoodsActivity.this);
                View view = inflater.inflate(R.layout.dialog, null);
                TextView quxiao = (TextView) view.findViewById(R.id.cancel);
                TextView queren = (TextView) view.findViewById(R.id.ok);
                DatePicker picker = (DatePicker) view.findViewById(R.id.picker);
                Window window = dialog.getWindow();
                dialog.setContentView(view);
                dialog.setTitle("请选择日期");
                dialog.show();
                window.setGravity(Gravity.BOTTOM);

                picker.init(year, month - 1, day, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        ReleaseGoodsActivity.this.year = year;
                        month = monthOfYear + 1;   //monthOfYear比日期显示少1
                        day = dayOfMonth;
                    }
                });

                quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        dialog = null;
                    }
                });
                queren.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDateTv.setText(ReleaseGoodsActivity.this.year + "-" + month + "-" + day);
                        dialog.dismiss();
                        dialog = null;
                    }
                });

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
        values.put("startPlace", mGoodsStartTv.getText().toString());
        values.put("destination", mGoodsDestTv.getText().toString());
        values.put("weight", mGoodsWeightET.getText().toString() + "t");
        // values.put("volume", mGoodsVulmeET.getText().toString() + " ");
        values.put("decribe", mGoodsDescibeET.getText().toString() + " ");
        values.put("phoneNum", mGoodsPhoneNumET.getText().toString() + " ");
        values.put("remark", mGoodsRemarkET.getText().toString() + " ");
        values.put("date", mDateTv.getText().toString());
        getContentResolver().insert(GoodsContenProvider.URI_Goods_ALL, values);
        //GoodsDBHelper.saveGoodsInfo(goods);
    }

    public String getDate() {
        Date date2 = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date2);
    }

    private void initView() {
        mBackIV = (ImageView) findViewById(R.id.release_goods_back_iv);
        mGoodsStartTv = (EditText) findViewById(R.id.release_goods_start_et);
        mGoodsDestTv = (EditText) findViewById(R.id.release_goods_dest_et);
        mGoodsWeightET = (EditText) findViewById(R.id.release_goods_weight_et);
        mGoodsRemarkET = (EditText) findViewById(R.id.release_goods_remark_et);
        mGoodsPhoneNumET = (EditText) findViewById(R.id.release_goods_phonenum_et);
        //mGoodsVulmeET = (EditText) findViewById(R.id.release_goods_vlume_et);
        mGoodsDescibeET = (EditText) findViewById(R.id.release_goods_decribe_et);
        mReleaseGoodsBtn = (Button) findViewById(R.id.release_goods_btn);
        mDateTv = (TextView) findViewById(R.id.releasw_goods_date_tv);

        mDateTv.setText(getDate());
    }


}
