package com.henlinkeji.shenbian.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henlinkeji.shenbian.LoginActivity;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by smyh on 2015/4/28.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.user_avator)
    SimpleDraweeView userAvatorImg;

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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        userAvatorImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_avator:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}

