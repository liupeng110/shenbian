package com.henlinkeji.shenbian.base.load;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.henlinkeji.shenbian.R;


/**
 * Created by Miracler on 17/3/9.
 */

public class LoadingDialog extends AlertDialog {

    private Context mContext;
    private ProgressBar mBar;
    private TextView mMessage;
    private boolean isCancel;

    public LoadingDialog(Context context, boolean isCancel) {
        super(context, R.style.loading_dialog);
        this.mContext = context;
        this.isCancel = isCancel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_process_dialog);

        setCanceledOnTouchOutside(isCancel);
        setCancelable(isCancel);

        mBar = (ProgressBar) findViewById(R.id.bar);
        mMessage = (TextView) findViewById(R.id.message);
    }

    public void show(String msg) {
        super.show();
        if (mMessage != null) {
            mMessage.setText(msg);
        }
    }

    public void exit() {

        super.dismiss();
    }

    //设置进度图片
    public void setIndeterminateDrawable(int drawable) {
        mBar.setIndeterminateDrawable(mContext.getResources().getDrawable(drawable));
    }

    //设置字体颜色
    public void setTextColor(int color) {
        mMessage.setTextColor(color);
    }

}
