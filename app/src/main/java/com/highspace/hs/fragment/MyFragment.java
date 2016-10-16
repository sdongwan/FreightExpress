package com.highspace.hs.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.activity.AboutUsActivity;
import com.highspace.hs.activity.MyAddressActivity;
import com.highspace.hs.activity.MyCaiWuActivity;
import com.highspace.hs.activity.UserLoginActivity;
import com.highspace.hs.activity.UserSettingActivity;
import com.highspace.hs.util.DoubleClickExitHelper;
import com.highspace.hs.util.FastBlurUtil;
import com.highspace.hs.util.FileUtil;
import com.highspace.hs.util.SharedPreferencesUtil;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyFragment extends Fragment {

    private RelativeLayout mMyAddressRL;
    private CircleImageView mCircleImageView;
    private TextView mUserInfoTv;
    private PopupWindow mSettingPopw;
    private ImageView mSettingIv;

    private String mPhoneNum = "";


    private DoubleClickExitHelper mDoubleClick = new DoubleClickExitHelper(getActivity());

    public static Uri pUri;

    private ImageView mUserBg;


    private RelativeLayout mMyCaiWuRL;
    private RelativeLayout mMySettingRL;
    private RelativeLayout mAboutUsRL;
    private Button mLoginBtn;

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
        if (savedInstanceState != null) {
            mPhoneNum = savedInstanceState.getString("phoneNum");
            mUserInfoTv.setText(mPhoneNum);
            //  SharedPreferencesUtil.saveStringData("loginInfo", password, "password", UserLoginActivity.this);
            mPhoneNum = SharedPreferencesUtil.getStringData("loginInfo", "未登录", "phoneNum", getActivity());
            mUserInfoTv.setText(mPhoneNum + "");
            mLoginBtn.setVisibility(View.VISIBLE);
            mLoginBtn.invalidate();


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        // SharedPreferencesUtil.saveBooleanData("loginFlag", true, "isLogin", getActivity());

        initView(view);
        initEvent();

        if (SharedPreferencesUtil.getBooleanData("loginFlag", false, "isLogin", getActivity())) {
            mLoginBtn.setVisibility(View.GONE);


            // mLoginBtn.invalidate();

        }


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6666 && resultCode == getActivity().RESULT_OK) {

            return;
        }
        if (requestCode == 8888 && resultCode == getActivity().RESULT_OK) {

            //  if (data != null) {

            mPhoneNum = data.getStringExtra("phoneNum");
            String password = data.getStringExtra("password");
            mUserInfoTv.setText(mPhoneNum);
            mUserInfoTv.invalidate();
            mLoginBtn.setVisibility(View.GONE);
            mLoginBtn.invalidate();

            SharedPreferencesUtil.saveBooleanData("loginFlag", true, "isLogin", getActivity());
            // TODO: 2016/10/14
            //  Log.e("login", "fdssssssssssssssssssssssssssssssssssssssssssssssssssssssssslogin");
            Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();


        }

        if (requestCode == Crop.REQUEST_PICK && resultCode == getActivity().RESULT_OK) {
            beginCrop(data.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            try {
                handleCrop(resultCode, data);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mPhoneNum = savedInstanceState.getString("phoneNum");
            mUserInfoTv.setText(mPhoneNum);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("phoneNum", mPhoneNum);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void initView(View view) {


        mAboutUsRL = (RelativeLayout) view.findViewById(R.id.my_fragment_about_us_rl);
        mMySettingRL = (RelativeLayout) view.findViewById(R.id.my_fragment_setting_rl);
        mUserBg = (ImageView) view.findViewById(R.id.my_fragment_user_bg_iv);

        mLoginBtn = (Button) view.findViewById(R.id.my_fragment_login_btn);

        mSettingIv = (ImageView) view.findViewById(R.id.my_fragment_setting_iv);
        View settingPopwView = LayoutInflater.from(getActivity()).inflate(R.layout.popw_setting, null);

        mSettingPopw = new PopupWindow(settingPopwView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mSettingPopw.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        mSettingPopw.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        mSettingPopw.setFocusable(true);


        mUserInfoTv = (TextView) view.findViewById(R.id.my_fragment_usernanem_tv);
        mMyCaiWuRL = (RelativeLayout) view.findViewById(R.id.my_fragment_my_caiwu_rl);
        mMyAddressRL = (RelativeLayout) view.findViewById(R.id.my_fragment_my_caiwu_my_xinyong_rl);
        // mMyBillRL = (RelativeLayout) view.findViewById(R.id.myfragment_my_bill_rl);
        mCircleImageView = (CircleImageView) view.findViewById(R.id.my_fragment_touxiang_civ);


    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getActivity().getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(getActivity());

    }

    private void handleCrop(int resultCode, Intent result) throws IOException {
        if (resultCode == getActivity().RESULT_OK) {
            pUri = Crop.getOutput(result);
            mCircleImageView.setImageResource(0);
            mCircleImageView.setImageURI(pUri);
            SharedPreferencesUtil.saveStringData("locate_flag", String.valueOf(pUri), "imageUri", getContext());
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), pUri);
            setUserBg(bitmap);

        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(getContext(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setUserBg(final Bitmap originBitmap) {


        final int blurRadius = 4;
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    Bundle bundle = msg.getData();
                    int bgWidth = (int) bundle.get("width");
                    int bgHeight = (int) bundle.get("height");

                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(originBitmap, bgWidth, bgHeight, false);
                    Bitmap blurBitmap = FastBlurUtil.doBlur(scaledBitmap, blurRadius, true);
                    mUserBg.setImageBitmap(blurBitmap);
                    FileUtil.saveBitmapIntenalCatchPath(blurBitmap, "blurbitmap.png");

                }


            }
        };

        mUserBg.post(new Runnable() {
            @Override
            public void run() {
                int width = mUserBg.getWidth();
                int height = mUserBg.getHeight();
                Message message = Message.obtain();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putInt("width", width);
                bundle.putInt("height", height);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }

    private void initEvent() {


        mAboutUsRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
            }
        });


        mMySettingRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserSettingActivity.class));
            }
        });


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(getActivity(), UserLoginActivity.class), 8888);
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

        mMyCaiWuRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyCaiWuActivity.class));
            }
        });


        // TODO: 2016/9/27 图片获取处理
        String str = SharedPreferencesUtil.getStringData("locate_flag", "", "imageUri", getActivity());
        if (str != "") {
            Uri uri = Uri.parse(str);
            mCircleImageView.setImageURI(uri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                if (FileUtil.getBitmapFromIntenerPath("blurbitmap.png") != null) {
                    bitmap = FileUtil.getBitmapFromIntenerPath("blurbitmap.png");
                    mUserBg.setImageBitmap(bitmap);

                } else {
                    setUserBg(bitmap);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            if (FileUtil.getBitmapFromIntenerPath("touxiang.png") != null) {
                Bitmap bitmap = FileUtil.getBitmapFromIntenerPath("touxiang.png");
                setUserBg(bitmap);
                mCircleImageView.setImageBitmap(bitmap);
            } else {
                /*
                ImageLoadUtil.getImageLoader(getContext()).loadImage("http://www.sdongwan.top/images/a.png", new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        if (loadedImage != null) {

                            FileUtil.saveBitmapIntenalCatchPath(loadedImage, "touxiang.png");
                            mCircleImageView.setImageBitmap(loadedImage);
                            setUserBg(loadedImage);

                        }
                    }
                });
*/
                Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.icon_all);
                FileUtil.saveBitmapIntenalCatchPath(bitmap, "touxiang.png");
                mCircleImageView.setImageBitmap(bitmap);
                setUserBg(bitmap);

            }

        }

    }
}
