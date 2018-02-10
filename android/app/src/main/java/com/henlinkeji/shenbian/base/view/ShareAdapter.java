package com.henlinkeji.shenbian.base.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.henlinkeji.shenbian.R;


public class ShareAdapter extends BaseAdapter {

    private static String[] shareNames;
    private int[] shareIcons = new int[]{R.mipmap.weixin, R.mipmap.pengyouquan};

    private LayoutInflater inflater;

    public ShareAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        shareNames = new String[]{"微信",
                "朋友圈"};
    }

    @Override
    public int getCount() {
        return shareNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.share_item, null);
        }
        ImageView shareIcon = (ImageView) convertView.findViewById(R.id.share_icon);
        TextView shareTitle = (TextView) convertView.findViewById(R.id.share_title);
        shareIcon.setImageResource(shareIcons[position]);
        shareTitle.setText(shareNames[position]);

        return convertView;
    }
}