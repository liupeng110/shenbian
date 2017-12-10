package com.henlinkeji.shenbian;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.LocationUtil;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.PermissionsChecker;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.fragments.home.AttentionFragment;
import com.henlinkeji.shenbian.fragments.home.HeadFragment;
import com.henlinkeji.shenbian.fragments.home.MineFragment;
import com.henlinkeji.shenbian.fragments.home.DiscoverFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_head)
    ImageView headImg;
    @BindView(R.id.iv_attention)
    ImageView attentionImg;
    @BindView(R.id.iv_discover)
    ImageView diacoverImg;
    @BindView(R.id.iv_my)
    ImageView myImg;

    @BindView(R.id.btn_head)
    TextView headTv;
    @BindView(R.id.btn_attention)
    TextView attentionTv;
    @BindView(R.id.btn_discover)
    TextView diacoverTv;
    @BindView(R.id.btn_my)
    TextView myTv;

    @BindView(R.id.rl_head)
    RelativeLayout headRl;
    @BindView(R.id.rl_attention)
    RelativeLayout attentionRl;
    @BindView(R.id.rl_discover)
    RelativeLayout diacoverRl;
    @BindView(R.id.rl_my)
    RelativeLayout myRl;
    @BindView(R.id.rl_add)
    RelativeLayout addRl;


    private HeadFragment headFragment;
    private AttentionFragment attentionFragment;
    private DiscoverFragment discoverFragment;
    private MineFragment mineFagment;

    private long exitTime = 0;

    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        mPermissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void initData() {
        showFragment(0);
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            //首先判断版本号是否大于等于6.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                startPermissionsActivity();
            }
        }
        if (SPUtils.getIMToken(this) != null) {
            connect(SPUtils.getIMToken(MainActivity.this));
        }
        LogUtil.e("==imtoken===" + SPUtils.getIMToken(this));
    }

    @Override
    protected void initListener() {
        headRl.setOnClickListener(this);
        attentionRl.setOnClickListener(this);
        diacoverRl.setOnClickListener(this);
        myRl.setOnClickListener(this);
        addRl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_head:
                showFragment(0);
                headImg.setBackgroundResource(R.mipmap.head_selected);
                attentionImg.setBackgroundResource(R.mipmap.attention);
                diacoverImg.setBackgroundResource(R.mipmap.discover);
                myImg.setBackgroundResource(R.mipmap.mine);
                headTv.setTextColor(Color.parseColor("#009698"));
                attentionTv.setTextColor(Color.parseColor("#8f959c"));
                diacoverTv.setTextColor(Color.parseColor("#8f959c"));
                myTv.setTextColor(Color.parseColor("#8f959c"));
                break;
            case R.id.rl_attention:
                showFragment(1);
                headImg.setBackgroundResource(R.mipmap.head);
                attentionImg.setBackgroundResource(R.mipmap.attention_selected);
                diacoverImg.setBackgroundResource(R.mipmap.discover);
                myImg.setBackgroundResource(R.mipmap.mine);
                headTv.setTextColor(Color.parseColor("#8f959c"));
                attentionTv.setTextColor(Color.parseColor("#009698"));
                diacoverTv.setTextColor(Color.parseColor("#8f959c"));
                myTv.setTextColor(Color.parseColor("#8f959c"));
                break;
            case R.id.rl_add:
                showPop();
                break;
            case R.id.rl_discover:
                showFragment(3);
                headImg.setBackgroundResource(R.mipmap.head);
                attentionImg.setBackgroundResource(R.mipmap.attention);
                diacoverImg.setBackgroundResource(R.mipmap.discover_selected);
                myImg.setBackgroundResource(R.mipmap.mine);
                headTv.setTextColor(Color.parseColor("#8f959c"));
                attentionTv.setTextColor(Color.parseColor("#8f959c"));
                diacoverTv.setTextColor(Color.parseColor("#009698"));
                myTv.setTextColor(Color.parseColor("#8f959c"));
                break;
            case R.id.rl_my:
                showFragment(4);
                headImg.setBackgroundResource(R.mipmap.head);
                attentionImg.setBackgroundResource(R.mipmap.attention);
                diacoverImg.setBackgroundResource(R.mipmap.discover);
                myImg.setBackgroundResource(R.mipmap.mine_selected);
                headTv.setTextColor(Color.parseColor("#8f959c"));
                attentionTv.setTextColor(Color.parseColor("#8f959c"));
                diacoverTv.setTextColor(Color.parseColor("#8f959c"));
                myTv.setTextColor(Color.parseColor("#009698"));
                break;
        }

    }


    public void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        if (index == 0) {
            if (headFragment == null) {
                headFragment = new HeadFragment();
                ft.add(R.id.ll_layout, headFragment);
            } else {
                ft.show(headFragment);
            }
        }
        if (index == 1) {
            if (attentionFragment == null) {
                attentionFragment = new AttentionFragment();
                ft.add(R.id.ll_layout, attentionFragment);
            } else {
                ft.show(attentionFragment);
            }
        }
        if (index == 3) {
            if (discoverFragment == null) {
                discoverFragment = new DiscoverFragment();
                ft.add(R.id.ll_layout, discoverFragment);
            } else {
                ft.show(discoverFragment);
            }
        }
        if (index == 4) {
            if (mineFagment == null) {
                mineFagment = new MineFragment();
                ft.add(R.id.ll_layout, mineFagment);
            } else {
                ft.show(mineFagment);
            }
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (headFragment != null) ft.hide(headFragment);
        if (attentionFragment != null) ft.hide(attentionFragment);
        if (discoverFragment != null) ft.hide(discoverFragment);
        if (mineFagment != null) ft.hide(mineFagment);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
//            ShowDialog.showSelectNoTitlePopup(this, "确定要退出应用吗？", R.string.sure, R.string.cancel, new OperationCallback() {
//                @Override
//                public void execute() {
//                    MyApplication.getInstance().exit();
//                }
//            }, new OperationCallback() {
//                @Override
//                public void execute() {
//
//                }
//            });
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.disPlayShortCenter(MainActivity.this, "再按一次返回键退出U服");
            exitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
        }
    }

    private void showPop() {
        View mMenuView = LayoutInflater.from(this).inflate(R.layout.head_add_layout, null);
        final PopupWindow popWindow = new PopupWindow(mMenuView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置按钮监听
        LinearLayout addArticleLl = (LinearLayout) mMenuView.findViewById(R.id.add_article_ll);
        LinearLayout addServiceLl = (LinearLayout) mMenuView.findViewById(R.id.add_service_ll);
        ImageView closeImg = (ImageView) mMenuView.findViewById(R.id.close);

        addArticleLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtils.getToken(MainActivity.this))) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, AddArticleActivity.class));
                }
                popWindow.dismiss();
            }
        });
        addServiceLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtils.getToken(MainActivity.this))) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, AddServiceActivity.class));
                }
                popWindow.dismiss();
            }
        });
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        popWindow.setAnimationStyle(R.style.animBottom);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popWindow.setBackgroundDrawable(dw);

        popWindow.showAtLocation(MainActivity.this.findViewById(R.id.home_rl), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setBackgroundAlpha(MainActivity.this, 1f);
            }
        });
        //设置背景半透明
        Utils.setBackgroundAlpha(this, 0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            ToastUtils.disPlayLongCenter(this, "重要权限未授予会导致应用基础功能无法使用，建议授予必要权限");
        }
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            LocationUtil.init(this);
        }
    }

    private void connect(String token) {
        if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    LogUtil.e("==Token 错误==");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtil.e("==连接融云成功==");
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtil.e("==连接融云失败==");
                }
            });
        }
    }
}