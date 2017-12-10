package com.henlinkeji.shenbian.fragments.classfy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.ui.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherClassfyFragment extends ViewPagerFragment {
    private String name;
    @BindView(R.id.selected)
    GridView gridViewForScrollView;

    private boolean isPrepared;
    private View view;

    public static OtherClassfyFragment newInstance(String text) {
        Bundle args = new Bundle();
        OtherClassfyFragment fragment = new OtherClassfyFragment();
        args.putString("name", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_other_classfy, null);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        ButterKnife.bind(this, view);
        initInstence();
        initData();
        initListener();
    }


    protected void initInstence() {
        Bundle parameters = getArguments();
        name = parameters.getString("name");
    }

    protected void initData() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("标题标题标题标题标题标题标题标题标题标题标题标题" + i + i + i + i + i + i + i + i + i);
        }

        gridViewForScrollView.setAdapter(new MyAdapter(getActivity(), list));
    }

    protected void initListener() {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.classfy_other_item_layout, null);
                viewHolder = new OtherClassfyFragment.MyAdapter.ViewHolder();
                viewHolder.nameTv = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (OtherClassfyFragment.MyAdapter.ViewHolder) convertView.getTag();
            }
            viewHolder.nameTv.setText(list.get(position));
            return convertView;
        }

        class ViewHolder {
            public TextView nameTv;
        }
    }

}
