package com.highspace.hs.fragment;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.activity.WuLiuContentActivity;
import com.highspace.hs.adapter.SelectCityAdapter;
import com.highspace.hs.adapter.SelectSortAdapter;
import com.highspace.hs.adapter.WuLiuAdapter;
import com.highspace.hs.db.CityDBHelper;
import com.highspace.hs.view.XListView;

import java.util.ArrayList;


public class WuliuFragment extends Fragment {

    private LinearLayout mPopwStartll;


    private LinearLayout mPopwDestll;


    private LinearLayout mPopwSortll;

    private PopupWindow mStartPopw;
    private PopupWindow mDestPopw;
    private PopupWindow mSortPopw;
    private LayoutInflater mLayoutInflater;

    private SelectCityAdapter mPopwLeftLvAdapter;
    private SelectCityAdapter mPopwRightLvAdapter;
    private SelectSortAdapter mPaixuAdapter;

    private ListView mLeftLv;
    private ListView mRightLv;

    private ListView mPaixuPopwLv;

    private CityDBHelper mCityDbHelper;


    private TextView mCityTextView;
    private TextView mShaixuanTextView;

    private boolean isTriangleDown = true;


    private XListView mListView;
    private WuLiuAdapter mWuLiuAdapter;
    private ArrayList<String> mItems = new ArrayList<String>();
    private Handler mHandler;
    private int mStart = 0;
    private static int sRefreshCnt = 0;


    private int mFromYDelta;


    private View mGrayView;

    private TextView mDestTv;


    private boolean isCityPop = false;
    private LinearLayout mMain;

    public WuliuFragment() {

    }

    public static WuliuFragment newInstance() {
        WuliuFragment wuliuFragment = new WuliuFragment();
        return wuliuFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View wuLiuLayout = inflater.inflate(R.layout.fragment_wuliu, container, false);
        initViews(wuLiuLayout);
        initEvents();
        return wuLiuLayout;
    }

