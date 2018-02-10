package com.henlinkeji.shenbian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.henlinkeji.shenbian.adapter.DiscoverAdapter;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.base.view.CustomImageView;
import com.henlinkeji.shenbian.base.view.NineGridlayout;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.henlinkeji.shenbian.bean.AllClassfy;
import com.henlinkeji.shenbian.bean.Discover;
import com.henlinkeji.shenbian.bean.LoginResult;
import com.henlinkeji.shenbian.bean.OrderList;
import com.henlinkeji.shenbian.bean.PrePayResult;
import com.henlinkeji.shenbian.refresh.VRefreshLayout;
import com.squareup.picasso.Picasso;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends BaseActivity {
    @BindView(R.id.order_lv)
    ListView orderLv;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.back)
    ImageView backIv;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    @BindView(R.id.refresh_layout)
    VRefreshLayout vRefreshLayout;

    private IWXAPI api;

    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order_list);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        SPUtils.setNeedDo(false,this);
        titleTv.setText("订单");
        titleRl.setBackgroundColor(Color.parseColor("#009698"));
        backIv.setImageResource(R.mipmap.back2);

        getList();

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        // 将该app注册到微信
        api.registerApp(Constants.APP_ID);
    }

    @Override
    protected void initData() {
        adapter = new OrderAdapter(this);
        orderLv.setAdapter(adapter);

        if (vRefreshLayout != null) {
            vRefreshLayout.setHeaderView(vRefreshLayout.getDefaultHeaderView());
            vRefreshLayout.setBackgroundColor(Color.WHITE);
            vRefreshLayout.setAutoRefreshDuration(400);
            vRefreshLayout.setRatioOfHeaderHeightToReach(1.5f);
            vRefreshLayout.addOnRefreshListener(new VRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (Utils.isNetworkAvailable(OrderListActivity.this)) {
                        getList();
                    } else {
                        vRefreshLayout.refreshComplete();
                        Toast.makeText(OrderListActivity.this, "当前网络不可用，请检查网络", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getPrePay(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(this));
        params.put("orderId", id);
        final LoadingDialog loadingDialog = new LoadingDialog(this, false);
        loadingDialog.show("");
        HttpUtils.post(this, MyConfig.GET_PREPAY, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                PrePayResult result = new Gson().fromJson(response, PrePayResult.class);
                if (result.getStatus().equals("0000")) {
                    PayReq req = new PayReq();
                    req.appId = result.getData().getAppid();
                    req.partnerId = result.getData().getPartnerid();
                    req.prepayId = result.getData().getPrepayid();
                    req.nonceStr = result.getData().getNoncestr();
                    req.timeStamp = result.getData().getTimestamp();
                    req.packageValue = result.getData().getPackage_();
                    req.sign = result.getData().getSign();
                    api.sendReq(req);
                } else {
                    ToastUtils.disPlayShort(OrderListActivity.this, "支付失败");
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                ShowDialog.showTipPopup(OrderListActivity.this, "支付失败", R.string.sure, new OperationCallback() {
                    @Override
                    public void execute() {
                    }
                });
            }
        });
    }

    private void getList() {
        final LoadingDialog loadingDialog = new LoadingDialog(this, true);
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(this));
        loadingDialog.show("");
        HttpUtils.post(this, MyConfig.ORDER_LIST, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                vRefreshLayout.refreshComplete();
                final OrderList orderList = new Gson().fromJson(response, OrderList.class);
                if (orderList.getStatus().equals("0000")) {
                    adapter.setData(orderList.getData());
                    orderLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (orderList.getData().get(position).getOrderOwnerFlag()==1) {
                                if (orderList.getData().get(position).getPaymentStatus().equals("0")) {
                                    boolean sIsWXAppInstalledAndSupported = api.isWXAppInstalled() && api.isWXAppSupportAPI();
                                    if (!sIsWXAppInstalledAndSupported) {
                                        Toast.makeText(OrderListActivity.this, "您的手机尚未安装微信或微信版本不支持支付功能", Toast.LENGTH_SHORT).show();
                                    } else {
                                        getPrePay(orderList.getData().get(position).getOrderNo());
                                    }
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                vRefreshLayout.refreshComplete();
                ShowDialog.showTipPopup(OrderListActivity.this, "服务器内部错误，请稍后重试", R.string.sure, new OperationCallback() {
                    @Override
                    public void execute() {

                    }
                });
            }
        });
    }

    public class OrderAdapter extends BaseAdapter {
        private Context context;
        private List<OrderList.DataBean> datalist = new ArrayList<>();

        public OrderAdapter(Context context) {
            this.context = context;
        }

        public void setData(List<OrderList.DataBean> datalist) {
            this.datalist = datalist;
            notifyDataSetChanged();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.order_list_item_layout, parent, false);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.desc = (TextView) convertView.findViewById(R.id.desc);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.pay = (TextView) convertView.findViewById(R.id.pay);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final OrderList.DataBean dataBean = datalist.get(position);
            if (dataBean.getOrderOwnerFlag()==1) {
                if (dataBean.getPaymentStatus().equals("0") ) {
                    holder.pay.setText("待付款" + dataBean.getServicePrice() + "元");
                    holder.pay.setBackgroundResource(R.drawable.pay_back1);
                } else if (dataBean.getPaymentStatus() .equals("1") ) {
                    holder.pay.setText("已付款" + dataBean.getServicePrice() + "元");
                    holder.pay.setBackgroundResource(R.drawable.pay_back2);
                }
            }else {
                if (dataBean.getPaymentStatus().equals("0") ) {
                    holder.pay.setText("买家已下单待支付" + dataBean.getServicePrice() + "元");
                    holder.pay.setBackgroundResource(R.drawable.pay_back1);
                } else if (dataBean.getPaymentStatus() .equals("1") ) {
                    holder.pay.setText("已收到买家付款" + dataBean.getServicePrice() + "元");
                    holder.pay.setBackgroundResource(R.drawable.pay_back2);
                }
            }
            holder.name.setText(dataBean.getUserName());
            holder.desc.setText(dataBean.getServiceDescription());
            holder.time.setText(Utils.formatDate(dataBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            if (!TextUtils.isEmpty(dataBean.getHomeUrl())) {
                Picasso.with(OrderListActivity.this).load(dataBean.getHomeUrl()).into(holder.icon);
            }
            return convertView;
        }


        class ViewHolder {
            public TextView name;
            public TextView desc;
            public TextView time;
            public ImageView icon;
            public TextView pay;
        }
    }
}
