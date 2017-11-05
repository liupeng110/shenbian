package com.henlinkeji.shenbian;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.fence.PoiItem;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps2d.overlay.PoiOverlay;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.autonavi.ae.gmap.maploader.ERROR_CODE;
import com.henlinkeji.shenbian.base.amap.GeoCoderUtil;
import com.henlinkeji.shenbian.base.amap.InputTipTask;
import com.henlinkeji.shenbian.base.amap.LatLngEntity;
import com.henlinkeji.shenbian.base.amap.LocationBean;
import com.henlinkeji.shenbian.base.amap.PoiSearchTask;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.InputTools;
import com.henlinkeji.shenbian.base.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPositionActivity extends BaseActivity implements AMapLocationListener, AMap.OnCameraChangeListener, LocationSource {
    @BindView(R.id.back)
    ImageView backImg;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.right)
    TextView rightTv;
    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.lv_location_nearby)
    ListView mapList;
    @BindView(R.id.et_search)
    AutoCompleteTextView autotext;
    @BindView(R.id.address_desc)
    TextView addressTv;
    @BindView(R.id.btn_ensure)
    Button sureBtn;

    private AMap aMap;
    private AMapLocationClient mLocationClient;

    private PoiAdapter poiAdapter;
    private LocationBean currentLoc;
    private LocationBean slectLoc;

    private LoadingDialog loadingDialog;

    private LocationSource.OnLocationChangedListener mListener;

    private LatLng latlng;

    private List<LocationBean> dataLists = new ArrayList<>();
    private ArrayList<String> locs = new ArrayList<>();
    private Inputtips mInputTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_position);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        titleTv.setText("位置信息");
        rightTv.setText("确定");
        rightTv.setTextColor(Color.parseColor("#ff0000"));
        backImg.setImageResource(R.mipmap.back);

        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setOnCameraChangeListener(this);
            setUpMap();
        }
        loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("身边");
        InputTools.HideKeyboard(titleTv);
    }

    //-------- 定位 Start ------

    private void setUpMap() {
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(getApplicationContext());
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mLocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setOnceLocation(true);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();
        }
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
    }

    @Override
    protected void initData() {
        poiAdapter = new PoiAdapter(this);
        mapList.setAdapter(poiAdapter);
    }

    @Override
    protected void initListener() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        autotext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    return;
                } else {
                    //第二个参数默认代表全国，也可以为城市区号
                    InputtipsQuery inputquery = new InputtipsQuery(s.toString().trim(), "");
                    inputquery.setCityLimit(true);
                    mInputTips = new Inputtips(SelectPositionActivity.this, inputquery);
                    mInputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                        @Override
                        public void onGetInputtips(List<Tip> list, int i) {

                            if (i == 1000) {
                                locs = new ArrayList<>();
                                if (list != null) {
                                    dataLists.clear();
                                    for (Tip tip : list) {
                                        locs.add(tip.getName());
                                        dataLists.add(new LocationBean(tip.getPoint().getLongitude(), tip.getPoint().getLatitude(), tip.getAddress(), tip.getDistrict()));
                                    }
                                }
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SelectPositionActivity.this, android.R.layout.simple_list_item_1, locs);
                                autotext.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                    mInputTips.requestInputtipsAsyn();

                    for (int i = 0; i < locs.size(); i++) {
                        if (autotext.getText().toString().equals(locs.get(i))) {
                            //这里是定位完成之后开始poi的附近搜索，代码在后面
                            PoiSearchTask.getInstance(SelectPositionActivity.this).setAdapter(poiAdapter).onSearch(locs.get(i), "", dataLists.get(i).getLat(), dataLists.get(i).getLon());
                            //移动地图中心到当前的定位位置
                            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dataLists.get(i).getLat(), dataLists.get(i).getLon()), 15));
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLoc != null) {
                    Intent intent = new Intent();
                    intent.putExtra("bean", slectLoc);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtils.disPlayShort(SelectPositionActivity.this, "未选择地址");
                }
            }
        });
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLoc != null) {
                    Intent intent = new Intent();
                    intent.putExtra("bean", currentLoc);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtils.disPlayShort(SelectPositionActivity.this, "未选择地址");
                }
            }
        });
    }

    /**
     * 定位的回调
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        loadingDialog.exit();
        if (aMapLocation != null && aMapLocation.getErrorCode() == ERROR_CODE.ERROR_NONE) {
            //获取定位信息
            double latitude = aMapLocation.getLatitude();
            double longitude = aMapLocation.getLongitude();
            String addressStr = aMapLocation.getCity() + aMapLocation.getDistrict() + aMapLocation.getStreet();
            if (!TextUtils.isEmpty(aMapLocation.getAoiName())) {
                addressStr += aMapLocation.getAoiName();
            } else {
                addressStr = addressStr + aMapLocation.getPoiName() + "附近";
            }
            addressTv.setText(addressStr);
            // 显示我的位置
            mListener.onLocationChanged(aMapLocation);
            //移动地图中心到当前的定位位置
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 15));
            //设置第一次焦点中心
            latlng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 14), 1000, null);
            //这里是定位完成之后开始poi的附近搜索，代码在后面
            PoiSearchTask.getInstance(this).setAdapter(poiAdapter).onSearch("", "", latitude, longitude);
        } else {
            addressTv.setText("定位失败");
        }
    }

    /**
     * 正在移动地图事件回调
     */
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    /**
     * 移动地图结束事件的回调
     */
    @Override
    public void onCameraChangeFinish(final CameraPosition cameraPosition) {
        latlng = cameraPosition.target;
        aMap.clear();
        aMap.addMarker(new MarkerOptions().position(latlng));
        LatLngEntity latLngEntity = new LatLngEntity(cameraPosition.target.latitude, cameraPosition.target.longitude);
        //地理反编码工具类，代码在后面
        GeoCoderUtil.getInstance(SelectPositionActivity.this).geoAddress(latLngEntity, new GeoCoderUtil.GeoCoderAddressListener() {
            @Override
            public void onAddressResult(String result) {
                if (!autotext.getText().toString().trim().equals("")) {
                    //输入地址后的点击搜索
                    addressTv.setText(autotext.getText().toString().trim());
                    currentLoc = new LocationBean(cameraPosition.target.longitude, cameraPosition.target.latitude, autotext.getText().toString().trim(), "");
                } else {
                    //拖动地图
                    addressTv.setText(result);
                    currentLoc = new LocationBean(cameraPosition.target.longitude, cameraPosition.target.latitude, result, "");
                }
                //地图的中心点位置改变后都开始poi的附近搜索
                PoiSearchTask.getInstance(SelectPositionActivity.this).setAdapter(poiAdapter).onSearch("", "", cameraPosition.target.latitude, cameraPosition.target.longitude);
            }
        });
    }

    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        mLocationClient.startLocation();
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        super.onDestroy();
    }

    //POI搜索显示地址adapter
    public class PoiAdapter extends BaseAdapter {

        private List<LocationBean> datas = new ArrayList<>();
        private HashMap<Integer, Boolean> hashMap = new HashMap<>();

        private static final int RESOURCE = R.layout.amap_poi_item_layout;

        public PoiAdapter(Context context) {

        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder vh = null;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = getLayoutInflater().inflate(RESOURCE, null);
                vh.tv_title = (TextView) convertView.findViewById(R.id.address);
                vh.tv_text = (TextView) convertView.findViewById(R.id.address_desc);
                vh.rightImg = (ImageView) convertView.findViewById(R.id.right);
                vh.positionRl = (RelativeLayout) convertView.findViewById(R.id.position_rl);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            LocationBean bean = (LocationBean) getItem(position);
            vh.tv_title.setText(bean.getTitle());
            vh.tv_text.setText(bean.getContent());
            if (hashMap.get(position)) {
                vh.rightImg.setVisibility(View.VISIBLE);
            } else {
                vh.rightImg.setVisibility(View.GONE);
            }
            mapList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (int i = 0; i < datas.size(); i++) {
                        hashMap.put(i, false);
                    }
                    hashMap.put(position, true);
                    if (parent.getId() == R.id.lv_location_nearby) {
                        //POI的地址的listview的item的点击
                        slectLoc = (LocationBean) poiAdapter.getItem(position);
                    } else {
                        //自动提示的内容的item的点击
                        //保存自动提示的列表的内容，供后面使用
                        List<LocationBean> dataLists = InputTipTask.getInstance(SelectPositionActivity.this).getBean();
                        //点击后地图中心移动
                        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dataLists.get(position).getLat(), dataLists.get(position).getLon()), 15));
                    }
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        private class ViewHolder {
            public TextView tv_title;
            public TextView tv_text;
            public ImageView rightImg;
            public RelativeLayout positionRl;
        }

        public void setData(List<LocationBean> datas) {
            loadingDialog.exit();
            this.datas = datas;
            if (datas.size() > 0) {
                for (int i = 0; i < datas.size(); i++) {
                    hashMap.put(i, false);
                }
                hashMap.put(0, true);
                slectLoc=datas.get(0);
                mapList.setVisibility(View.VISIBLE);
            } else {
                ToastUtils.disPlayShort(SelectPositionActivity.this, "没有搜索结果");
                mapList.setVisibility(View.GONE);
            }
        }
    }
}