    private void initEvents() {
        mLeftLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mPopwLeftLvAdapter.changeSelected(position);
                mPopwRightLvAdapter = new SelectCityAdapter(getContext(), mCityDbHelper.getCitysFromDB(mCityDbHelper.getProVincesFromDB().getCountry().get(position).getProvinceName()).getCities(), 1);
                mRightLv.setAdapter(mPopwRightLvAdapter);

            }
        });

        mRightLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isCityPop) {
                    mCityTextView.setText("" + mPopwRightLvAdapter.getItem(position));
                } else {
                    mDestTv.setText("" + mPopwRightLvAdapter.getItem(position));
                }
                mStartPopw.dismiss();
            }
        });

        mPaixuPopwLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mShaixuanTextView.setText(mPaixuAdapter.getItem(position) + "");
                mSortPopw.dismiss();
            }
        });


        mPopwStartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStartPopw.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    mStartPopw.dismiss();
                    //((ImageView) (mPopwStartll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_down);
                    mGrayView.setVisibility(View.GONE);
                } else {
                    mStartPopw.showAsDropDown(v);
                    isCityPop = true;
                    mGrayView.setVisibility(View.VISIBLE);
                    mGrayView.setClickable(false);
                    mGrayView.setFocusable(false);
                    ((ImageView) (mPopwStartll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_up);


                }
            }
        });
        mStartPopw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (isCityPop) {
                    ((ImageView) (mPopwStartll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_down);
                    mGrayView.setVisibility(View.GONE);
                    isCityPop = false;
                } else {
                    ((ImageView) (mPopwDestll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_down);
                    mGrayView.setVisibility(View.GONE);

                }
            }
        });


        mPopwDestll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(), "paixu", Toast.LENGTH_SHORT).show();
                //// TODO: 2016/9/18
                if (mStartPopw.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    mStartPopw.dismiss();
                    //((ImageView) (mPopwStartll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_down);
                    mGrayView.setVisibility(View.GONE);
                } else {
                    mStartPopw.showAsDropDown(v);
                    //  mGrayView.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.pop_enter_anim));
                    mGrayView.setVisibility(View.VISIBLE);
                    mGrayView.setClickable(false);
                    mGrayView.setFocusable(false);
                    ((ImageView) (mPopwDestll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_up);


                }

            }
        });


        mPopwSortll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSortPopw.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    mSortPopw.dismiss();
                    //((ImageView) (mPopwStartll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_down);
                    //mGrayView.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.pop_exit_anim));
                    mGrayView.setVisibility(View.GONE);
                } else {

                    mSortPopw.showAsDropDown(v);
                    // mGrayView.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.pop_enter_anim));
                    mGrayView.setVisibility(View.VISIBLE);
                    mGrayView.setClickable(false);
                    mGrayView.setFocusable(false);
                    ((ImageView) (mPopwSortll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_up);


                }
            }
        });

        mSortPopw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ((ImageView) (mPopwSortll.getChildAt(1))).setImageResource(R.mipmap.icon_triangle_to_down);

                // mGrayView.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.pop_exit_anim));
                mGrayView.setVisibility(View.GONE);
            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "shaixuan", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getContext(), WuLiuContentActivity.class));

            }
        });

    }

    private void initViews(View view) {

        mMain = (LinearLayout) view.findViewById(R.id.wuliu_fragment_main_ll);
        mDestTv = (TextView) view.findViewById(R.id.wuliu_fragment_destplace_tv);
        mCityTextView = (TextView) view.findViewById(R.id.wuliu_fragment_startplace_tv);
        mShaixuanTextView = (TextView) view.findViewById(R.id.wuliu_fragment_paixu_tv);

        mGrayView = view.findViewById(R.id.wuliu_fragment_gray_layout);


        mPopwStartll = (LinearLayout) view.findViewById(R.id.wuliu_fragment_startplace_ll);


        mPopwDestll = (LinearLayout) view.findViewById(R.id.wuliu_fragment_destplace_ll);


        mPopwSortll = (LinearLayout) view.findViewById(R.id.wuliu_fragment_paixu_ll);

        mCityDbHelper = CityDBHelper.newInstance(getContext());

        mLayoutInflater = LayoutInflater.from(getContext());


        View citypopupview = mLayoutInflater.inflate(R.layout.ppw_city, null);
        mStartPopw = new PopupWindow(citypopupview, ViewGroup.LayoutParams.WRAP_CONTENT, 450, true);
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //mStartPopw.setBackgroundDrawable(dw);
        mStartPopw.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        mStartPopw.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        mStartPopw.setFocusable(true);
        mStartPopw.setAnimationStyle(R.style.pop_anim_style);


        View paixupopupview = mLayoutInflater.inflate(R.layout.ppw_shaixuan, null);
        mSortPopw = new PopupWindow(paixupopupview, ViewGroup.LayoutParams.MATCH_PARENT, 450, true);

        mSortPopw.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        mSortPopw.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        mSortPopw.setFocusable(true);

        mSortPopw.setAnimationStyle(R.style.pop_anim_style);

        mPaixuPopwLv = (ListView) paixupopupview.findViewById(R.id.popw_shaixuan_lv);
        ArrayList paixuList = new ArrayList();
        paixuList.add("综合排序");
        paixuList.add("人气最高");
        paixuList.add("好评率");
        mPaixuAdapter = new SelectSortAdapter(getContext(), paixuList);
        mPaixuPopwLv.setAdapter(mPaixuAdapter);


        mLeftLv = (ListView) citypopupview.findViewById(R.id.popw_city_selector_left_lv);
        mPopwLeftLvAdapter = new SelectCityAdapter(getContext(), mCityDbHelper.getProVincesFromDB().getCountry(), 0);
        mLeftLv.setAdapter(mPopwLeftLvAdapter);
        mRightLv = (ListView) citypopupview.findViewById(R.id.popw_city_selector_right_lv);
        mPopwRightLvAdapter = new SelectCityAdapter(getContext(), mCityDbHelper.getCitysFromDB(mCityDbHelper.getProVincesFromDB().getCountry().get(0).getProvinceName()).getCities(), 1);
        mRightLv.setAdapter(mPopwRightLvAdapter);


        mListView = (XListView) view.findViewById(R.id.wuliu_fragment_xListView);
        mListView.setPullLoadEnable(true);
        geneItems();
        mWuLiuAdapter = new WuLiuAdapter(getContext(), mItems);
        mListView.setAdapter(mWuLiuAdapter);

//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);


        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mStart = ++sRefreshCnt;
                        mItems.clear();
                        geneItems();
                        // mAdapter.notifyDataSetChanged();
                        //mAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_wuliu_info, mItems);
                        //mListView.setAdapter(mAdapter);
                        onLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        geneItems();
                        mWuLiuAdapter.notifyDataSetChanged();
                        onLoad();
                    }
                }, 2000);
            }
        });
        mHandler = new Handler();

    }

    private void geneItems() {
        for (int i = 0; i != 20; ++i) {
            mItems.add("refresh cnt " + (++mStart));
        }
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

}
