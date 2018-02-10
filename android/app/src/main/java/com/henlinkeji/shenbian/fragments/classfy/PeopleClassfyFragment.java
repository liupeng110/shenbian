package com.henlinkeji.shenbian.fragments.classfy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.ServiceDetailActivity;
import com.henlinkeji.shenbian.base.ui.ViewPagerFragment;
import com.henlinkeji.shenbian.bean.Classfy;
import com.henlinkeji.shenbian.bean.ClassfyData;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleClassfyFragment extends ViewPagerFragment {
    private String name;
    @BindView(R.id.selected)
    GridView gridViewForScrollView;
    @BindView(R.id.no_data)
    TextView noTv;

    private boolean isPrepared;
    private View view;

    private List<ClassfyData.DataBean.ServiceInfosBeanX.ServiceInfosBean> list = new ArrayList<>();

    public static PeopleClassfyFragment newInstance(List<ClassfyData.DataBean.ServiceInfosBeanX.ServiceInfosBean> list) {
        Bundle args = new Bundle();
        PeopleClassfyFragment fragment = new PeopleClassfyFragment();
        args.putSerializable("list", (Serializable) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_people_classfy, null);
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
        list = (List<ClassfyData.DataBean.ServiceInfosBeanX.ServiceInfosBean>) parameters.getSerializable("list");
    }

    protected void initData() {
        gridViewForScrollView.setAdapter(new MyAdapter(getActivity()));
        if (list.size()<=0){
            noTv.setVisibility(View.VISIBLE);
        }else {
            noTv.setVisibility(View.GONE);
        }
    }

    protected void initListener() {
    }

    class MyAdapter extends BaseAdapter {
        private Context context;

        MyAdapter(Context context) {
            this.context = context;
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
            MyAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.classfy_people_item_layout, null);
                viewHolder = new MyAdapter.ViewHolder();
                viewHolder.titleTv = (TextView) convertView.findViewById(R.id.title);
                viewHolder.locTv = (TextView) convertView.findViewById(R.id.location);
                viewHolder.descTv = (TextView) convertView.findViewById(R.id.desc);
                viewHolder.starNumTv = (TextView) convertView.findViewById(R.id.star_count);
                viewHolder.numTv = (TextView) convertView.findViewById(R.id.num);
                viewHolder.avar = (SimpleDraweeView) convertView.findViewById(R.id.user_avator);
                viewHolder.item = (LinearLayout) convertView.findViewById(R.id.item);
                viewHolder.cover = (ImageView) convertView.findViewById(R.id.cover);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (MyAdapter.ViewHolder) convertView.getTag();
            }
            final ClassfyData.DataBean.ServiceInfosBeanX.ServiceInfosBean bean = list.get(position);
            viewHolder.titleTv.setText(bean.getServiceTitle());
            viewHolder.descTv.setText(bean.getServiceDescription());
            viewHolder.locTv.setText(bean.getAddress());
            viewHolder.starNumTv.setText(bean.getStarRating() + "(" + bean.getEvaluateCount() + ")");
            viewHolder.numTv.setText("成交" + bean.getOrderQuantity() + "单");
            viewHolder.avar.setImageURI(Uri.parse(bean.getUserIcon()));
            Picasso.with(getActivity()).load(bean.getCoverImage()).into(viewHolder.cover);
            viewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
                    intent.putExtra("id", bean.getId());
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class ViewHolder {
            public TextView titleTv;
            public TextView locTv;
            public TextView descTv;
            public TextView starNumTv;
            public TextView numTv;
            public SimpleDraweeView avar;
            public ImageView cover;
            public LinearLayout item;
        }
    }

}
