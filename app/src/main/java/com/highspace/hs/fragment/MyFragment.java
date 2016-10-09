package com.highspace.hs.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.activity.MyAddressActivity;
import com.highspace.hs.activity.MyCaiWuActivity;
import com.highspace.hs.util.DoubleClickExitHelper;
import com.highspace.hs.util.ImageLoadUtil;
import com.highspace.hs.util.SharedPreferencesUtil;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyFragment extends Fragment {

    private RelativeLayout mMyAddressRL;
    private RelativeLayout mMyBillRL;
    private CircleImageView mCircleImageView;
    private TextView mLoginTextView;
    private PopupWindow mSettingPopw;
    private ImageView mSettingIv;
    private TextView mSettingXiugaiTv;

    private Uri mImagUri;

    private DoubleClickExitHelper mDoubleClick = new DoubleClickExitHelper(getActivity());

    public static Uri mUri;

    private boolean isImage = false;

    private boolean isLogin = false;


    private RelativeLayout mMyCaiWuRL;

    public MyFragment() {
        // Required empty public constructor
    }


    public static MyFragment newInstance() {
        MyFragment myFragment = new MyFragment();
        return myFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentContent = inflater.inflate(R.layout.fragment_my, container, false);
        initViews(myFragmentContent);
        initEvents();
        /*
        if(getArguments()!=null){
            Bundle bundle=getArguments();
            mImagUri= bundle.getParcelableArray("mUri");
            mCircleImageView.setImageURI(mImagUri);
        }
        */
        return myFragmentContent;
    }

    private void initEvents() {
        mSettingXiugaiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crop.pickImage(getActivity());
            }
        });
        mSettingIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingPopw.isShowing()) {
                    mSettingPopw.dismiss();
                } else {
                    mSettingPopw.showAsDropDown(mSettingIv, -mSettingIv.getWidth(), 0);
                }
            }
        });
        mMyAddressRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyAddressActivity.class));
            }
        });
        /*
        mMyBillRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyBillActivity.class));
            }
        });
        */
        /*
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    Crop.pickImage(getActivity());
                } else {
                    getActivity().startActivityForResult(new Intent(getActivity(), UserLoginActivity.class), 6666);
                }
            }
        });
        //mCircleImageView.setImageURI(MainActivity.mUri);
        */
        mMyCaiWuRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyCaiWuActivity.class));
            }
        });

        ImageLoadUtil.getImageLoader(getContext()).displayImage("http://www.sdongwan.top/images/a.png", mCircleImageView);
        // TODO: 2016/9/27 图片获取处理 
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6666 && resultCode == getActivity().RESULT_OK) {

            isLogin = data.getBooleanExtra("flag", true);
            //Log.e("isLogin", isLogin + "" + "dfssssssssfdssssssssss");
            // Toast.makeText(getActivity(), "loginflag", Toast.LENGTH_LONG).show();
            mLoginTextView.setText(data.getStringExtra("loginName"));


        }

        if (requestCode == Crop.REQUEST_PICK && resultCode == getActivity().RESULT_OK) {
            beginCrop(data.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, data);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void initViews(View view) {


        mSettingIv = (ImageView) view.findViewById(R.id.my_fragment_setting_iv);
        View settingPopwView = LayoutInflater.from(getActivity()).inflate(R.layout.popw_setting, null);
        mSettingXiugaiTv = (TextView) settingPopwView.findViewById(R.id.popw_setting_xiugai_tv);
        mSettingPopw = new PopupWindow(settingPopwView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mSettingPopw.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        mSettingPopw.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        mSettingPopw.setFocusable(true);


        mLoginTextView = (TextView) view.findViewById(R.id.my_fragment_login_tv);
        mMyCaiWuRL = (RelativeLayout) view.findViewById(R.id.my_fragment_my_caiwu_rl);
        mMyAddressRL = (RelativeLayout) view.findViewById(R.id.my_fragment_my_caiwu_my_xinyong_rl);
        // mMyBillRL = (RelativeLayout) view.findViewById(R.id.myfragment_my_bill_rl);
        mCircleImageView = (CircleImageView) view.findViewById(R.id.my_fragment_touxiang_civ);


/*
SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("locate_flag", Context.MODE_PRIVATE);
        isImage = sharedPreferences1.getBoolean("isImage", false);
 */

        isImage = SharedPreferencesUtil.getBooleanData("locate_flag", false, "isImage", getActivity());
        if (isImage && isLogin) {
            /*
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("locate_flag", Context.MODE_PRIVATE);
            String urlString = sharedPreferences.getString("imageUri", "");
             */
            String urlString = SharedPreferencesUtil.getStringData("locate_flag", "", "imageUri", getActivity());
            mCircleImageView.setImageURI(Uri.parse(urlString));
        } else {
            isImage = true;
            /*
               SharedPreferences sharedPreferences = getContext().getSharedPreferences("locate_flag", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isImage", isImage);
            editor.commit();
             */
            SharedPreferencesUtil.saveBooleanData("locate_flag", isImage, "isImage", getActivity());
            mCircleImageView.setImageResource(R.mipmap.ic_launcher);
        }


    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getActivity().getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(getActivity());
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == getActivity().RESULT_OK) {
            mUri = Crop.getOutput(result);
            mCircleImageView.setImageResource(0);
            mCircleImageView.setImageURI(mUri);
            //mCircleImageView.invalidate();
            SharedPreferencesUtil.saveStringData("locate_flag", String.valueOf(mUri), "imageUri", getContext());
            /*
                             SharedPreferences sharedPreferences = getContext().getSharedPreferences("locate_flag", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("imageUri", String.valueOf(mUri));
            editor.commit();
             */


        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(getContext(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
