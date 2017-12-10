package com.henlinkeji.shenbian.fragments.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.adapter.DiscoverAdapter;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseFragment;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.bean.Discover;
import com.henlinkeji.shenbian.refresh.VRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by smyh on 2015/4/28.
 */
public class DiscoverFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    @BindView(R.id.lv_discover)
    ListView discoverLv;
    @BindView(R.id.refresh_layout)
    VRefreshLayout vRefreshLayout;

    private List<Discover.DataBean> dataBeanList = new ArrayList<>();

    private DiscoverAdapter discoverAdapter;

    private LoadingDialog loadingDialog;

    private int last_index;
    private int total_index;
    private int page=1;
    private int totalPage;
    private boolean isLoading = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initInstence() {
        titleTv.setText("身边头条");
        titleRl.setBackgroundColor(Color.parseColor("#009698"));

        loadingDialog = new LoadingDialog(getActivity(), true);
        loadingDialog.show("");
    }

    @Override
    protected void initData() {
        getDiscover();
        discoverAdapter = new DiscoverAdapter(getActivity());
        discoverLv.setAdapter(discoverAdapter);

        if (vRefreshLayout != null) {
            vRefreshLayout.setHeaderView(vRefreshLayout.getDefaultHeaderView());
            vRefreshLayout.setBackgroundColor(Color.WHITE);
            vRefreshLayout.setAutoRefreshDuration(400);
            vRefreshLayout.setRatioOfHeaderHeightToReach(1.5f);
            vRefreshLayout.addOnRefreshListener(new VRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (Utils.isNetworkAvailable(getActivity())) {
                        page=1;
                        dataBeanList.clear();
                        getDiscover();
                    } else {
                        vRefreshLayout.refreshComplete();
                        Toast.makeText(getActivity(), "当前网络不可用，请检查网络", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    protected void initListener() {
        discoverLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
                    if (!isLoading) {
                        isLoading = true;
                        page++;
                        if (page>totalPage){
                            ToastUtils.disPlayShort(getActivity(),"已加载全部");
                        }else {
                            getDiscover();
                        }
                        loadComplete();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                last_index = firstVisibleItem + visibleItemCount;
                total_index = totalItemCount;
            }
        });
    }

    private void getDiscover() {
        Map<String, String> params = new HashMap<>();
        params.put("city", SPUtils.getCity(getActivity()));
        params.put("pageNo", page + "");
        params.put("center", SPUtils.getLongitude(getActivity()) + "," + SPUtils.getLatitude(getActivity()));
        HttpUtils.post(getActivity(), MyConfig.DISCOVER, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog!=null) {
                    loadingDialog.exit();
                }
                vRefreshLayout.refreshComplete();
                Discover discover = new Gson().fromJson(response, Discover.class);
                if (discover.getStatus().equals("0000")) {
                    dataBeanList.addAll(discover.getData());
                    discoverAdapter.setData(dataBeanList);
                    totalPage=discover.getTotalPage();
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog!=null) {
                    loadingDialog.exit();
                }
                vRefreshLayout.refreshComplete();
            }
        });
    }

    public void loadComplete() {
        isLoading = false;
        getActivity().invalidateOptionsMenu();
    }
}
