package com.henlinkeji.shenbian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.utils.ScreenTools;
import com.henlinkeji.shenbian.base.view.CustomImageView;
import com.henlinkeji.shenbian.base.view.NineGridlayout;
import com.henlinkeji.shenbian.bean.Image;

import java.util.List;

/**
 * Created by Liu on 2017/10/29.
 */

public class DiscoverAdapter extends BaseAdapter {
    private Context context;
    private List<List<Image>> datalist;

    public DiscoverAdapter(Context context, List<List<Image>> datalist) {
        this.context = context;
        this.datalist = datalist;
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
        List<Image> itemList = datalist.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.discover_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivMore = (NineGridlayout) convertView.findViewById(R.id.iv_ngrid_layout);
            viewHolder.ivOne = (CustomImageView) convertView.findViewById(R.id.iv_oneimage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (itemList.isEmpty() || itemList.isEmpty()) {
            viewHolder.ivMore.setVisibility(View.GONE);
            viewHolder.ivOne.setVisibility(View.GONE);
        } else if (itemList.size() == 1) {
            viewHolder.ivMore.setVisibility(View.GONE);
            viewHolder.ivOne.setVisibility(View.VISIBLE);
            handlerOneImage(viewHolder, itemList.get(0));
        } else {
            viewHolder.ivMore.setVisibility(View.VISIBLE);
            viewHolder.ivOne.setVisibility(View.GONE);
            viewHolder.ivMore.setImagesData(itemList);
        }

        return convertView;
    }

    private void handlerOneImage(ViewHolder viewHolder, Image image) {
        viewHolder.ivOne.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        viewHolder.ivOne.setImageUrl(image.getUrl());
    }


    class ViewHolder {
        public NineGridlayout ivMore;
        public CustomImageView ivOne;
    }
}