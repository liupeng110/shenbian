package com.henlinkeji.shenbian.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.LoginActivity;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.SearchActivity;
import com.henlinkeji.shenbian.SelectCityActivity;
import com.henlinkeji.shenbian.adapter.RecyclerGridViewAdapter;
import com.henlinkeji.shenbian.base.amap.LocationBean;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.MultiItemTypeAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.henlinkeji.shenbian.bean.HeadTop;
import com.henlinkeji.shenbian.bean.HotSell;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * Created by smyh on 2015/4/28.
 */
public class HeadFragment extends Fragment {
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.head_loc_tv)
    TextView locTv;
    @BindView(R.id.head_search_lin)
    LinearLayout searchLin;
    @BindView(R.id.shopping_car_iv)
    ImageView headCarIv;
    @BindView(R.id.head_loc_lin)
    LinearLayout headLocLin;
    @BindView(R.id.quick_search_recy)
    RecyclerView quickSearchRecy;
    @BindView(R.id.classfy_recy)
    RecyclerView classfyRecy;
    @BindView(R.id.hot_recy)
    RecyclerView hotRecy;

    private CommonAdapter<String> quickSearchAdapter;
    private CommonAdapter<HotSell> hotSellAdapter;
    private List<String> searchList = new ArrayList<>();
    private List<String> classfyTextList = new ArrayList<>();
    private List<String> classfyImgList1 = new ArrayList<>();
    private List<Integer> classfyImgList2 = new ArrayList<>();
    private List<HotSell> hotSellList = new ArrayList<>();

    private AMapLocationClient mlocationClient = null;
    private LoadingDialog loadingDialog;

    private static final int SELECT_CITY = 3; // 请求码

    private boolean isSlect = false;

    private String city;

    private RecyclerGridViewAdapter recyclerGridViewAdapter;

    //初始化地图控制器对象
    private AMap aMap;

    private boolean isGetJW = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one, null);
        ButterKnife.bind(this, view);
//        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        initInstence();
        initData();
        initListener();
        return view;
    }

    protected void initInstence() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        quickSearchRecy.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        classfyRecy.setLayoutManager(gridLayoutManager);

        //设置布局管理器
        hotRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        mlocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为100000ms
