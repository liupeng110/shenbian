package com.henlinkeji.shenbian.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.ui.BaseFragment;

import butterknife.ButterKnife;


/**
 * Created by smyh on 2015/4/28.
 */
public class AttentionFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initInstence() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

}
