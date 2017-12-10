package com.henlinkeji.shenbian.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;
import android.widget.ListView;

/**
 * Created by Miracler on 17/9/17.
 */

public class ExpandableListViewForScrollView extends ExpandableListView {
    public ExpandableListViewForScrollView(Context context) {
        super(context);
    }
    public ExpandableListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ExpandableListViewForScrollView(Context context, AttributeSet attrs,
                                           int defStyle) {
        super(context, attrs, defStyle);
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}