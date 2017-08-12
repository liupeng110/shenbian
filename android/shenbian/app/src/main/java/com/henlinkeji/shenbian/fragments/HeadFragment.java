package com.henlinkeji.shenbian.fragments;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.ui.BaseFragment;
import com.henlinkeji.shenbian.base.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by smyh on 2015/4/28.
 */
public class HeadFragment extends BaseFragment {

    @BindView(R.id.good_share_tv)
    TextView goodShareTv;
    @BindView(R.id.good_share_view)
    View goodShareView;
    @BindView(R.id.door_service_tv)
    TextView doorServiceTv;
    @BindView(R.id.door_service_view)
    View doorServiceView;
    @BindView(R.id.door_service_rl)
    RelativeLayout doorServiceRl;
    @BindView(R.id.good_share_rl)
    RelativeLayout goodShareRl;

    private GoodShareFragment goodShareFragment;
    private DoorServiceFragment doorServiceFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initInstence() {
        showFragment(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        goodShareRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodShareTv.setTextSize(14);
                goodShareTv.setTextColor(Color.parseColor("#ffffff"));
                goodShareView.setVisibility(View.VISIBLE);
                doorServiceTv.setTextSize(12);
                doorServiceTv.setTextColor(Color.parseColor("#cfcfcf"));
                doorServiceView.setVisibility(View.INVISIBLE);
//                showFragment(0);
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.rl_layout,new GoodShareFragment()).commit();
            }
        });
        doorServiceRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doorServiceTv.setTextSize(14);
                doorServiceTv.setTextColor(Color.parseColor("#ffffff"));
                doorServiceView.setVisibility(View.VISIBLE);
                goodShareTv.setTextSize(12);
                goodShareTv.setTextColor(Color.parseColor("#cfcfcf"));
                goodShareView.setVisibility(View.INVISIBLE);
//                showFragment(1);
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.rl_layout,new DoorServiceFragment()).commit();
            }
        });
    }

    public void showFragment(int index) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        if (index == 0) {
            if (goodShareFragment == null) {
                goodShareFragment = new GoodShareFragment();
                ft.add(R.id.rl_layout, goodShareFragment);
            } else {
                ft.show(goodShareFragment);
            }
        }
        if (index == 1) {
            if (doorServiceFragment == null) {
                doorServiceFragment = new DoorServiceFragment();
                ft.add(R.id.rl_layout, doorServiceFragment);
            } else {
                ft.show(doorServiceFragment);
            }
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (goodShareFragment != null) ft.hide(goodShareFragment);
        if (doorServiceFragment != null) ft.hide(doorServiceFragment);
    }
}