//        mLocationOption.setInterval(100000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
        loadingDialog = new LoadingDialog(getActivity(), true);
        loadingDialog.show("身边");
    }

    protected void initData() {
        classfyTextList.add("找服务");
        classfyTextList.add("找人");
        classfyTextList.add("找活动");
        classfyTextList.add("找工作");
        classfyTextList.add("找租房");
        classfyTextList.add("学技能");
        classfyTextList.add("修手机、电脑");
        classfyTextList.add("全部");

        classfyImgList2.add(R.mipmap.zhaofuwu);
        classfyImgList2.add(R.mipmap.zhaoren);
        classfyImgList2.add(R.mipmap.zhaohuodong);
        classfyImgList2.add(R.mipmap.zhaogongzuo);
        classfyImgList2.add(R.mipmap.zhaozufang);
        classfyImgList2.add(R.mipmap.xuejineng);
        classfyImgList2.add(R.mipmap.xiushouji);
        classfyImgList2.add(R.mipmap.quanbu);

        quickSearchAdapter = new CommonAdapter<String>(getActivity(), R.layout.quick_search_item_layout) {
            @Override
            protected void convert(ViewHolder holder, String bean, int position) {
                holder.setText(R.id.text, bean);
            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView) {
                super.onViewHolderCreated(holder, itemView);
                AutoUtils.autoSize(itemView);
            }
        };
        quickSearchRecy.setAdapter(quickSearchAdapter);

        //设置适配器
        recyclerGridViewAdapter = new RecyclerGridViewAdapter(getActivity());
        classfyRecy.setAdapter(recyclerGridViewAdapter);
        recyclerGridViewAdapter.setLocalData(classfyTextList, classfyImgList2);
        recyclerGridViewAdapter.notifyDataSetChanged();

        hotSellAdapter = new CommonAdapter<HotSell>(getActivity(), R.layout.hot_sell_item_layout) {
            @Override
            protected void convert(ViewHolder holder, HotSell hotSell, int position) {
                    holder.setText(R.id.name,hotSell.getName());
                    holder.setText(R.id.content,hotSell.getContent());
            }
            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView) {
                super.onViewHolderCreated(holder, itemView);
                AutoUtils.autoSize(itemView);
            }
        };
        hotRecy.setAdapter(hotSellAdapter);

        HotSell h1 = new HotSell();
        h1.setName("服务1");
        h1.setContent("内容1内容1内容1内容1内容1内容1内容1内容1");
        HotSell h2 = new HotSell();
        h2.setName("服务2");
        h2.setContent("内容1内容1内容1内容1内容1内容2222222222222222222");
        HotSell h3 = new HotSell();
        h3.setName("服务3");
        h3.setContent("内容3333333333");
        HotSell h4 = new HotSell();
        h4.setName("服务4");
        h4.setContent("内容1内容1内容444444");
        HotSell h5 = new HotSell();
        h5.setName("服务5");
        h5.setContent("内容1内容1内容5555555");
        HotSell h6 = new HotSell();
        h6.setName("服务6");
        h6.setContent("内容1内容1内容66666666");
        hotSellList.add(h1);
        hotSellList.add(h2);
        hotSellList.add(h3);
        hotSellList.add(h4);
        hotSellList.add(h5);
        hotSellList.add(h6);

        hotSellAdapter.setDatas(hotSellList);

        aMap.getUiSettings().setZoomControlsEnabled(false);

    }

    protected void initListener() {
        //设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        aMapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);//定位时间
//                            ToastUtils.disPlayShort(getActivity(), aMapLocation.getAddress() +//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                                    aMapLocation.getCountry() +//国家信息
//                                    aMapLocation.getProvince() +//省信息
//                                    aMapLocation.getCity() +//城市信息
//                                    aMapLocation.getDistrict() +//城区信息
//                                    aMapLocation.getStreet() +//街道信息
//                                    aMapLocation.getStreetNum());//街道门牌号信息);
                        if (!isSlect) {
                            locTv.setText(aMapLocation.getCity());
                        }
                        city = aMapLocation.getCity();
                        //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
                        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 15, 0, 0));
                        aMap.moveCamera(mCameraUpdate);
                        if (!isGetJW) {
                            isGetJW = true;
                            getHead(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                        }
                    } else {
                        locTv.setText("定位失败");
                    }
                }
            }
        });

        quickSearchAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ToastUtils.disPlayShortCenter(getActivity(), searchList.get(position));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        headLocLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectCityActivity.class);
                intent.putExtra("city", city);
                startActivityForResult(intent, SELECT_CITY);
            }
        });

        recyclerGridViewAdapter.setOnRecyclerViewItemListener(new RecyclerGridViewAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (position != classfyTextList.size() - 1) {
                    ToastUtils.disPlayShortCenter(getActivity(), classfyTextList.get(position));
                } else {
                    ToastUtils.disPlayShort(getActivity(), classfyTextList.get(position));
                }
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });

        searchLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        headCarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECT_CITY:
                    isSlect = true;
                    LocationBean bean = (LocationBean) data.getSerializableExtra("bean");
                    locTv.setText(bean.getTitle());
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    private void getHead(double latitude, double longitude) {
        Map<String, String> params = new HashMap<>();
        params.put("latitude", latitude + "");
        params.put("longitude", longitude + "");
        HttpUtils.post(getActivity(), MyConfig.HEAD, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                loadingDialog.exit();
                HeadTop headTop = new Gson().fromJson(response, HeadTop.class);
                if (!TextUtils.isEmpty(headTop.getStatus())) {
                    if (headTop.getStatus().equals("0000")) {
                        for (int i = 0; i < headTop.getData().getCategories().size(); i++) {
                            searchList.add(headTop.getData().getCategories().get(i));
                        }
                        quickSearchAdapter.setDatas(searchList);
                        quickSearchAdapter.notifyDataSetChanged();
                        for (int i = 0; i < headTop.getData().getImgInfo().size(); i++) {
                            classfyTextList.add(headTop.getData().getImgInfo().get(i).getText());
                            classfyImgList1.add(headTop.getData().getImgInfo().get(i).getUrl());
                        }
                        recyclerGridViewAdapter.setNetData(classfyTextList, classfyImgList1);
                        recyclerGridViewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
            }
        });
    }

}


