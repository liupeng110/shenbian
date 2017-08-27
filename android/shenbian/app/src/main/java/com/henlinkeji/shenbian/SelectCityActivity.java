package com.henlinkeji.shenbian;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.henlinkeji.shenbian.base.amap.InputTipTask;
import com.henlinkeji.shenbian.base.amap.LocationBean;
import com.henlinkeji.shenbian.base.amap.PoiSearchTask;
import com.henlinkeji.shenbian.base.listener.EndLessOnScrollListener;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.MultiItemTypeAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCityActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener {

    @BindView(R.id.lv_location_nearby)
    RecyclerView mapList;
    @BindView(R.id.no_result)
    TextView noResultTv;
    @BindView(R.id.head_cancel_lin)
    LinearLayout cancelLin;
    @BindView(R.id.head_loc_lin)
    LinearLayout locLin;
    @BindView(R.id.head_loc_tv)
    TextView locTv;
    @BindView(R.id.head_search_edt)
    EditText searchEdt;

    private LoadingDialog loadingDialog;

    private String city;
    private String keyWord;
    private int page = 1;

    private CommonAdapter<LocationBean> adapter;

    private List<LocationBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_city);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        mapList.setLayoutManager(new LinearLayoutManager(this));
        loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("身边");
        if (getIntent() != null) {
            city = getIntent().getStringExtra("city");
        }
        adapter = new CommonAdapter<LocationBean>(this, R.layout.amap_poi_item_layout) {
            @Override
            protected void convert(ViewHolder holder, LocationBean locationBean, int position) {
                holder.setText(R.id.address, locationBean.getTitle());
                holder.setText(R.id.address_desc, locationBean.getContent());
                holder.setVisible(R.id.right, false);
            }
        };
        mapList.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        keyWord = city;
        search();
    }

    private void search() {
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", city);
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(page);//设置查询页码

        locTv.setText(city);
    }

    @Override
    protected void initListener() {
        cancelLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    return;
                } else {
                    keyWord = s.toString().trim();
                    page = 1;
                    datas.clear();
                    search();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        locLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(v);
            }
        });
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                //POI的地址的listview的item的点击
                LocationBean slectLoc = datas.get(position);
                Intent intent = new Intent();
                intent.putExtra("bean", slectLoc);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mapList.addOnScrollListener(new EndLessOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {
                page++;
                search();
            }
        });
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {
                ArrayList<PoiItem> items = poiResult.getPois();
                ArrayList<LocationBean> list = new ArrayList<>();
                for (PoiItem item : items) {
                    //获取经纬度对象
                    LatLonPoint llp = item.getLatLonPoint();
                    double lon = llp.getLongitude();
                    double lat = llp.getLatitude();
                    //获取标题
                    String title = item.getTitle();
                    //获取内容
                    String text = item.getSnippet();
                    list.add(new LocationBean(lon, lat, title, text));
                }
                datas.addAll(datas.size(), list);
                loadingDialog.exit();
                adapter.setDatas(datas);
                if (datas.size() > 0) {
                    mapList.setVisibility(View.VISIBLE);
                    noResultTv.setVisibility(View.GONE);
                } else {
                    mapList.setVisibility(View.GONE);
                    noResultTv.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    private void showPop(View view) {
        View mMenuView = LayoutInflater.from(this).inflate(R.layout.city_list_layout, null);
        final PopupWindow popWindow = new PopupWindow(mMenuView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置按钮监听
        RecyclerView recyclerView = (RecyclerView) mMenuView.findViewById(R.id.city_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(SelectCityActivity.this));
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popWindow.setBackgroundDrawable(dw);
        final List<String> list = new ArrayList<>();
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");
        list.add("南京");
        list.add("苏州");
        list.add("无锡");
        CommonAdapter<String> adapter = new CommonAdapter<String>(SelectCityActivity.this, R.layout.city_list_item_layout) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.text, s);
            }
        };
        adapter.setDatas(list);
        recyclerView.setAdapter(adapter);
        //获取点击View的坐标
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popWindow.showAsDropDown(view);//在v的下面
        //显示在下方
        popWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth(), location[1]);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                city = list.get(position);
                keyWord = city;
                page = 1;
                datas.clear();
                search();
                popWindow.dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setBackgroundAlpha(SelectCityActivity.this, 1f);
            }
        });
        //设置背景半透明
        Utils.setBackgroundAlpha(this, 0.5f);
    }

}
