package com.henlinkeji.shenbian.shenbian;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.maps.MapView;
import com.henlinkeji.shenbian.shenbian.Fragments.FourFragment;
import com.henlinkeji.shenbian.shenbian.Fragments.OneFragment;
import com.henlinkeji.shenbian.shenbian.Fragments.ThreeFragment;
import com.henlinkeji.shenbian.shenbian.Fragments.TwoFragment;

public class MainActivity extends AppCompatActivity implements  OneFragment.OnFragmentInteractionListener,
        TwoFragment.OnFragmentInteractionListener,ThreeFragment.OnFragmentInteractionListener,
        FourFragment.OnFragmentInteractionListener{

    MapView mMapView = null;

    private FrameLayout mHomeContent;
    private RadioGroup mHomeRadioGroup;
    private RadioButton mHomeHomeRb;
    private RadioButton mHomeFindRb;
    private RadioButton mHomeSearchRb;
    private RadioButton mHomeProfileRb;

    static final int NUM_ITEMS = 4;//一共四个fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    protected void initView() {
        mHomeContent = (FrameLayout) findViewById(R.id.mHomeContent); //tab上方的区域
        mHomeRadioGroup = (RadioGroup) findViewById(R.id.bottom_tab);  //底部的四个tab
        mHomeHomeRb = (RadioButton) findViewById(R.id.mHomeHomeRb);
        mHomeFindRb = (RadioButton) findViewById(R.id.mHomeFindRb);
        mHomeSearchRb = (RadioButton) findViewById(R.id.mHomeSearchRb);
        mHomeProfileRb = (RadioButton) findViewById(R.id.mHomeProfileRb);

        //监听事件：为底部的RadioGroup绑定状态改变的监听事件
        mHomeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    case R.id.mHomeHomeRb:
                        index = 0;
                        break;
                    case R.id.mHomeFindRb:
                        index = 1;
                        break;
                    case R.id.mHomeSearchRb:
                        index = 2;
                        break;
                    case R.id.mHomeProfileRb:
                        index = 3;
                        break;
                }
                //通过fragments这个adapter还有index来替换帧布局中的内容
                Fragment fragment = (Fragment) fragments.instantiateItem(mHomeContent, index);
                //一开始将帧布局中 的内容设置为第一个
                fragments.setPrimaryItem(mHomeContent, 0, fragment);
                fragments.finishUpdate(mHomeContent);


            }
        });
    }


    //第一次启动时，我们让mHomeHomeRb这个radiobutton处于选中状态。
    // 当然了，在这之前，先要在布局文件中设置其他的某一个radiobutton（只要不是mHomeHomeRb就行）
    // 的属性为android:checked="true"，才会出发下面的这个check方法切换到mHomeHomeRb
    @Override
    protected void onStart() {
        super.onStart();
        mHomeRadioGroup.check(R.id.mHomeHomeRb);
    }

    //用adapter来管理四个Fragment界面的变化。注意，我这里用的Fragment都是v4包里面的
    FragmentStatePagerAdapter fragments = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

        @Override
        public int getCount() {
            return NUM_ITEMS;//一共有四个Fragment
        }

        //进行Fragment的初始化
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0://首页
                    fragment = new OneFragment();
                    break;
                case 1://发现
                    fragment = new TwoFragment();
                    break;

                case 2://搜索
                    fragment = new ThreeFragment();
                    break;

                case 3://我的
                    fragment = new FourFragment();
                    break;
                default:
                    new OneFragment();
                    break;
            }

            return fragment;
        }
    };

    protected void initData() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
