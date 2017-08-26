package com.henlinkeji.shenbian.base.view.rvadapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.henlinkeji.shenbian.base.view.rvadapter.base.ItemViewDelegate;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public void setDatas(List<T> mDatas) {
        super.setDatas(mDatas);
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public CommonAdapter(final Context context, final int layoutId) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);

    public void addData(T data) {
        super.setDatas(mDatas);
        mDatas.add(data);
        notifyDataSetChanged();
    }

}
