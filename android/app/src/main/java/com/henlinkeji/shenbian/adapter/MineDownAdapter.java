package com.henlinkeji.shenbian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.bean.MineDownList;

import java.util.List;

/**
 * Created by Miracler on 17/9/17.
 */

public class MineDownAdapter extends BaseAdapter {

    private List<MineDownList> mineDownLists;
    private LayoutInflater inflater;

    public MineDownAdapter(List<MineDownList> list, Context context) {
        this.mineDownLists = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mineDownLists == null ? 0 : mineDownLists.size();
    }

    @Override
    public MineDownList getItem(int position) {
        return mineDownLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mine_down_list_item_layout, null);
            holder = new ViewHolder();
                    /*得到各个控件的对象*/
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);//绑定ViewHolder对象

        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                   }
        }
        MineDownList mineDown=mineDownLists.get(position);
        holder.title.setText(mineDown.getTitle());
        holder.img.setImageResource(mineDown.getImg());
        return convertView;
    }

    class ViewHolder {
        public TextView title;
        public ImageView img;
    }

}
