package com.henlinkeji.shenbian.fragments.classfy;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.amap.api.maps.model.MyLocationStyle;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.view.GridViewForScrollView;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceClassfyFragment extends Fragment {
    private String name;
    @BindView(R.id.recy)
    RecyclerView recyclerView;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.selected)
    GridViewForScrollView gridViewForScrollView;

    //初始化地图控制器对象
    private AMap aMap;
    private AMapLocationClient mlocationClient = null;

    private CommonAdapter<String> recyAdapter;

    public static ServiceClassfyFragment newInstance(String text) {
        Bundle args = new Bundle();
        ServiceClassfyFragment fragment = new ServiceClassfyFragment();
        args.putString("name", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_service_classfy, null);
        ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        initInstence();
        initData();
        initListener();
        return view;
    }


    protected void initInstence() {
        Bundle parameters = getArguments();
        name = parameters.getString("name");

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

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
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(100000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setZoomControlsEnabled(false);
    }

    protected void initData() {
        recyAdapter = new CommonAdapter<String>(getActivity(), R.layout.classfy_recy_item_layout) {
            @Override
            protected void convert(ViewHolder holder, String bean, int position) {
                holder.setText(R.id.title, bean);
            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView) {
                super.onViewHolderCreated(holder, itemView);
                AutoUtils.autoSize(itemView);
            }
        };
        recyclerView.setAdapter(recyAdapter);

        List<String> list=new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            list.add("哈哈哈"+i);
        }
        recyAdapter.setDatas(list);
        recyAdapter.notifyDataSetChanged();

        gridViewForScrollView.setAdapter(new MyAdapter(getActivity(),list));
    }

    protected void initListener() {
        //设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
                        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 15, 0, 0));
                        aMap.moveCamera(mCameraUpdate);
                    }
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private List<String> list;

        MyAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        public int getCount() {
            return list.size();
        }

        public Object getItem(int item) {
            return list.get(item);
        }

        public long getItemId(int id) {
            return id;
        }

        //创建View方法
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.classfy_selected_item_layout, null);
                viewHolder = new MyAdapter.ViewHolder();
                viewHolder.nameTv = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (MyAdapter.ViewHolder) convertView.getTag();
            }
            viewHolder.nameTv.setText(list.get(position));
            return convertView;
        }

        class ViewHolder {
            public TextView nameTv;
        }
    }
}
