package com.henlinkeji.shenbian.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ScrollingView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henlinkeji.shenbian.LoginActivity;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.ui.BaseFragment;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.henlinkeji.shenbian.bean.MineDownList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by smyh on 2015/4/28.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.user_data)
    LinearLayout userDataLin;
    @BindView(R.id.user_avator)
    SimpleDraweeView userAvatorImg;
    @BindView(R.id.mine_recy)
    RecyclerView mineRecy;
    @BindView(R.id.published_lin)
    LinearLayout publishedLin;
    @BindView(R.id.published_tv)
    TextView publishedTv;
    @BindView(R.id.visited_lin)
    LinearLayout visitedLin;
    @BindView(R.id.visited_tv)
    TextView visitedTv;
    @BindView(R.id.attentioned_lin)
    LinearLayout attentionedLin;
    @BindView(R.id.attentioned_tv)
    TextView attentionedTv;
    @BindView(R.id.followed_lin)
    LinearLayout followedLin;
    @BindView(R.id.followed_tv)
    TextView followedTv;
    @BindView(R.id.my_article_ll)
    LinearLayout myArticleLin;
    @BindView(R.id.my_service_ll)
    LinearLayout myServiceLin;

    private CommonAdapter<MineDownList> adapter;
    private List<MineDownList> mineDownLists=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_four;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initInstence() {
        mineRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new CommonAdapter<MineDownList>(getActivity(),R.layout.mine_down_list_item_layout) {
            @Override
            protected void convert(ViewHolder holder, MineDownList mineDownList, int position) {
                holder.setText(R.id.title,mineDownList.getTitle());
            }
        };
        mineRecy.setAdapter(adapter);
        userDataLin.setFocusableInTouchMode(true);
        userDataLin.requestFocus();
    }

    @Override
    protected void initData() {
        MineDownList mineDownList1=new MineDownList();
        MineDownList mineDownList2=new MineDownList();
        MineDownList mineDownList3=new MineDownList();
        MineDownList mineDownList4=new MineDownList();
        MineDownList mineDownList5=new MineDownList();
        MineDownList mineDownList6=new MineDownList();
        MineDownList mineDownList7=new MineDownList();
        mineDownList1.setTitle("消息通知");
        mineDownList2.setTitle("我的收入");
        mineDownList3.setTitle("我的订单");
        mineDownList4.setTitle("资料设置");
        mineDownList5.setTitle("安全与隐私");
        mineDownList6.setTitle("反馈");
        mineDownList7.setTitle("帮助");
        mineDownLists.add(mineDownList1);
        mineDownLists.add(mineDownList2);
        mineDownLists.add(mineDownList3);
        mineDownLists.add(mineDownList4);
        mineDownLists.add(mineDownList5);
        mineDownLists.add(mineDownList6);
        mineDownLists.add(mineDownList7);
        adapter.setDatas(mineDownLists);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        userAvatorImg.setOnClickListener(this);
        publishedLin.setOnClickListener(this);
        visitedLin.setOnClickListener(this);
        attentionedLin.setOnClickListener(this);
        followedLin.setOnClickListener(this);
        myArticleLin.setOnClickListener(this);
        myServiceLin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_avator:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    ToastUtils.disPlayShort(getActivity(),"用户头像");
                }
                break;
            case R.id.published_lin:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    ToastUtils.disPlayShort(getActivity(),"已发布");
                }
                break;
            case R.id.visited_lin:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    ToastUtils.disPlayShort(getActivity(),"7日访客");
                }
                break;
            case R.id.attentioned_lin:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    ToastUtils.disPlayShort(getActivity(),"关注");
                }
                break;
            case R.id.followed_lin:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    ToastUtils.disPlayShort(getActivity(),"粉丝");
                }
                break;
            case R.id.my_article_ll:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    ToastUtils.disPlayShort(getActivity(),"我的文章");
                }
                break;
            case R.id.my_service_ll:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    ToastUtils.disPlayShort(getActivity(),"我的服务");
                }
                break;
        }
    }
}

