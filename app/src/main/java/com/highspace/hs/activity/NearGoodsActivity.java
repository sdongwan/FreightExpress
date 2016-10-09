package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.highspace.hs.R;
import com.highspace.hs.bean.Info;
import com.highspace.hs.listener.MyOrientationListener;
import com.highspace.hs.view.DrivingRouteOverlay;

import java.util.List;


public class NearGoodsActivity extends Activity implements OnGetRoutePlanResultListener {
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    private Button mCompanyBtn;
    private Button mCarBtn;

    private LocationClient mLocationClient;
    private NearGoodsLocationListener mMyLocationListener;

    private boolean isFirstIn = true;
    private boolean isOverLay = true;

    private double mLongitude;
    private double mLatitude;

    private float mCurrentX;

    private BitmapDescriptor mMarker;

    private RelativeLayout topLayout;

    private BitmapDescriptor mBitmapDescriptor = null;

    private MyOrientationListener mMyOrientationListener;

    private MyLocationConfiguration.LocationMode mLocationMode;

    private PoiSearch mPoiSearch;
    private RoutePlanSearch mSearch;

    private Intent mIntent;

    private String mLocatedPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_goods);
        mIntent = getIntent();
        mLocatedPlace = mIntent.getStringExtra("place");
        initView();
        initEvent();
    }

    private void initView() {
        mCompanyBtn = (Button) findViewById(R.id.near_goods_near_company_btn);
        mCarBtn = (Button) findViewById(R.id.near_goods_near_car_btn);
        initBaiduMap();

    }

    public void initBaiduMap() {

        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);

        mPoiSearch = PoiSearch.newInstance();
        mSearch = RoutePlanSearch.newInstance();

        mLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
        mLocationClient = new LocationClient(this);

        mMyLocationListener = new NearGoodsLocationListener();

        mLocationClient.registerLocationListener(mMyLocationListener);

        mBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_navi_map_gps_locked);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setScanSpan(1000);
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
        //  mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
//卫星地图
        // mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//开启交通图
        // mBaiduMap.setTrafficEnabled(true);

        mMyOrientationListener = new MyOrientationListener(this);
        mMyOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChange(float x) {
                mCurrentX = x;
            }
        });



        //构建Marker图标
        final BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_openmap_mark);

        final OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
            public void onGetPoiResult(PoiResult result) {
                //获取POI检索结果

                if (result == null) {
                    Toast.makeText(NearGoodsActivity.this, "未能检索到", Toast.LENGTH_LONG).show();
                    return;
                }
                for (int i = 0; i < result.getAllPoi().size(); i++) {

                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(result.getAllPoi().get(i).location)
                            .icon(bitmap);
                    //在地图上添加Marker，并显示
                    mBaiduMap.addOverlay(option);
                    isOverLay = false;

                }
            }


            public void onGetPoiDetailResult(PoiDetailResult result) {
                //获取Place详情页检索结果
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };

        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);


        /*
        mSearch.setOnGetRoutePlanResultListener(this);
        PlanNode stNode = PlanNode.withCityNameAndPlaceName("东莞", "厚街");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName("江门", "五邑大学");
        mSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode).to(enNode));
*/

    }

    private void initEvent() {


        mCompanyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOverLay) {
                    if (!mLocatedPlace.equals("") && mLocatedPlace != null) {
                        mPoiSearch.searchInCity((new PoiCitySearchOption()).city(mLocatedPlace).keyword("货运物流公司").pageNum(10));
                    }
                } else {
                    Toast.makeText(NearGoodsActivity.this, "周围物流公司已显示", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addOverLay(Info.infos);
                // TODO: 2016/10/3
            }
        });
    }


    @Override
    protected void onStart() {//???
        super.onStart();

        mBaiduMap.setMyLocationEnabled(true);
        if (mBaiduMap.isMyLocationEnabled()) {
            mLocationClient.start();
        }
        mMyOrientationListener.start();

    }

    @Override
    protected void onStop() {//??
        super.onStop();

        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        mMyOrientationListener.stop();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPoiSearch.destroy();
        mSearch.destroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
        if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(NearGoodsActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (drivingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {


            if (drivingRouteResult.getRouteLines().size() > 1) {
                for (int i = 0; i < drivingRouteResult.getRouteLines().size(); i++) {

                    DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                    mBaiduMap.setOnMarkerClickListener(overlay);
                    // routeOverlay = overlay;
                    overlay.setData(drivingRouteResult.getRouteLines().get(i));
                    overlay.addToMap();
                    //  overlay.zoomToSpan();
                }

            } else if (drivingRouteResult.getRouteLines().size() == 1) {
                // route = drivingRouteResult.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                // routeOverlay = overlay;
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(drivingRouteResult.getRouteLines().get(0));
                overlay.addToMap();
                // overlay.zoomToSpan();

            }

        }
    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }



    private class NearGoodsLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();
            MyLocationData myLocationData = new MyLocationData.Builder().
                    direction(mCurrentX).
                    accuracy(bdLocation.getRadius()).
                    latitude(mLatitude).
                    longitude(mLongitude).build();

            mBaiduMap.setMyLocationData(myLocationData);

            MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(mLocationMode, true, mBitmapDescriptor);
            mBaiduMap.setMyLocationConfigeration(myLocationConfiguration);

            if (isFirstIn) {
                LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(mapStatusUpdate);
                isFirstIn = false;
            }
        }
    }

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        private boolean useDefaultIcon = true;

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.mipmap.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.mipmap.icon_en);
            }
            return null;
        }
    }


    private void addOverLay(List<Info> infos) {
        mBaiduMap.clear();
        LatLng latLng = null;
        Marker marker = null;
        OverlayOptions overlayOptions = null;
        for (Info info : infos) {
            latLng = new LatLng(info.getLatitude(), info.getLongitude());
            overlayOptions = new MarkerOptions().position(latLng).icon(mMarker).zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(overlayOptions);
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", info);
            marker.setExtraInfo(bundle);

        }
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(mapStatusUpdate);

    }

}