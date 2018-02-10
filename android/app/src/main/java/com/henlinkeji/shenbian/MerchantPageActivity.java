package com.henlinkeji.shenbian;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.adapter.ServiceTabVpAdapter;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.bean.GetUpToken;
import com.henlinkeji.shenbian.bean.PersonalService;
import com.henlinkeji.shenbian.fragments.classfy.ServiceClassfyFragment;
import com.henlinkeji.shenbian.fragments.mine.MineArticleFragment;
import com.henlinkeji.shenbian.fragments.mine.MineServiceFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    SimpleDraweeView avatorIv;

    @BindView(R.id.page_tablayout)
    TabLayout pageTab;
    @BindView(R.id.page_viewpager)
    ViewPager pageVp;

    private List<Fragment> fragmentList;
    private List<String> titleList;

    private int pos;

    private String userId;

    private int attentionFlag;

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
        MyApplication.getInstance().addActivity(this);
        if (getIntent() != null) {
            pos = getIntent().getIntExtra("pos", 0);
        }
        if (getIntent().hasExtra("id")) {
            userId = getIntent().getStringExtra("id");
        } else {
            userId = SPUtils.getUserId(this);
        }
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();
        titleList.add("服务");
        titleList.add("文章");

        getData();

        for (int i = 0; i < titleList.size(); i++) {
            pageTab.addTab(pageTab.newTab().setText(titleList.get(i)));
        }
    }

    @Override
    protected void initListener() {
        pageTab.setTabMode(TabLayout.MODE_FIXED);

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        attentionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtils.getToken(MerchantPageActivity.this))) {
                    startActivity(new Intent(MerchantPageActivity.this, LoginActivity.class));
                } else {
                    if (attentionFlag == 0) {
                        addAttention(1);
                    } else {
                        addAttention(0);
                    }
                }
            }
        });
    }

    private void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(this));
        params.put("userId", userId);
        HttpUtils.post(this, MyConfig.GET_SERVICE, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                PersonalService personalService = new Gson().fromJson(response, PersonalService.class);
                if (personalService.getStatus().equals("0000")) {
                    fragmentList.clear();
                    fragmentList.add(MineServiceFragment.newInstance(personalService.getData().getServices()));
                    fragmentList.add(new MineArticleFragment());
                    if (personalService.getData().getConcernFlag().equals("-1")) {
                        attentionTv.setVisibility(View.INVISIBLE);
                    } else if (personalService.getData().getConcernFlag().equals("0")) {
                        attentionTv.setVisibility(View.VISIBLE);
                        attentionFlag = 0;
                        attentionTv.setText("+ 关注");
                    } else {
                        attentionTv.setVisibility(View.VISIBLE);
                        attentionFlag = 1;
                        attentionTv.setText("已关注");
                    }
                    titleTv.setText(personalService.getData().getUserName());
                    if (!TextUtils.isEmpty(personalService.getData().getUserIcon())) {
                        avatorIv.setImageURI(Uri.parse(personalService.getData().getUserIcon()));
                    }
                    ServiceTabVpAdapter fAdapter = new ServiceTabVpAdapter(getSupportFragmentManager(), fragmentList, titleList);
                    pageVp.setAdapter(fAdapter);
                    pageTab.setupWithViewPager(pageVp);

                    pageVp.setCurrentItem(pos);
                } else {
                    fragmentList.clear();
                    fragmentList.add(new MineServiceFragment());
                    fragmentList.add(new MineArticleFragment());
                    ServiceTabVpAdapter fAdapter = new ServiceTabVpAdapter(getSupportFragmentManager(), fragmentList, titleList);
                    pageVp.setAdapter(fAdapter);
                    pageTab.setupWithViewPager(pageVp);

                    pageVp.setCurrentItem(pos);
                }
            }

            @Override
            public void onFailure(String response) {
                fragmentList.clear();
                fragmentList.add(new MineServiceFragment());
                fragmentList.add(new MineArticleFragment());
                ServiceTabVpAdapter fAdapter = new ServiceTabVpAdapter(getSupportFragmentManager(), fragmentList, titleList);
                pageVp.setAdapter(fAdapter);
                pageTab.setupWithViewPager(pageVp);

                pageVp.setCurrentItem(pos);
            }
        });

    }

    private void addAttention(final int collectionStatus) {
        final LoadingDialog loadingDialog = new LoadingDialog(this, true);
        if (collectionStatus == 0) {
            loadingDialog.show("取消关注中……");
        } else {
            loadingDialog.show("添加关注中……");
        }
        Map<String, String> params = new HashMap<>();
        params.put("interestedUserId", userId + "");
        params.put("token", SPUtils.getToken(this));
        params.put("concernStatus", collectionStatus + "");
        HttpUtils.post(this, MyConfig.ATTENTION, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                GetUpToken getUpToken = new Gson().fromJson(response, GetUpToken.class);
                if (getUpToken.getStatus().equals("0000")) {
                    if (collectionStatus == 0) {
                        attentionFlag = 0;
                        attentionTv.setText("+ 关注");
                        ToastUtils.disPlayShort(MerchantPageActivity.this, "已取消关注");
                    } else {
                        attentionFlag = 1;
                        attentionTv.setText("已关注");
                        ToastUtils.disPlayShort(MerchantPageActivity.this, "关注成功");
                    }
                } else {
                    ShowDialog.showTipPopup(MerchantPageActivity.this, getUpToken.getError(), R.string.sure, new OperationCallback() {
                        @Override
                        public void execute() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
            }
        });
    }
}
