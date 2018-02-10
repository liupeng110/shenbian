package com.henlinkeji.shenbian.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.amap.api.maps.model.MyLocationStyle;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.AllClassfyActivity;
import com.henlinkeji.shenbian.ArticleDetailActivity;
import com.henlinkeji.shenbian.LoginActivity;
import com.henlinkeji.shenbian.LookMapActivity;
import com.henlinkeji.shenbian.LookOtherActivity;
import com.henlinkeji.shenbian.LookPeopleActivity;
import com.henlinkeji.shenbian.LookServiceActivity;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.SearchActivity;
import com.henlinkeji.shenbian.SelectCityActivity;
import com.henlinkeji.shenbian.ServiceDetailActivity;
import com.henlinkeji.shenbian.ShoppingCartActivity;
import com.henlinkeji.shenbian.adapter.MineDownAdapter;
import com.henlinkeji.shenbian.adapter.RecyclerGridViewAdapter;
import com.henlinkeji.shenbian.base.amap.LocationBean;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.base.view.FullyLinearLayoutManager;
import com.henlinkeji.shenbian.base.view.ListViewForScrollView;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.MultiItemTypeAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.henlinkeji.shenbian.bean.GetUpToken;
import com.henlinkeji.shenbian.bean.HeadBottom;
import com.henlinkeji.shenbian.bean.HeadTop;
import com.henlinkeji.shenbian.bean.HotSell;
import com.henlinkeji.shenbian.bean.MineDownList;
import com.henlinkeji.shenbian.refresh.VRefreshLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
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
    ListViewForScrollView hotRecy;
    @BindView(R.id.chaozhi_img1)
    SimpleDraweeView chaoZhiImg1;
    @BindView(R.id.chaozhi_img2)
    SimpleDraweeView chaoZhiImg2;
    @BindView(R.id.see_map_rl)
    RelativeLayout seeMapRl;
    @BindView(R.id.refresh_layout)
    VRefreshLayout vRefreshLayout;

    private CommonAdapter<String> quickSearchAdapter;
