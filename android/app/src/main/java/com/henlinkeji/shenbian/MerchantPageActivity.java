package com.henlinkeji.shenbian;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henlinkeji.shenbian.adapter.ServiceTabVpAdapter;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.fragments.classfy.ServiceClassfyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MerchantPageActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.right)
    TextView attentionTv;
    @BindView(R.id.back)
    ImageView backIv;
    @BindView(R.id.avator)
    ImageView avatorIv;

    @BindView(R.id.page_tablayout)
    TabLayout pageTab;
    @BindView(R.id.page_viewpager)
    ViewPager pageVp;

    private List<Fragment> fragmentList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_merchant_page);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {

    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();
        titleList.add("文章");
        titleList.add("服务");

        for (int i = 0; i <titleList.size() ; i++) {
            fragmentList.add(ServiceClassfyFragment.newInstance(titleList.get(i)));
            pageTab.addTab(pageTab.newTab().setText(titleList.get(i)));
        }
    }

    @Override
    protected void initListener() {
        pageTab.setTabMode(TabLayout.MODE_FIXED);

        ServiceTabVpAdapter fAdapter = new ServiceTabVpAdapter(getSupportFragmentManager(),fragmentList,titleList);
        pageVp.setAdapter(fAdapter);
        pageTab.setupWithViewPager(pageVp);

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
