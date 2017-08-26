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
    ListView mapList;
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

    private PoiAdapter poiAdapter;

    private LoadingDialog loadingDialog;

    private String city;

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
        loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("身边");
        if (getIntent() != null) {
            city = getIntent().getStringExtra("city");
        }
    }

    @Override
    protected void initData() {
        search(city);
    }

    private void search(String keyWord) {
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", city);
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();

        poiAdapter = new PoiAdapter(this);
        mapList.setAdapter(poiAdapter);

        locTv.setText(city);
    }

    @Override
    protected void initListener() {
        mapList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //POI的地址的listview的item的点击
                LocationBean slectLoc = (LocationBean) poiAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("bean", slectLoc);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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
                    search(s.toString().trim());
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
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {
                ArrayList<LocationBean> datas = new ArrayList<>();
                ArrayList<PoiItem> items = poiResult.getPois();
                for (PoiItem item : items) {
                    //获取经纬度对象
                    LatLonPoint llp = item.getLatLonPoint();
                    double lon = llp.getLongitude();
                    double lat = llp.getLatitude();
                    //获取标题
                    String title = item.getTitle();
                    //获取内容
                    String text = item.getSnippet();
                    datas.add(new LocationBean(lon, lat, title, text));
                }
                poiAdapter.setData(datas);
                poiAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    //POI搜索显示地址adapter
    public class PoiAdapter extends BaseAdapter {

        private List<LocationBean> datas = new ArrayList<>();

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
            vh.rightImg.setVisibility(View.GONE);
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
                mapList.setVisibility(View.VISIBLE);
                noResultTv.setVisibility(View.GONE);
            } else {
                mapList.setVisibility(View.GONE);
                noResultTv.setVisibility(View.VISIBLE);
            }
        }
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
                search(city);
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
