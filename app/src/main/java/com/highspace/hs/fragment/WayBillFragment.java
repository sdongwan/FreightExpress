package com.highspace.hs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.highspace.hs.R;


public class WayBillFragment extends Fragment {

    private ViewPager mWaybillVp;
    private PagerSlidingTabStrip mPagerTabStrip;
    private TextView mTuoyunTV;
    private TextView mZhaobiaoTV;

    public WayBillFragment() {
        // Required empty public constructor
    }

    public static WayBillFragment newInstance() {
        WayBillFragment wayBillFragment = new WayBillFragment();
        return wayBillFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_waybill, container, false);
        initViews(view);
        initEvents();
        return view;
    }

    private void initEvents() {
        mTuoyunTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTuoyunTV.setBackgroundResource(R.mipmap.bg_left_bold);
                mZhaobiaoTV.setBackgroundResource(R.mipmap.bg_right_transparent);
                //// TODO: 2016/9/18
                Toast.makeText(getContext(), "tuoyun", Toast.LENGTH_SHORT).show();
            }
        });

        mZhaobiaoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTuoyunTV.setBackgroundResource(R.mipmap.bg_left_transparent);
                mZhaobiaoTV.setBackgroundResource(R.mipmap.bg_right_bold);
                //// TODO: 2016/9/18
                Toast.makeText(getContext(), "zhaobiao", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initViews(View view) {
       // mWaybillVp = (ViewPager) view.findViewById(R.id.waybill_vp);
       // mPagerTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
       // mWaybillVp.setAdapter(new WayBillVPAdapter(getActivity()));
       // mPagerTabStrip.setViewPager(mWaybillVp);

        mTuoyunTV = (TextView) view.findViewById(R.id.tuoyun_tv);
        mZhaobiaoTV = (TextView) view.findViewById(R.id.zhaobiao_tv);


    }


}