//    private CommonAdapter<HeadBottom.DataBean.VosBean> hotSellAdapter;
    private List<String> searchList = new ArrayList<>();

    private List<String> classfyTextList = new ArrayList<>();

    private List<String> classfyImgList1 = new ArrayList<>();
    private List<Integer> classfyImgList2 = new ArrayList<>();
    private List<Integer> classfyIdList = new ArrayList<>();

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

    private int chaozhi1;
    private int chaozhi2;

    List<HeadTop.DataBean.PositionsBean> posList = new ArrayList<>();

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
//        hotRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        mlocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //设置定位间隔,单位毫秒,默认为100000ms
//        mLocationOption.setInterval(100000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
        loadingDialog = new LoadingDialog(getActivity(), true);
        loadingDialog.show("");
    }

    protected void initData() {
        classfyTextList.clear();
        classfyTextList.add("教育学习");
        classfyTextList.add("生活服务");
        classfyTextList.add("艺术培养");
        classfyTextList.add("工作辅导");
//        classfyTextList.add("找租房");
//        classfyTextList.add("学技能");
//        classfyTextList.add("修手机、电脑");
//        classfyTextList.add("全部");
        classfyImgList2.clear();
        classfyImgList2.add(R.mipmap.zhaofuwu);
        classfyImgList2.add(R.mipmap.zhaoren);
        classfyImgList2.add(R.mipmap.zhaohuodong);
        classfyImgList2.add(R.mipmap.zhaogongzuo);
//        classfyImgList2.add(R.mipmap.zhaozufang);
//        classfyImgList2.add(R.mipmap.xuejineng);
//        classfyImgList2.add(R.mipmap.xiushouji);
//        classfyImgList2.add(R.mipmap.quanbu);

        classfyIdList.clear();
        classfyIdList.add(1);
        classfyIdList.add(2);
        classfyIdList.add(3);
        classfyIdList.add(4);

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

//        hotSellAdapter = new CommonAdapter<HeadBottom.DataBean.VosBean>(getActivity(), R.layout.hot_sell_item_layout) {
//            @Override
//            protected void convert(ViewHolder holder, final HeadBottom.DataBean.VosBean hotSell, int position) {
//                holder.setText(R.id.name, hotSell.getServiceTitle());
//                holder.setText(R.id.content, hotSell.getServiceDescription());
//                holder.setText(R.id.star_count, hotSell.getStarRating() + "(" + hotSell.getSoldCount() + ")");
//                holder.setText(R.id.sell_count, "已售" + hotSell.getSoldCount() + "件");
//                if (hotSell.getUserTags() != null) {
//                    holder.setText(R.id.mark, hotSell.getUserTags());
//                } else {
//                    holder.setVisible(R.id.mark, false);
//                }
//                LatLng latLng1 = new LatLng(currentLatitude, currentLongitude);
//                if (!TextUtils.isEmpty(hotSell.getLocation())) {
//                    String[] locs = hotSell.getLocation().split(",");
//                    LatLng latLng2 = null;
//                    if (locs.length >= 1) {
//                        latLng2 = new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0]));
//                    }
//                    float dis = AMapUtils.calculateLineDistance(latLng1, latLng2);
//                    if (dis < 1000) {
//                        DecimalFormat decimalFormat = new DecimalFormat("0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
//                        holder.setText(R.id.distance, decimalFormat.format(dis) + "m");
//                    } else {
//                        holder.setText(R.id.distance, Utils.mToKm(dis));
//                    }
//                }
//                if (hotSell.getHomeUrl() != null) {
//                    SimpleDraweeView img = holder.getView(R.id.img);
//                    img.setImageURI(Uri.parse(hotSell.getHomeUrl()));
//                }
//                holder.setOnClickListener(R.id.hot_sell_rl, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
//                        intent.putExtra("id", hotSell.getId());
//                        startActivity(intent);
//                    }
//                });
//            }
//
//            @Override
//            public void onViewHolderCreated(ViewHolder holder, View itemView) {
//                super.onViewHolderCreated(holder, itemView);
//                AutoUtils.autoSize(itemView);
//            }
//        };
//        hotRecy.setAdapter(hotSellAdapter);

        if (vRefreshLayout != null) {
            vRefreshLayout.setHeaderView(vRefreshLayout.getDefaultHeaderView());
            vRefreshLayout.setBackgroundColor(Color.WHITE);
            vRefreshLayout.setAutoRefreshDuration(400);
            vRefreshLayout.setRatioOfHeaderHeightToReach(1.5f);
            vRefreshLayout.addOnRefreshListener(new VRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (Utils.isNetworkAvailable(getActivity())) {
                        getHead();
                        getHeadBottom();
                    } else {
                        vRefreshLayout.refreshComplete();
                        Toast.makeText(getActivity(), "当前网络不可用，请检查网络", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
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
                        SPUtils.setLatitude(currentLatitude + "", getActivity());
                        SPUtils.setLongitude(currentLongitude + "", getActivity());
                        if (!isSlect) {
                            locTv.setText(aMapLocation.getCity());
                        }
                        city = aMapLocation.getCity();
                        SPUtils.setCity(city, getActivity());
                        if (!isGetJW) {
                            isGetJW = true;
                            getHead();
                            getHeadBottom();
                        }
                    } else {
                        mlocationClient.startLocation();
                        locTv.setText("定位失败");
                    }
                }
            }
        });

        quickSearchAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                ToastUtils.disPlayShortCenter(getActivity(), searchList.get(position));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        headLocLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SelectCityActivity.class);
