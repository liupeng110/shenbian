package com.henlinkeji.shenbian.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.view.CustomImageView;
import com.henlinkeji.shenbian.base.view.NineGridlayout;
import com.henlinkeji.shenbian.bean.Discover;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static io.rong.imkit.plugin.image.PictureSelectorActivity.PicItemHolder.itemList;

/**
 * Created by Liu on 2017/10/29.
 */

public class DiscoverAdapter extends BaseAdapter {
    private Context context;
    private List<Discover.DataBean> datalist = new ArrayList<>();

    public DiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Discover.DataBean> datalist) {
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.discover_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.avar = (ImageView) convertView.findViewById(R.id.img);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            viewHolder.ivMore = (NineGridlayout) convertView.findViewById(R.id.iv_ngrid_layout);
            viewHolder.ivOne = (CustomImageView) convertView.findViewById(R.id.iv_oneimage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Discover.DataBean dataBean = datalist.get(position);
        if (dataBean.getHomeUrl() != null) {
            if (!TextUtils.isEmpty(dataBean.getHomeUrl())) {
                Picasso.with(context).load(dataBean.getHomeUrl()).into(viewHolder.avar);
            } else {
                viewHolder.avar.setImageResource(R.mipmap.temp);
            }
        } else {
            viewHolder.avar.setImageResource(R.mipmap.temp);
        }
        viewHolder.time.setText(dataBean.getReleaseTime());
        viewHolder.name.setText(dataBean.getServiceTitle());
        if (dataBean.getServiceDescription() != null) {
            if (!TextUtils.isEmpty(dataBean.getServiceDescription())) {
                viewHolder.desc.setText(dataBean.getServiceDescription());
            } else {
                viewHolder.desc.setText("");
            }
        } else {
            viewHolder.desc.setText("");
        }
        viewHolder.time.setText(dataBean.getReleaseTime());
        if (dataBean.getAddress() != null) {
            if (!TextUtils.isEmpty(dataBean.getAddress())) {
                viewHolder.address.setText(dataBean.getAddress());
            } else {
                viewHolder.address.setText("");
            }
        } else {
            viewHolder.address.setText("");
        }
        if (dataBean.getMaterialUrl()!=null){
            if (!TextUtils.isEmpty(dataBean.getMaterialUrl())){
                String[] urls=dataBean.getMaterialUrl().split(";");
                if (urls.length == 1) {
                    viewHolder.ivMore.setVisibility(View.GONE);
                    viewHolder.ivOne.setVisibility(View.VISIBLE);
                    handlerOneImage(viewHolder, urls[0]);
                } else {
                    List<String> list=new ArrayList<>();
                    viewHolder.ivMore.setVisibility(View.VISIBLE);
                    viewHolder.ivOne.setVisibility(View.GONE);
                    for (int i = 0; i <urls.length ; i++) {
                        list.add(urls[i]);
                    }
                    viewHolder.ivMore.setImagesData(list);
                }
            }else {
                viewHolder.ivMore.setVisibility(View.GONE);
                viewHolder.ivOne.setVisibility(View.GONE);
            }
        }else {
            viewHolder.ivMore.setVisibility(View.GONE);
            viewHolder.ivOne.setVisibility(View.GONE);
        }
        return convertView;
    }

    private void handlerOneImage(ViewHolder viewHolder, String url) {
        viewHolder.ivOne.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        viewHolder.ivOne.setImageUrl(url);
    }


    class ViewHolder {
        public NineGridlayout ivMore;
        public CustomImageView ivOne;
        public ImageView avar;
        public TextView name;
        public TextView desc;
        public TextView time;
        public TextView address;
    }
}