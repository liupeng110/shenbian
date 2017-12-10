package com.henlinkeji.shenbian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henlinkeji.shenbian.adapter.ServiceTabVpAdapter;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.fragments.classfy.ServiceClassfyFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LookServiceActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.back)
    ImageView backIv;
    @BindView(R.id.right)
    ImageView rightIv;
    @BindView(R.id.classfy_down)
    ImageView downIv;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    @BindView(R.id.classfy_tablayout)
    TabLayout classfyTab;
    @BindView(R.id.classfy_viewpager)
    ViewPager classfyVp;

    private List<Fragment> fragmentList;
    private List<String> titleList;

    private HashMap<Integer, Integer> selMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_look_service);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        titleTv.setText("找服务");
        titleRl.setBackgroundColor(Color.parseColor("#404040"));
        backIv.setImageResource(R.mipmap.back2);
        rightIv.setImageResource(R.mipmap.shoping_car);
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();
        titleList.add("美发美甲");
        titleList.add("寄快递");
        titleList.add("送水");
        titleList.add("设计打印");
        titleList.add("维修");
        titleList.add("寄快递");
        titleList.add("送水");
        titleList.add("设计打印");

        for (int i = 0; i < titleList.size(); i++) {
            fragmentList.add(ServiceClassfyFragment.newInstance(titleList.get(i)));
            classfyTab.addTab(classfyTab.newTab().setText(titleList.get(i)));
            selMap.put(i, 0);
        }
    }

    @Override
    protected void initListener() {
        classfyTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        ServiceTabVpAdapter fAdapter = new ServiceTabVpAdapter(getSupportFragmentManager(), fragmentList, titleList);
        classfyVp.setAdapter(fAdapter);
        classfyTab.setupWithViewPager(classfyVp);

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        downIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop();
            }
        });
    }

    private void showPop() {
        View mMenuView = LayoutInflater.from(this).inflate(R.layout.look_service_pop_layout, null);
        final PopupWindow popWindow = new PopupWindow(mMenuView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        GridView gridView = mMenuView.findViewById(R.id.classfy_second);
        final List<String> nameList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nameList.add(titleList.get(classfyTab.getSelectedTabPosition()) + i);
        }
        MyAdapter adapter = new MyAdapter(LookServiceActivity.this, nameList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selMap.put(classfyTab.getSelectedTabPosition(), position);
                popWindow.dismiss();
            }
        });
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popWindow.setBackgroundDrawable(dw);

        popWindow.showAsDropDown(classfyTab, 0, 0);
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private List<String> list;

        MyAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        public int getCount() {
            return list.size();
        }

        public Object getItem(int item) {
            return list.get(item);
        }

        public long getItemId(int id) {
            return id;
        }

        //创建View方法
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.classfy_pop_item_layout, null);
                viewHolder = new ViewHolder();
                viewHolder.nameTv = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.nameTv.setText(list.get(position));
            if (position == selMap.get(classfyTab.getSelectedTabPosition())) {
                viewHolder.nameTv.setTextColor(Color.parseColor("#009698"));
            } else {
                viewHolder.nameTv.setTextColor(Color.parseColor("#a2a5aa"));
            }
            return convertView;
        }

        class ViewHolder {
            public TextView nameTv;
        }
    }

}
