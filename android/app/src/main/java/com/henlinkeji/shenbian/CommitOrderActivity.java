package com.henlinkeji.shenbian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.InputTools;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.view.CustomDatePicker;
import com.henlinkeji.shenbian.base.view.ExpandableListViewForScrollView;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.bean.CommitOrder;
import com.henlinkeji.shenbian.bean.CommitOrderResult;
import com.henlinkeji.shenbian.bean.PrePayResult;
import com.henlinkeji.shenbian.bean.QueryCart;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitOrderActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView backImg;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.toolbar)
    RelativeLayout titleRl;
    @BindView(R.id.add_address)
    RelativeLayout addressRl;
    @BindView(R.id.shopping_car_list)
    ExpandableListViewForScrollView expandableListView;
    @BindView(R.id.order)
    TextView orderTv;
    @BindView(R.id.time)
    TextView timeTv;
    @BindView(R.id.address)
    TextView addressTv;
    @BindView(R.id.time_rl)
    RelativeLayout timeRl;
    @BindView(R.id.msg_et)
    EditText msgEt;

    private List<QueryCart.DataBean> groups = new ArrayList<>();

    private CustomDatePicker customDatePicker;

    private String nowTime;

    private List<CommitOrder> list = new ArrayList<>();

    private IWXAPI api;

    public static CommitOrderActivity commitOrderActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_commit_order);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        SPUtils.setNeedDo(true,this);
        titleTv.setText("提交订单");
        backImg.setImageResource(R.mipmap.back2);
        titleRl.setBackgroundColor(Color.parseColor("#009698"));
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        // 将该app注册到微信
        api.registerApp(Constants.APP_ID);
        commitOrderActivity=this;
    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            groups = (List<QueryCart.DataBean>) getIntent().getSerializableExtra("orderlist");
        }
        if (groups != null) {
            for (int i = 0; i < groups.size(); i++) {
                CommitOrder commitOrder = new CommitOrder();
                for (int j = 0; j < groups.get(i).getCarts().size(); j++) {
                    commitOrder.setOrderQuantity(groups.get(i).getCarts().get(j).getServiceAmount());
                    commitOrder.setServiceId(groups.get(i).getCarts().get(j).getServiceId());
                    list.add(commitOrder);
                }
            }
        }
        calculate();
        ShopcartAdapter shopcartAdapter = new ShopcartAdapter(groups, this);
        expandableListView.setAdapter(shopcartAdapter);

        for (int i = 0; i < shopcartAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        nowTime = sdf.format(new Date(System.currentTimeMillis()));
        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                timeTv.setText(time);
            }
        }, nowTime, "2050-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(true); // 显示时和分
        customDatePicker.setIsLoop(false); // 允许循环滚动
    }

    @Override
    protected void initListener() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addressRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommitOrderActivity.this, SelectAddressActivity.class);
                intent.putExtra("tag", 0);
                startActivity(intent);
            }
        });
        timeRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDatePicker.show(nowTime);
            }
        });
        orderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputTools.HideKeyboard(v);
                commit();
            }
        });
    }

    private void commit() {
        if (TextUtils.isEmpty(addressTv.getText().toString())) {
            ToastUtils.disPlayShort(this, "位置、联系人未填写");
            return;
        }
        if (TextUtils.isEmpty(timeTv.getText().toString())) {
            ToastUtils.disPlayShort(this, "时间未填写");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(this));
        params.put("paymentType", 1 + "");
        params.put("serviceTime", timeTv.getText().toString());
        params.put("note", msgEt.getText().toString());
        params.put("orderDetails", new Gson().toJson(list));
        params.put("address", addressTv.getText().toString());
        params.put("cityName", SPUtils.getDataString("city", "", CommitOrderActivity.this));
        params.put("contact", SPUtils.getDataString("name", "", CommitOrderActivity.this));
        params.put("houseName", SPUtils.getDataString("detail", "", CommitOrderActivity.this));
        params.put("mobilePhone", SPUtils.getDataString("phone", "", CommitOrderActivity.this).replace(" ", ""));
        params.put("detailStreet", SPUtils.getDataString("address", "", CommitOrderActivity.this));
        final LoadingDialog loadingDialog = new LoadingDialog(this, false);
        loadingDialog.show("下单中");
        HttpUtils.post(this, MyConfig.COMMIT_ORDER, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                CommitOrderResult result = new Gson().fromJson(response, CommitOrderResult.class);
                if (result.getStatus().equals("0000")) {
                    boolean sIsWXAppInstalledAndSupported = api.isWXAppInstalled() && api.isWXAppSupportAPI();
                    if (!sIsWXAppInstalledAndSupported) {
                        Toast.makeText(CommitOrderActivity.this, "您的手机尚未安装微信或微信版本不支持支付功能", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(CommitOrderActivity.this, OrderListActivity.class));
                    } else {
                        getPrePay(result.getData());
                    }
                } else {
                    ShowDialog.showTipPopup(CommitOrderActivity.this, result.getError(), R.string.sure, new OperationCallback() {
                        @Override
                        public void execute() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                ShowDialog.showTipPopup(CommitOrderActivity.this, "下单失败", R.string.sure, new OperationCallback() {
                    @Override
                    public void execute() {

                    }
                });
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
                    finish();
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                ShowDialog.showTipPopup(CommitOrderActivity.this, "支付失败", R.string.sure, new OperationCallback() {
                    @Override
                    public void execute() {
                        finish();
                        startActivity(new Intent(CommitOrderActivity.this, OrderListActivity.class));
                    }
                });
            }
        });
    }

    private void calculate() {
        double totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            List<QueryCart.DataBean.CartsBean> childs = groups.get(i).getCarts();
            for (int j = 0; j < childs.size(); j++) {
                QueryCart.DataBean.CartsBean product = childs.get(j);
                if (product.getPrice() != null) {
                    if (!TextUtils.isEmpty(product.getPrice()))
                        totalPrice += Double.valueOf(product.getPrice()) * product.getServiceAmount();
                }
            }
        }

        final DecimalFormat df = new DecimalFormat("#0.00");

        orderTv.setText("¥：" + df.format(totalPrice) + "    支付");
    }

    public class ShopcartAdapter extends BaseExpandableListAdapter {

        private List<QueryCart.DataBean> groups;
        private Context context;

        public ShopcartAdapter(List<QueryCart.DataBean> groups, Context context) {
            this.groups = groups;
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return groups.get(groupPosition).getCarts().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            List<QueryCart.DataBean.CartsBean> childs = groups.get(groupPosition).getCarts();
            return childs.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            final GroupViewHolder gholder;
            if (convertView == null) {
                gholder = new GroupViewHolder();
                convertView = View.inflate(context, R.layout.shopping_car_merchant_item, null);
                AutoUtils.autoSize(convertView);

                gholder.shopAvar = (SimpleDraweeView) convertView.findViewById(R.id.shopping_car_shop_avar);
                gholder.shopAvar.setScaleType(ImageView.ScaleType.CENTER_CROP);

                gholder.shopName = (TextView) convertView.findViewById(R.id.shopping_car_shop_name);
                gholder.v1 = (View) convertView.findViewById(R.id.view1);


                convertView.setTag(gholder);
            } else {
                gholder = (GroupViewHolder) convertView.getTag();
            }
            final QueryCart.DataBean group = (QueryCart.DataBean) getGroup(groupPosition);

            if (group.getUserIcon() != null) {
                if (!TextUtils.isEmpty(group.getUserIcon())) {
                    gholder.shopAvar.setImageURI(Uri.parse(group.getUserIcon()));
                } else {
                    gholder.shopAvar.setImageURI(Uri.parse(group.getUserIcon()));
                }
            } else {
                gholder.shopAvar.setImageResource(R.mipmap.default_avar);
            }
            gholder.shopName.setText(group.getShopName());

            if (groupPosition == 0) {
                gholder.v1.setVisibility(View.GONE);
            } else {
                gholder.v1.setVisibility(View.VISIBLE);
            }
            notifyDataSetChanged();
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
            final ChildViewHolder cholder;
            if (convertView == null) {
                cholder = new ChildViewHolder();
                convertView = View.inflate(context, R.layout.commit_order_service_item_layout, null);
                AutoUtils.autoSize(convertView);
                cholder.serviceName = (TextView) convertView.findViewById(R.id.service_name);
                cholder.serviceNum = (TextView) convertView.findViewById(R.id.service_num);
                cholder.servicePrice = (TextView) convertView.findViewById(R.id.service_price);
                convertView.setTag(cholder);
            } else {
                cholder = (ChildViewHolder) convertView.getTag();
            }

            final QueryCart.DataBean.CartsBean goodsInfo = (QueryCart.DataBean.CartsBean) getChild(groupPosition, childPosition);
            if (goodsInfo != null) {

                cholder.serviceName.setText(goodsInfo.getServiceTitle());

                cholder.serviceNum.setText("x " + goodsInfo.getServiceAmount());

                cholder.servicePrice.setText("¥ " + goodsInfo.getPrice());

            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        /**
         * 组元素绑定器
         */
        private class GroupViewHolder {
            SimpleDraweeView shopAvar;
            TextView shopName;
            RelativeLayout shopInfo;
            View v1;
        }

        /**
         * 子元素绑定器
         */
        private class ChildViewHolder {
            TextView serviceName;
            TextView serviceNum;
            TextView servicePrice;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.post(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(runnable);
    }


    private Runnable runnable = new Runnable() {
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    String address = "";
                    if (!TextUtils.isEmpty(SPUtils.getDataString("name", "", CommitOrderActivity.this))) {
                        address = address + SPUtils.getDataString("name", "", CommitOrderActivity.this);
                    }
                    if (!TextUtils.isEmpty(SPUtils.getDataString("phone", "", CommitOrderActivity.this))) {
                        address = address + SPUtils.getDataString("phone", "", CommitOrderActivity.this);
                    }
                    if (!TextUtils.isEmpty(SPUtils.getDataString("city", "", CommitOrderActivity.this))) {
                        address = address + SPUtils.getDataString("city", "", CommitOrderActivity.this);
                    }
                    if (!TextUtils.isEmpty(SPUtils.getDataString("address", "", CommitOrderActivity.this))) {
                        address = address + SPUtils.getDataString("address", "", CommitOrderActivity.this);
                    }
                    if (!TextUtils.isEmpty(SPUtils.getDataString("detail", "", CommitOrderActivity.this))) {
                        address = address + SPUtils.getDataString("detail", "", CommitOrderActivity.this);
                    }
                    addressTv.setText(address);
                    break;
            }
        }
    };
}
