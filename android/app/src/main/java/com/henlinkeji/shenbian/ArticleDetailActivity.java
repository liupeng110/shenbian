package com.henlinkeji.shenbian;

import android.os.Bundle;

import com.henlinkeji.shenbian.base.ui.BaseActivity;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article_detail);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
