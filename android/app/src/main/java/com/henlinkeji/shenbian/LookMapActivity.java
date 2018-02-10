package com.henlinkeji.shenbian;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.bean.HeadTop;
import com.zhy.autolayout.AutoLayoutActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LookMapActivity extends AutoLayoutActivity {
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.right)
    TextView rightTv;
    @BindView(R.id.back)
    ImageView backIv;
    @BindView(R.id.toolbar_rl)
    RelativeLayout toolbarRl;

    private List<HeadTop.DataBean.PositionsBean> posList = new ArrayList<>();

    //初始化地图控制器对象
    private AMap aMap;
    private AMapLocationClient mlocationClient = null;

    private double lastLongitude;
    private double lastLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_map);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        initInstence();
        initData();
        initListener();
    }

    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        mlocationClient = new AMapLocationClient(getApplicationContext());
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置定位间隔,单位毫秒,默认为100000ms
//        mLocationOption.setInterval(100000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.interval(100000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//定位一次，且将视角移动到地图中心点。
        myLocationStyle.showMyLocation(false);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setZoomControlsEnabled(true);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setCompassEnabled(true);
        mlocationClient.startLocation();

        titleTv.setText("附近的服务");
        toolbarRl.setBackgroundColor(Color.parseColor("#404040"));
        rightTv.setTextColor(Color.parseColor("#ffffff"));
        titleTv.setTextColor(Color.parseColor("#ffffff"));
        backIv.setImageResource(R.mipmap.back2);
    }

    protected void initData() {
        if (getIntent().hasExtra("list")) {
            posList = (List<HeadTop.DataBean.PositionsBean>) getIntent().getSerializableExtra("list");
        }

        for (int i = 0; i < posList.size(); i++) {
            String loc = posList.get(i).get_location();
            String[] locs = loc.split(",");
            if (locs.length >= 1) {
                LatLng latLng = new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0]));
                final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng));
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(latLng);
                markerOption.draggable(false);//设置Marker可拖动
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_48)));
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(false);//设置marker平贴地图效果
                marker.setMarkerOptions(markerOption);
            }
        }
        //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
        if (posList.size() >= 1) {
            String loc = posList.get(0).get_location();
            String[] locs = loc.split(",");
            CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0])), 15, 0, 0));
            aMap.moveCamera(mCameraUpdate);
        }
    }

    protected void initListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                setMarker(cameraPosition.target.longitude + "", cameraPosition.target.latitude + "");
                LogUtil.e("==longitude=="+cameraPosition.target.longitude);
                LogUtil.e("==latitude=="+cameraPosition.target.latitude);
            }
        });
    }

    private void setMarker(String currentLongitude, String currentLatitude) {
        Map<String, String> params = new HashMap<>();
        params.put("city", SPUtils.getCity(this));
        params.put("center", currentLongitude + "," + currentLatitude);
        HttpUtils.post(this, MyConfig.HEAD_TOP, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                HeadTop headTop = new Gson().fromJson(response, HeadTop.class);
                if (!TextUtils.isEmpty(headTop.getStatus())) {
                    if (headTop.getStatus().equals("0000")) {
                        List<HeadTop.DataBean.PositionsBean>  posList = headTop.getData().getPositions();
                        for (int i = 0; i < posList.size(); i++) {
                            String loc = posList.get(i).get_location();
                            String[] locs = loc.split(",");
                            if (locs.length >= 1) {
                                LatLng latLng = new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0]));
                                final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng));
                                MarkerOptions markerOption = new MarkerOptions();
                                markerOption.position(latLng);
                                markerOption.draggable(false);//设置Marker可拖动
                                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_48)));
                                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                                markerOption.setFlat(false);//设置marker平贴地图效果
                                marker.setMarkerOptions(markerOption);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(String response) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
}
