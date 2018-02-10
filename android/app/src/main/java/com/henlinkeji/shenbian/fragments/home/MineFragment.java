package com.henlinkeji.shenbian.fragments.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.EditInfoActivity;
import com.henlinkeji.shenbian.FeedBackActivity;
import com.henlinkeji.shenbian.LoginActivity;
import com.henlinkeji.shenbian.MerchantPageActivity;
import com.henlinkeji.shenbian.OrderListActivity;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.WebActivity;
import com.henlinkeji.shenbian.adapter.MineDownAdapter;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.ui.BaseFragment;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.view.ListViewForScrollView;
import com.henlinkeji.shenbian.bean.MineDownList;
import com.henlinkeji.shenbian.bean.MyInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;


/**
 * Created by smyh on 2015/4/28.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.user_data)
    LinearLayout userDataLin;
    @BindView(R.id.user_avator)
    SimpleDraweeView userAvatorImg;
    @BindView(R.id.mine_recy)
    ListViewForScrollView listViewForScrollView;
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
    @BindView(R.id.user_name)
    TextView nameTv;
    @BindView(R.id.my_article_ll)
    LinearLayout myArticleLin;
    @BindView(R.id.my_service_ll)
    LinearLayout myServiceLin;

    private MineDownAdapter adapter;

    private List<MineDownList> mineDownLists = new ArrayList<>();

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
        userDataLin.setFocusableInTouchMode(true);
        userDataLin.requestFocus();
    }

    @Override
    protected void initData() {
        MineDownList mineDownList1 = new MineDownList();
        MineDownList mineDownList2 = new MineDownList();
        MineDownList mineDownList3 = new MineDownList();
        MineDownList mineDownList4 = new MineDownList();
        MineDownList mineDownList5 = new MineDownList();
        MineDownList mineDownList6 = new MineDownList();
        MineDownList mineDownList7 = new MineDownList();
        mineDownList1.setTitle("消息通知");
        mineDownList1.setImg(R.mipmap.mine_msg);
        mineDownList2.setTitle("我的收入");
        mineDownList2.setImg(R.mipmap.mine_wallet);
        mineDownList3.setTitle("我的订单");
        mineDownList3.setImg(R.mipmap.mine_order);
        mineDownList4.setTitle("资料设置");
        mineDownList4.setImg(R.mipmap.mine_info);
        mineDownList5.setTitle("安全与隐私");
        mineDownList5.setImg(R.mipmap.mine_security);
        mineDownList6.setTitle("反馈");
        mineDownList6.setImg(R.mipmap.mine_advice);
        mineDownList7.setTitle("帮助");
        mineDownList7.setImg(R.mipmap.mine_help);
//        mineDownLists.add(mineDownList1);
        mineDownLists.add(mineDownList2);
        mineDownLists.add(mineDownList3);
        mineDownLists.add(mineDownList4);
        mineDownLists.add(mineDownList5);
        mineDownLists.add(mineDownList6);
        mineDownLists.add(mineDownList7);

        adapter = new MineDownAdapter(mineDownLists, getActivity());
        listViewForScrollView.setAdapter(adapter);
        listViewForScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), OrderListActivity.class));
                    }
                }
                if (position == 2) {
                    if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), EditInfoActivity.class));
                    }
                }
                if (position == 4) {
                    if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), FeedBackActivity.class));
                    }
                }
                if (position == 3) {
                    if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    } else {
                       Intent intent= new Intent(getActivity(), WebActivity.class);
                        intent.putExtra("name","安全与隐私");
                        intent.putExtra("url",MyConfig.SECURITY);
                        startActivity(intent);
                    }
                }
                if (position == 5) {
                    if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    } else {
                        Intent intent= new Intent(getActivity(), WebActivity.class);
                        intent.putExtra("name","帮助");
                        intent.putExtra("url",MyConfig.HELP);
                        startActivity(intent);
                    }
                }
            }
        });
        if (!TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
            getInfo();
        }
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
        switch (v.getId()) {
            case R.id.user_avator:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    Intent intent = new Intent(getActivity(), MerchantPageActivity.class);
                    intent.putExtra("pos", 0);
                    startActivity(intent);
                }
                break;
//            case R.id.published_lin:
//                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
//                }else {
//                    ToastUtils.disPlayShort(getActivity(),"已发布");
//                }
//                break;
//            case R.id.visited_lin:
//                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
//                }else {
//                    ToastUtils.disPlayShort(getActivity(),"7日访客");
//                }
//                break;
//            case R.id.attentioned_lin:
//                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
//                }else {
//                    ToastUtils.disPlayShort(getActivity(),"关注");
//                }
//                break;
//            case R.id.followed_lin:
//                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
//                }else {
//                    ToastUtils.disPlayShort(getActivity(),"粉丝");
//                }
//                break;
            case R.id.my_article_ll:
//                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
//                }else {
//                    ToastUtils.disPlayShort(getActivity(),"我的文章");
//                }
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    Intent intent = new Intent(getActivity(), MerchantPageActivity.class);
                    intent.putExtra("pos", 1);
                    startActivity(intent);
                }
                break;
            case R.id.my_service_ll:
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    Intent intent = new Intent(getActivity(), MerchantPageActivity.class);
                    intent.putExtra("pos", 0);
                    startActivity(intent);
                }
                break;
        }
    }

    private void getInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(getActivity()));
        HttpUtils.post(getActivity(), MyConfig.BASIC_INFO, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                MyInfo info = new Gson().fromJson(response, MyInfo.class);
                if (info.getStatus().equals("0000")) {
                    initInfo(info.getData());
                }
            }

            @Override
            public void onFailure(String response) {
            }
        });
    }

    private void initInfo(MyInfo.DataBean info) {
        Uri uri = Uri.parse(info.getUrserIcon());
        userAvatorImg.setImageURI(uri);
        nameTv.setText(info.getUserName());
        publishedTv.setText(info.getPublishedCount() + "");
        visitedTv.setText(info.getCollectCount() + "");
        attentionedTv.setText(info.getAttentionCount() + "");
        followedTv.setText(info.getFansCount() + "");

        adapter.setIncome("¥：" + info.getIncome());

        RongIM.getInstance().refreshUserInfoCache(new UserInfo(SPUtils.getUserId(getActivity()), info.getUserName(), Uri.parse(info.getUrserIcon())));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (!TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                getInfo();
            }
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
//        handler.post(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(runnable);
    }


    private Runnable runnable = new Runnable() {
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    if (!TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                        getInfo();
                    }
                    break;
            }
        }
    };
}

