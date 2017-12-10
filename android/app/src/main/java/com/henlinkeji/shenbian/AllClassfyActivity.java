package com.henlinkeji.shenbian;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.henlinkeji.shenbian.bean.AllClassfy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllClassfyActivity extends BaseActivity {

    @BindView(R.id.classfy_recy)
    RecyclerView classfyRecy;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.back)
    ImageView backIv;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;

    private CommonAdapter<AllClassfy> adapter;

    private List<AllClassfy> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_all_classfy);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        titleTv.setText("全部分类");
        titleRl.setBackgroundColor(Color.parseColor("#009698"));
        backIv.setImageResource(R.mipmap.back2);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classfyRecy.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        adapter = new CommonAdapter<AllClassfy>(this, R.layout.all_classfy_item_layout) {
            @Override
            protected void convert(ViewHolder holder, AllClassfy allClassfy, int position) {
                holder.setText(R.id.title, allClassfy.getName());
                holder.setText(R.id.desc, allClassfy.getDesc());
            }
        };
        classfyRecy.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for (int i = 0; i < 20; i++) {
            AllClassfy classfy = new AllClassfy();
            classfy.setName("类别" + i + i + i);
            classfy.setDesc("类别类别" + i);
            list.add(classfy);
        }
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();
    }
}
