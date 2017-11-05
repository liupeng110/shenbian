package com.henlinkeji.shenbian.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hjm on 2016/9/4.
 */
public abstract class BaseFragment extends Fragment {

    //*************页面布局view***************
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), null);
        loadFragment();
        return view;
    }

    //=====================================
    private void loadFragment() {
        initView(view);
        initInstence();
        initData();
        initListener();
    }

    //================初始布局ID=================
    protected abstract int getLayoutId();

    //================初始化控件=================
    protected abstract void initView(View view);

    //================初始化变量=================
    protected abstract void initInstence();

    //================填充数据===================
    protected abstract void initData();

    //================设置监听==================
    protected abstract void initListener();
}
