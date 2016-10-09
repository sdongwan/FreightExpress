package com.highspace.hs.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.highspace.hs.R;
import com.highspace.hs.activity.MyCommentActivity;
import com.highspace.hs.activity.MyGoodsActivity;
import com.highspace.hs.activity.MyOrderActivity;
import com.highspace.hs.activity.NearGoodsActivity;
import com.highspace.hs.activity.ReleaseGoodsActivity;
import com.highspace.hs.activity.SaoMaActivity;
import com.highspace.hs.adapter.HomeCarouselAdapter;
import com.highspace.hs.util.DensityUtil;
import com.highspace.hs.util.FileUtil;
import com.highspace.hs.util.ImageLoadUtil;
import com.highspace.hs.util.MobleUtil;
import com.highspace.hs.util.SharedPreferencesUtil;
import com.highspace.hs.view.LoadProgressDialog;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    private static final int REQUEST_PHOTO_IMAGE = 1667;

    private ViewPager mViewPager;
    private List<ImageView> mImageViewList;
    public TextView mLacateplaceTv;

    private Handler mHandler;
    private Timer mTimer;


    public LocationClient mLocationClient = null;
    public BDLocationListener mMyListener;

    private LoadProgressDialog mDialog;


    private LinearLayout mVpLayout;
    private ImageView mSaoMaIV;


    private static Boolean isfirstLocate = true;

    private TextView mAnimotionTextTv;

    private TextView mFabuhuoyuanTV;
    private TextView mNearGoodsTV;
    private TextView mMyOrderTV;
    private TextView mMyGoodsTV;

    private TextView mComment;
    private Animation mAnimation;


    private String mPlace = "";
    private int REQUEST_CODE = 1666;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layoutView = inflater.inflate(R.layout.fragment_home, container, false);


        mPlace = SharedPreferencesUtil.getStringData("locate_flag", "北京", "mPlace", getActivity());

        Log.e("mPlace", mPlace);

        initViews(layoutView);
        initEvents();
        return layoutView;
    }

    @Override
    public void onResume() {

        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        mLocationClient.unRegisterLocationListener(mMyListener);
        mLocationClient.stop();
        mAnimotionTextTv.clearAnimation();

    }

    @Override
    public void onStart() {
        super.onStart();
        if(mAnimation!=null){
            mAnimotionTextTv.startAnimation(mAnimation);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTimer.cancel();
        mTimer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public HomeFragment() {
        //mContext = this.getActivity().getBaseContext();
    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;

    }

    private void initEvents() {

        mFabuhuoyuanTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReleaseGoodsActivity.class));


            }
        });
        mMyGoodsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyGoodsActivity.class));
            }
        });


        mNearGoodsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NearGoodsActivity.class);
                intent.putExtra("mPlace", mPlace);
                startActivity(intent);
            }
        });


        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 11) {
                    mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % 3);
                }
            }
        };
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(11);
                //Log.e("message", "dfskjsdkfjsdkfsdlkfjlsdfjldsfjldsjflkdsjf");
            }
        }, 3000, 6000);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 3; i++) {
                    ImageView imageView = (ImageView) mVpLayout.getChildAt(i);
                    imageView.setImageResource(i == position ? R.mipmap.icon_page_indicator_focused : R.mipmap.icon_page_indicator_unfocused);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mSaoMaIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(), "扫码", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SaoMaActivity.class);
                getActivity().startActivityForResult(intent, REQUEST_CODE);


            }
        });
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.text_bottom_to_top);
        mAnimotionTextTv.startAnimation(mAnimation);


        mMyOrderTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
            }
        });
        mComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyCommentActivity.class));
            }
        });
    }

    private void initViews(View view) {

        mComment = (TextView) view.findViewById(R.id.home_fragment_comment_tv);


        mMyGoodsTV = (TextView) view.findViewById(R.id.home_fragment_my_goods_tv);
        mFabuhuoyuanTV = (TextView) view.findViewById(R.id.home_fragment_release_goods_tv);
        mNearGoodsTV = (TextView) view.findViewById(R.id.home_fragment_near_goods_tv);
        mMyOrderTV = (TextView) view.findViewById(R.id.home_fragment_my_order_tv);


        mAnimotionTextTv = (TextView) view.findViewById(R.id.home_fragment_animotinText_tv);
        mSaoMaIV = (ImageView) view.findViewById(R.id.home_fragment_titlebar_saoma_iv);
        mLacateplaceTv = (TextView) view.findViewById(R.id.home_fragment_titlebar_locatedplace_tv);
        mViewPager = (ViewPager) view.findViewById(R.id.homefragment_lubo_vp);
        mImageViewList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            final ImageView imageView = new ImageView(this.getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoadUtil.getImageLoader(getContext()).loadImage("http://www.sdongwan.top/images/a.png", new SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    // loadedImage.setHeight(DensityUtil.dp2px(getContext(), 180));
                    //loadedImage.setWidth(MobleUtil.getScreenWith(getActivity()));

                    Bitmap target = Bitmap.createBitmap(MobleUtil.getScreenWith(getContext()), DensityUtil.dp2px(getContext(), 180), loadedImage.getConfig());
                    Canvas canvas = new Canvas(target);
                    canvas.drawBitmap(loadedImage, null, new Rect(0, 0, target.getWidth(), target.getHeight()), null);
                    imageView.setImageBitmap(target);

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    Bitmap loadedImage = BitmapFactory.decodeResource(getResources(),R.mipmap.icon_no_img);
                    Bitmap target = Bitmap.createBitmap(MobleUtil.getScreenWith(getContext()), DensityUtil.dp2px(getContext(), 180), loadedImage.getConfig());
                    Canvas canvas = new Canvas(target);
                    canvas.drawBitmap(loadedImage, null, new Rect(0, 0, target.getWidth(), target.getHeight()), null);
                    imageView.setImageBitmap(target);

                }
            });

            mImageViewList.add(imageView);

        }
        mViewPager.setAdapter(new HomeCarouselAdapter(mImageViewList));
        mVpLayout = (LinearLayout) view.findViewById(R.id.home_fragment_circle_img_ll);
        for (int j = 0; j < 3; j++) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 0, 0, 0);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(j == 0 ? R.mipmap.icon_page_indicator_focused : R.mipmap.icon_page_indicator_unfocused);
            mVpLayout.addView(imageView);
        }


        mLocationClient = new LocationClient(getActivity());     //声明LocationClient类
        initLocation();
        mMyListener = new MyLocationListener();
        if (mMyListener != null)
            mLocationClient.registerLocationListener(mMyListener);    //注册监听函数

        // isfirstLocate = loadLocateFlag();
        isfirstLocate = SharedPreferencesUtil.getBooleanData("locate_flag", true, "locate", getActivity());
        if (isfirstLocate) {
            mDialog = LoadProgressDialog.Create(getContext(), "正在定位中...");
            if (!mDialog.isShowing()) {
                mDialog.show();
            }
            mLocationClient.start();
            isfirstLocate = false;
            SharedPreferencesUtil.saveBooleanData("locate_flag", isfirstLocate, "locate", getActivity());

        } else {
            if (!mPlace.equals(""))
                mLacateplaceTv.setText(mPlace + "");

        }

        //mLacateplaceTv.setText(BaiduMapUtil.getInstance().cityResult);
        //   Log.e("位置", BaiduMapUtil.getInstance().cityResult+"");
    }

    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        //int span = 1000;
        option.setScanSpan(1000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);

    }


    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                mLacateplaceTv.setText(location.getCity() + "");


                SharedPreferencesUtil.saveStringData("locate_flag", location.getCity() + "", "mPlace", getActivity());

                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                Log.e("位置", location.getLocationDescribe() + "");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                // mLacateplaceTv.setText(location.getCity() + "");
                mLacateplaceTv.setText(location.getCity() + "");
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }

                SharedPreferencesUtil.saveStringData("locate_flag", location.getCity() + "", "mPlace", getActivity());


            }
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }


            }
        }

        if (requestCode == REQUEST_PHOTO_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver cr = getActivity().getContentResolver();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片


                    CodeUtils.analyzeBitmap(FileUtil.getPath(getActivity(), uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });

                    if (mBitmap != null) {
                        mBitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