//                intent.putExtra("city", city);
//                startActivityForResult(intent, SELECT_CITY);
            }
        });

        recyclerGridViewAdapter.setOnRecyclerViewItemListener(new RecyclerGridViewAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
//                if (position == 0) {
//                    startActivity(new Intent(getActivity(), LookServiceActivity.class));
//                } else if (position == 1) {
                Intent intent = new Intent(getActivity(), LookPeopleActivity.class);
                intent.putExtra("center", "116.542951,39.639531");
                intent.putExtra("city", city);
                intent.putExtra("id", classfyIdList.get(position));
                intent.putExtra("name", classfyTextList.get(position));
                startActivity(intent);
//                }else if (position==7){
//                    startActivity(new Intent(getActivity(), AllClassfyActivity.class));
//                }else {
//                    startActivity(new Intent(getActivity(), LookOtherActivity.class));
//                }
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
                    startActivity(new Intent(getActivity(), ShoppingCartActivity.class));
                }
            }
        });
        chaoZhiImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
                intent.putExtra("id", chaozhi1);
                startActivity(intent);
            }
        });
        chaoZhiImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
                intent.putExtra("id", chaozhi2);
                startActivity(intent);
            }
        });

        seeMapRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LookMapActivity.class);
                if (posList != null) {
                    if (posList.size() > 0) {
                        intent.putExtra("list", (Serializable) posList);
                    }
                }
                startActivity(intent);
            }
        });
        mMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LookMapActivity.class);
                if (posList != null) {
                    if (posList.size() > 0) {
                        intent.putExtra("list", (Serializable) posList);
                    }
                }
                startActivity(intent);
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
        params.put("center", currentLongitude + "," + currentLatitude);
        HttpUtils.post(getActivity(), MyConfig.HEAD_TOP, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                loadingDialog.exit();
                classfyTextList.clear();
                classfyImgList1.clear();
                classfyIdList.clear();
                searchList.clear();
                vRefreshLayout.refreshComplete();
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
                            classfyIdList.add(headTop.getData().getImgInfo().get(i).getClassificationId());
                        }
                        recyclerGridViewAdapter.setNetData(classfyTextList, classfyImgList1);
                        recyclerGridViewAdapter.notifyDataSetChanged();
                        posList = headTop.getData().getPositions();
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
                        if (posList.size() >= 1) {
                            String loc = posList.get(0).get_location();
                            String[] locs = loc.split(",");
                            //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
                            CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0])), 15, 0, 0));
                            aMap.moveCamera(mCameraUpdate);
                        }
                    }
                }
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
                vRefreshLayout.refreshComplete();
            }
        });
    }

    private void getHeadBottom() {
        Map<String, String> params = new HashMap<>();
        params.put("city", city);
        params.put("center", currentLongitude + "," + currentLatitude);
        params.put("page", 1 + "");
        HttpUtils.post(getActivity(), MyConfig.HEAD_BOTTOM, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                HeadBottom headBottom = new Gson().fromJson(response, HeadBottom.class);
                if (headBottom.getStatus().equals("0000")) {
                    hotSellList = headBottom.getData().getVos();
//                    hotSellAdapter.setDatas(hotSellList);
                    hotRecy.setAdapter(new HotAdapter(hotSellList,getActivity()) );
                    List<HeadBottom.DataBean.GreatValueBean> greatValueBeanList = headBottom.getData().getGreatValue();
                    if (greatValueBeanList != null) {
                        if (greatValueBeanList.size() >= 2) {
                            chaoZhiImg2.setVisibility(View.VISIBLE);
                            chaoZhiImg1.setVisibility(View.VISIBLE);
                            if (greatValueBeanList.get(1).getHomeUrl() != null) {
                                chaoZhiImg2.setImageURI(Uri.parse(greatValueBeanList.get(1).getHomeUrl()));
                            } else {
                                chaoZhiImg2.setVisibility(View.GONE);
                            }
                            if (greatValueBeanList.get(0).getHomeUrl() != null) {
                                chaoZhiImg1.setImageURI(Uri.parse(greatValueBeanList.get(0).getHomeUrl()));
                            } else {
                                chaoZhiImg1.setVisibility(View.GONE);
                            }
                            chaozhi1 = greatValueBeanList.get(0).getId();
                            chaozhi2 = greatValueBeanList.get(1).getId();
                        } else if (greatValueBeanList.size() == 1) {
                            chaoZhiImg2.setVisibility(View.INVISIBLE);
                            chaoZhiImg1.setVisibility(View.VISIBLE);
                            if (greatValueBeanList.get(0).getHomeUrl() != null) {
                                chaoZhiImg1.setImageURI(Uri.parse(greatValueBeanList.get(0).getHomeUrl()));
                            }
                            chaozhi1 = greatValueBeanList.get(0).getId();
                        } else if (greatValueBeanList.size() == 0) {
                            chaoZhiImg2.setVisibility(View.GONE);
                            chaoZhiImg1.setVisibility(View.GONE);
                        }
                    } else {
                        chaoZhiImg2.setVisibility(View.GONE);
                        chaoZhiImg1.setVisibility(View.GONE);
                    }
                }
                loadingDialog.exit();
                vRefreshLayout.refreshComplete();
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
                vRefreshLayout.refreshComplete();
            }
        });
    }

    class HotAdapter extends BaseAdapter {

        private List<HeadBottom.DataBean.VosBean> mineDownLists;
        private LayoutInflater inflater;

        public HotAdapter(List<HeadBottom.DataBean.VosBean> list, Context context) {
            this.mineDownLists = list;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mineDownLists == null ? 0 : mineDownLists.size();
        }

        @Override
        public HeadBottom.DataBean.VosBean getItem(int position) {
            return mineDownLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.hot_sell_item_layout, null);
                holder = new ViewHolder();
                    /*得到各个控件的对象*/
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.content = (TextView) convertView.findViewById(R.id.content);
                holder.star_count = (TextView) convertView.findViewById(R.id.star_count);
                holder.sell_count = (TextView) convertView.findViewById(R.id.sell_count);
                holder.mark = (TextView) convertView.findViewById(R.id.mark);
                holder.distance = (TextView) convertView.findViewById(R.id.distance);
                holder.hot_sell_rl = (RelativeLayout) convertView.findViewById(R.id.hot_sell_rl);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                   }
            }
            final HeadBottom.DataBean.VosBean hotSell = mineDownLists.get(position);
            holder.name.setText(hotSell.getServiceTitle());
            holder.content.setText(hotSell.getServiceDescription());
            holder.star_count.setText(hotSell.getStarRating() + "(" + hotSell.getSoldCount() + ")");
            holder.sell_count.setText("已售" + hotSell.getSoldCount() + "件");
            if (hotSell.getUserTags() != null) {
                holder.mark.setText(hotSell.getUserTags());
            } else {
                holder.mark.setVisibility(View.GONE);
            }
            LatLng latLng1 = new LatLng(currentLatitude, currentLongitude);
            if (!TextUtils.isEmpty(hotSell.getLocation())) {
                String[] locs = hotSell.getLocation().split(",");
                LatLng latLng2 = null;
                if (locs.length >= 1) {
                    latLng2 = new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0]));
                }
                float dis = AMapUtils.calculateLineDistance(latLng1, latLng2);
                if (dis < 1000) {
                    DecimalFormat decimalFormat = new DecimalFormat("0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                    holder.distance.setText(decimalFormat.format(dis) + "m");
                } else {
                    holder.distance.setText(Utils.mToKm(dis));
                }
            }
            if (hotSell.getHomeUrl() != null) {
                holder.img.setImageURI(Uri.parse(hotSell.getHomeUrl()));
            }
            holder.hot_sell_rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
                    intent.putExtra("id", hotSell.getId());
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class ViewHolder {
            public TextView name;
            public TextView content;
            public TextView star_count;
            public TextView sell_count;
            public TextView mark;
            public TextView distance;
            public ImageView img;
            public RelativeLayout hot_sell_rl;
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}


