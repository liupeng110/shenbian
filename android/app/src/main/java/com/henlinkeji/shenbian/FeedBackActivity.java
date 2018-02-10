package com.henlinkeji.shenbian;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.back)
    ImageView backIv;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.submit)
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_feed_back);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        titleRl.setBackgroundColor(Color.parseColor("#404040"));
        backIv.setImageResource(R.mipmap.back2);
        titleTv.setText("意见反馈");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               if (TextUtils.isEmpty(s.toString())){
                   submit.setBackgroundResource(R.drawable.submit_back);
               }else {
                   submit.setBackgroundResource(R.drawable.submit_back2);
               }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(content.getText().toString())){
                    ToastUtils.disPlayShort(FeedBackActivity.this,"请输入内容");
                }else {
                    FeedBackActivity.this.finish();
                }
            }
        });
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.disPlayShort(FeedBackActivity.this,"感谢您的宝贵意见");
                finish();
            }
        });
    }
}
