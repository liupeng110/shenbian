package com.henlinkeji.shenbian.fragments.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.ServiceDetailActivity;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.ui.BaseFragment;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.henlinkeji.shenbian.bean.ClassfyData;
import com.henlinkeji.shenbian.bean.MyInfo;
import com.henlinkeji.shenbian.bean.PersonalService;
import com.henlinkeji.shenbian.fragments.classfy.PeopleClassfyFragment;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineServiceFragment extends BaseFragment {
    @BindView(R.id.no_data)
    TextView dataTv;
    @BindView(R.id.service_recy)
    RecyclerView recyclerView;

    private List<PersonalService.DataBean.ServicesBean> list=new ArrayList<>();

    private CommonAdapter<PersonalService.DataBean.ServicesBean> adapter;

    private int tag=0;

    public MineServiceFragment() {
        tag=0;
    }

    public static MineServiceFragment newInstance(List<PersonalService.DataBean.ServicesBean> list) {
        Bundle args = new Bundle();
        MineServiceFragment fragment = new MineServiceFragment();
        args.putSerializable("list", (Serializable) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_service;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initInstence() {
        Bundle parameters = getArguments();
        if (tag==0) {
            list = (List<PersonalService.DataBean.ServicesBean>) parameters.getSerializable("list");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        adapter = new CommonAdapter<PersonalService.DataBean.ServicesBean>(getActivity(), R.layout.personal_service_item_layout) {
            @Override
            protected void convert(ViewHolder holder, final PersonalService.DataBean.ServicesBean bean, int position) {
                holder.setText(R.id.name, bean.getServiceTitle());
                holder.setText(R.id.content, bean.getServiceDescription());
                if (bean.getHomeUrl() != null) {
                    SimpleDraweeView img = holder.getView(R.id.img);
                    img.setImageURI(Uri.parse(bean.getHomeUrl()));
                }
                holder.setOnClickListener(R.id.hot_sell_rl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
                        intent.putExtra("id", bean.getId());
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
        recyclerView.setAdapter(adapter);
        adapter.setDatas(list);
        if (list.size()<=0){
            dataTv.setVisibility(View.VISIBLE);
        }else {
            dataTv.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initListener() {

    }


}
