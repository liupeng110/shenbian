package com.henlinkeji.shenbian.fragments.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.adapter.DiscoverAdapter;
import com.henlinkeji.shenbian.base.ui.BaseFragment;
import com.henlinkeji.shenbian.bean.Image;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by smyh on 2015/4/28.
 */
public class DiscoverFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    @BindView(R.id.lv_discover)
    ListView discoverLv;


    private List<List<Image>> imagesList;

    private String[] images=new String[]{
            "http://img2.niutuku.com/desk/1208/1426/ntk-1426-17776.jpg"
            ,"http://imgstore.cdn.sogou.com/app/a/100540002/760050.jpg"
            ,"http://imgsrc.baidu.com/imgad/pic/item/71cf3bc79f3df8dc613cb28fc711728b471028ae.jpg"
            ,"http://img2.niutuku.com/desk/1208/1510/ntk-1510-24060.jpg"
            ,"http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=178dcd40db09b3deffb2ec2ba4d606f4/9d82d158ccbf6c81c1c946c0b63eb13533fa401f.jpg"
            ,"http://img1.imgtn.bdimg.com/it/u=2846755474,3235131370&fm=214&gp=0.jpg"
            ,"http://imgsrc.baidu.com/imgad/pic/item/810a19d8bc3eb1359d5a74a4ac1ea8d3fd1f4414.jpg"
            ,"http://imgstore.cdn.sogou.com/app/a/100540002/398213.jpg"
            ,"http://img0.imgtn.bdimg.com/it/u=2603370572,337907411&fm=214&gp=0.jpg"
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initInstence() {
        titleTv.setText("身边头条");
        titleRl.setBackgroundColor(Color.parseColor("#009698"));
    }

    @Override
    protected void initData() {
        getData();
        discoverLv.setAdapter(new DiscoverAdapter(getActivity(),imagesList));
    }

    @Override
    protected void initListener() {

    }

    private void getData() {
        imagesList=new ArrayList<>();
        //从一到9生成9条朋友圈内容，分别是1~9张图片
        for(int i=0;i<9;i++){
            ArrayList<Image> itemList=new ArrayList<>();
            for(int j=0;j<=i;j++){
                itemList.add(new Image(images[j]));
            }
            imagesList.add(itemList);
        }
    }

}
