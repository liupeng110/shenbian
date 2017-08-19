package com.henlinkeji.shenbian.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by hjm on 2016/9/4.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout(savedInstanceState);
        loadActivity();
    }

    //=================加载Activity================
    private void loadActivity() {
        initView();
        initInstence();
        initData();
        initListener();
    }

    //=================加载Activity布局================
    protected abstract void initLayout(Bundle savedInstanceState);

    //================初始化控件====================
    protected abstract void initView();

    //================初始化变量====================
    protected abstract void initInstence();

    //================填充数据======================
    protected abstract void initData();

    //================设置监听=====================
    protected abstract void initListener();
}

