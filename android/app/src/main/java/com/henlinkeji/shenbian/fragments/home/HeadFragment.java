package com.henlinkeji.shenbian.fragments.home;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.ArticleDetailActivity;
import com.henlinkeji.shenbian.LoginActivity;
import com.henlinkeji.shenbian.LookServiceActivity;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.SearchActivity;
import com.henlinkeji.shenbian.SelectCityActivity;
import com.henlinkeji.shenbian.ServiceDetailActivity;
import com.henlinkeji.shenbian.ShoppingCartActivity;
import com.henlinkeji.shenbian.adapter.RecyclerGridViewAdapter;
import com.henlinkeji.shenbian.base.amap.LocationBean;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.base.view.FullyLinearLayoutManager;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.MultiItemTypeAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.henlinkeji.shenbian.bean.HeadBottom;
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
    @BindView(R.id.chaozhi_img1)
    SimpleDraweeView chaoZhiImg1;
    @BindView(R.id.chaozhi_img2)
    SimpleDraweeView chaoZhiImg2;

    private CommonAdapter<String> quickSearchAdapter;
    private CommonAdapter<HeadBottom.DataBean.VosBean> hotSellAdapter;
    private List<String> searchList = new ArrayList<>();

    private List<String> classfyTextList = new ArrayList<>();

    private List<String> classfyImgList1 = new ArrayList<>();
    private List<Integer> classfyImgList2 = new ArrayList<>();

    private List<HeadBottom.DataBean.VosBean> hotSellList = new ArrayList<>();

    private AMapLocationClient mlocationClient = null;
    private LoadingDialog loadingDialog;

    private static final int SELECT_CITY = 3; // 请求码

    private boolean isSlect = false;

    private String city;
    private double currentLatitude;
    private double currentLongitude;

    private RecyclerGridViewAdapter recyclerGridViewAdapter;

    //初始化地图控制器对象
    private AMap aMap;

    private boolean isGetJW = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one, null);
        ButterKnife.bind(this, view);
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
        classfyTextList.clear();
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

        hotSellAdapter = new CommonAdapter<HeadBottom.DataBean.VosBean>(getActivity(), R.layout.hot_sell_item_layout) {
            @Override
            protected void convert(ViewHolder holder, final HeadBottom.DataBean.VosBean hotSell, int position) {
                holder.setText(R.id.name, hotSell.getServiceTitle());
                holder.setText(R.id.content, hotSell.getServiceDescription());
                holder.setText(R.id.star_count, hotSell.getStarRating() + "(" + hotSell.getSoldCount() + ")");
                holder.setText(R.id.sell_count, "已售" + hotSell.getSoldCount() + "件");
                if (hotSell.getUserTags() != null) {
                    holder.setText(R.id.mark, hotSell.getUserTags());
                } else {
                    holder.setVisible(R.id.mark, false);
                }
                LatLng latLng1 = new LatLng(currentLatitude, currentLongitude);
                if (hotSell.getLocation()!=null) {
                    String[] locs = hotSell.getLocation().split(",");
                    LatLng latLng2 = null;
                    if (locs.length >= 1) {
                        latLng2 = new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0]));
                    }
                    float dis = AMapUtils.calculateLineDistance(latLng1, latLng2);
                    if (dis < 1000) {
                        holder.setText(R.id.distance, dis + "m");
                    } else {
                        holder.setText(R.id.distance, Utils.mToKm(dis));
                    }
                }
                if (hotSell.getHomeUrl() != null) {
                    SimpleDraweeView img = holder.getView(R.id.img);
                    img.setImageURI(Uri.parse(hotSell.getHomeUrl()));
                }
                holder.setOnClickListener(R.id.hot_sell_rl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),ServiceDetailActivity.class);
                        intent.putExtra("id",hotSell.getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView) {
                super.onViewHolderCreated(holder, itemView);
                AutoUtils.autoSize(itemView);
            }
        };
        hotRecy.setAdapter(hotSellAdapter);

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
                        currentLatitude = aMapLocation.getLatitude();//获取纬度
                        currentLongitude = aMapLocation.getLongitude();//获取经度
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
//                        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 15, 0, 0));
//                        aMap.moveCamera(mCameraUpdate);
                        if (!isGetJW) {
                            isGetJW = true;
                            getHead();
                            getHeadBottom();
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
                if (position == 0) {
                    startActivity(new Intent(getActivity(), LookServiceActivity.class));
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
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    ToastUtils.disPlayShort(getActivity(), "购物车");
                }
            }
        });
        chaoZhiImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ArticleDetailActivity.class));
            }
        });
        chaoZhiImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ServiceDetailActivity.class));
            }
        });

        headCarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShoppingCartActivity.class));
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

    private void getHead() {
        Map<String, String> params = new HashMap<>();
        params.put("city", city);
        params.put("center", currentLatitude + "," + currentLongitude);
        params.put("center", "116.542951,39.639531");
        HttpUtils.post(getActivity(), MyConfig.HEAD_TOP, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                loadingDialog.exit();
                classfyTextList.clear();
                classfyImgList1.clear();
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
                        for (int i = 0; i < headTop.getData().getPositions().size(); i++) {
                            String loc = headTop.getData().getPositions().get(i).get_location();
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
                                CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0])), 10, 0, 0));
                                aMap.moveCamera(mCameraUpdate);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
            }
        });
    }

    private void getHeadBottom() {
        Map<String, String> params = new HashMap<>();
        params.put("city", city);
//        params.put("center", currentLatitude + "," + currentLongitude);
        params.put("center", "116.542951,39.639531");
        params.put("page", 1 + "");
        HttpUtils.post(getActivity(), MyConfig.HEAD_BOTTOM, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                HeadBottom headBottom = new Gson().fromJson(response, HeadBottom.class);
                if (headBottom.getStatus().equals("0000")) {
                    hotSellList = headBottom.getData().getVos();
                    hotSellAdapter.setDatas(hotSellList);
                    List<HeadBottom.DataBean.GreatValueBean> greatValueBeanList = headBottom.getData().getGreatValue();
                    if (greatValueBeanList.size() >= 2) {
                        if (greatValueBeanList.get(0).getHomeUrl() != null)
                            chaoZhiImg2.setImageURI(Uri.parse(greatValueBeanList.get(1).getHomeUrl()));
                        if (greatValueBeanList.get(1).getHomeUrl() != null)
                            chaoZhiImg1.setImageURI(Uri.parse(greatValueBeanList.get(0).getHomeUrl()));
                    }
                }
                loadingDialog.exit();
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
            }
        });
    }

}


