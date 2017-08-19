package com.henlinkeji.shenbian;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.fragments.AttentionFragment;
import com.henlinkeji.shenbian.fragments.HeadFragment;
import com.henlinkeji.shenbian.fragments.MineFragment;
import com.henlinkeji.shenbian.fragments.DiscoverFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_bar)
     BottomNavigationBar mBottomBar;

    private HeadFragment headFragment;
    private AttentionFragment attentionFragment;
    private DiscoverFragment discoverFragment;
    private MineFragment mineFagment;

    private long exitTime = 0;

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
        mBottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
    }

    @Override
    protected void initData() {
        mBottomBar.setBarBackgroundColor(R.color.white);//set background color for navigation bar
        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.head_selected, R.string.firstPage)
                .setInactiveIconResource(R.mipmap.head_unselected)
                .setActiveColor(Color.parseColor("#1296db"))
                .setInActiveColor(Color.parseColor("#8f959c")))
                .addItem(new BottomNavigationItem(R.mipmap.attention_selected, R.string.attention)
                        .setInactiveIconResource(R.mipmap.attention_unselected)
                        .setActiveColor(Color.parseColor("#1296db"))
                        .setInActiveColor(Color.parseColor("#8f959c")))
                .addItem(new BottomNavigationItem(R.mipmap.discover_selected, R.string.discover)
                        .setInactiveIconResource(R.mipmap.discover_unselected)
                        .setActiveColor(Color.parseColor("#1296db"))
                        .setInActiveColor(Color.parseColor("#8f959c")))
                .addItem(new BottomNavigationItem(R.mipmap.mine_selected, R.string.mine)
                        .setInactiveIconResource(R.mipmap.mine_unselected)
                        .setActiveColor(Color.parseColor("#1296db"))
                        .setInActiveColor(Color.parseColor("#8f959c")))
                .setFirstSelectedPosition(0)
                .initialise();

        showFragment(0);
    }

    @Override
    protected void initListener() {
        mBottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                showFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
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
        if (index == 2) {
            if (discoverFragment == null) {
                discoverFragment = new DiscoverFragment();
                ft.add(R.id.ll_layout, discoverFragment);
            } else {
                ft.show(discoverFragment);
            }
        }
        if (index == 3) {
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
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.disPlayShortCenter(MainActivity.this, "再按一次返回键退出应用《身边》");
            exitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
        }
    }
}