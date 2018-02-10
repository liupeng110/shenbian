package com.henlinkeji.shenbian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.bean.QueryCart;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView backImg;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.toolbar)
    RelativeLayout titleRl;
    @BindView(R.id.shopping_car_list)
    ExpandableListView expandableListView;
    @BindView(R.id.order)
    TextView orderTv;
    @BindView(R.id.no_data)
    TextView noTv;

    private ShopcartAdapter selva;

    private List<QueryCart.DataBean> groups = new ArrayList<>();
    private List<QueryCart.DataBean> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shopping_cart);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        titleTv.setText("购物车");
        backImg.setImageResource(R.mipmap.back2);
        titleRl.setBackgroundColor(Color.parseColor("#009698"));
    }

    @Override
    protected void initData() {
        getCart();
    }

    @Override
    protected void initListener() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getCart() {
        final LoadingDialog loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("获取购物车列表中");
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(this));
        HttpUtils.post(this, MyConfig.QUERY_CART, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog!=null) {
                    loadingDialog.exit();
                }
                QueryCart queryCart = new Gson().fromJson(response, QueryCart.class);
                if (queryCart.getStatus().equals("0000")) {
                    groups = queryCart.getData();
                    if (groups.size() == 0) {
                        noTv.setVisibility(View.VISIBLE);
                        expandableListView.setVisibility(View.GONE);
                        orderTv.setVisibility(View.GONE);
                    } else {
                        noTv.setVisibility(View.GONE);
                        expandableListView.setVisibility(View.VISIBLE);
                        orderTv.setVisibility(View.VISIBLE);
                        initEvents();
                    }
                }else {
                    noTv.setVisibility(View.VISIBLE);
                    expandableListView.setVisibility(View.GONE);
                    orderTv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog!=null) {
                    loadingDialog.exit();
                }
            }
        });
    }

    private void initEvents() {
        selva = new ShopcartAdapter(groups, this);
        selva.setCheckInterface(new CheckInterface() {
            @Override
            public void checkGroup(int groupPosition, boolean isChecked) {
                List<QueryCart.DataBean.CartsBean> childs = groups.get(groupPosition).getCarts();
                for (int i = 0; i < childs.size(); i++) {
                    childs.get(i).setChoosed(isChecked);
                }
//                if (isAllCheck())
//                    allSelect.setChecked(true);
//                else
//                    allSelect.setChecked(false);
                selva.notifyDataSetChanged();
                calculate();
            }

            @Override
            public void checkChild(int groupPosition, int childPosiTion, boolean isChecked) {
                boolean allChildSameState = true;
                QueryCart.DataBean group = groups.get(groupPosition);
                group.getCarts().get(childPosiTion).setChoosed(isChecked);
                List<QueryCart.DataBean.CartsBean> childs = groups.get(groupPosition).getCarts();
                for (int i = 0; i < childs.size(); i++) {
                    if (childs.get(i).isChoosed() != isChecked) {
                        allChildSameState = false;
                        break;
                    }
                }

                if (allChildSameState) {
                    group.setChoosed(isChecked);
                } else {
                    group.setChoosed(false);
                }

//                if (isAllCheck()) {
//                    allSelect.setChecked(true);
//                } else {
//                    allSelect.setChecked(false);
//                }
                selva.notifyDataSetChanged();
                calculate();
            }
        });

        expandableListView.setAdapter(selva);

        for (int i = 0; i < selva.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;
            }
        });
        calculate();
    }

    private void calculate() {
        double totalPrice = 0.00;

        if (groups!=null){
            orderTv.setVisibility(View.VISIBLE);
        }else {
            orderTv.setVisibility(View.GONE);
        }

        for (int i = 0; i < groups.size(); i++) {
            List<QueryCart.DataBean.CartsBean> childs = groups.get(i).getCarts();
            for (int j = 0; j < childs.size(); j++) {
                QueryCart.DataBean.CartsBean product = childs.get(j);
                if (product.isChoosed()) {
                    if (product.getPrice() != null) {
                        if (!TextUtils.isEmpty(product.getPrice()))
                            totalPrice += Double.valueOf(product.getPrice()) * product.getServiceAmount();
                    }
                }
            }
        }

        final DecimalFormat df = new DecimalFormat("#0.00");

        orderTv.setText("合计：" + df.format(totalPrice) + "    下单");

        orderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderList.clear();
                for (int i = 0; i < groups.size(); i++) {
                    QueryCart.DataBean data = groups.get(i);
                    QueryCart.DataBean cartListEntity = new QueryCart.DataBean();
                    List<QueryCart.DataBean.CartsBean> goods = new ArrayList<>();
                    for (QueryCart.DataBean.CartsBean cart : data.getCarts()) {
                        if (cart.isChoosed()) {
                            goods.add(cart);
                        }
                    }
                    cartListEntity.setUserIcon(data.getUserIcon());
                    cartListEntity.setShopName(data.getShopName());
                    cartListEntity.setShopUserId(data.getShopUserId());
                    cartListEntity.setCarts(goods);

                    if (goods.size() != 0) {
                        orderList.add(cartListEntity);
                    }
                }

                if (orderList.size() > 0) {
                    Intent intent = new Intent(ShoppingCartActivity.this, CommitOrderActivity.class);
                    intent.putExtra("orderlist", (Serializable) orderList);
                    startActivity(intent);
                } else {
                    ToastUtils.disPlayShort(ShoppingCartActivity.this, "请选择要支付的商品");
                }
            }
        });
    }


    public class ShopcartAdapter extends BaseExpandableListAdapter {

        private List<QueryCart.DataBean> groups;
        private Context context;
        private CheckInterface checkInterface;

        /**
         * 构造函数
         *
         * @param groups  组元素列表
         * @param context
         */
        public ShopcartAdapter(List<QueryCart.DataBean> groups, Context context) {
            this.groups = groups;
            this.context = context;
        }

        public void setCheckInterface(CheckInterface checkInterface) {
            this.checkInterface = checkInterface;
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
                gholder.cb_check = (CheckBox) convertView.findViewById(R.id.shopping_car_select);
                gholder.shopAvar.setScaleType(ImageView.ScaleType.CENTER_CROP);

                gholder.shopName = (TextView) convertView.findViewById(R.id.shopping_car_shop_name);
                gholder.v1 = (View) convertView.findViewById(R.id.view1);

                gholder.shopInfo = (RelativeLayout) convertView.findViewById(R.id.shopping_car_info);

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

            gholder.cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    group.setChoosed(((CheckBox) v).isChecked());
                    checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
                }
            });
            gholder.cb_check.setChecked(group.isChoosed());
            notifyDataSetChanged();
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
            final ChildViewHolder cholder;
            if (convertView == null) {
                cholder = new ChildViewHolder();
                convertView = View.inflate(context, R.layout.shopping_car_service_item, null);
                AutoUtils.autoSize(convertView);

                convertView.setMinimumHeight(224);

                cholder.cb_check = (CheckBox) convertView.findViewById(R.id.shopping_service_item_select);

                cholder.serviceAvar = (ImageView) convertView.findViewById(R.id.shopping_service_item_img);
                cholder.serviceAvar.setScaleType(ImageView.ScaleType.CENTER_CROP);

                cholder.serviceName = (TextView) convertView.findViewById(R.id.shopping_service_item_name);
                cholder.serviceIntr = (TextView) convertView.findViewById(R.id.shopping_service_item_intr);
                cholder.servicePrice = (TextView) convertView.findViewById(R.id.shopping_service_item_price);
                cholder.add = (Button) convertView.findViewById(R.id.btnIncrease);
                cholder.minus = (Button) convertView.findViewById(R.id.btnDecrease);
                cholder.numEdt = (EditText) convertView.findViewById(R.id.num);
                cholder.detail = (RelativeLayout) convertView.findViewById(R.id.ll_service_detail);

                convertView.setTag(cholder);
            } else {
                cholder = (ChildViewHolder) convertView.getTag();
            }

            final QueryCart.DataBean.CartsBean goodsInfo = (QueryCart.DataBean.CartsBean) getChild(groupPosition, childPosition);
            if (goodsInfo != null) {
                if (goodsInfo.getHomeUrl() != null) {
                    if (!TextUtils.isEmpty(goodsInfo.getHomeUrl())) {
                        Picasso.with(ShoppingCartActivity.this).load(goodsInfo.getHomeUrl()).into(cholder.serviceAvar);
                    }
                }

                cholder.serviceName.setText(goodsInfo.getServiceTitle());

                cholder.serviceIntr.setText(goodsInfo.getServiceDescription());

                cholder.servicePrice.setText(goodsInfo.getPrice());

                cholder.numEdt.setText(goodsInfo.getServiceAmount() + "");

                cholder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (goodsInfo.getServiceAmount() == 100) {
                            ToastUtils.disPlayShort(ShoppingCartActivity.this, "数量最多为100");
                        } else {
                            updateNum(goodsInfo.getServiceId(), goodsInfo.getServiceAmount() + 1);
                            goodsInfo.setServiceAmount(goodsInfo.getServiceAmount() + 1);
                            notifyDataSetChanged();
                            calculate();
                        }
                    }
                });
                cholder.minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (goodsInfo.getServiceAmount() == 1) {
                            ToastUtils.disPlayShort(ShoppingCartActivity.this, "数量最少为1");
                        } else {
                            updateNum(goodsInfo.getServiceId(), goodsInfo.getServiceAmount() - 1);
                            goodsInfo.setServiceAmount(goodsInfo.getServiceAmount() - 1);
                            notifyDataSetChanged();
                            calculate();
                        }
                    }
                });
                cholder.numEdt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().isEmpty()) return;
                        final int amount = Integer.valueOf(s.toString());
                        if (amount > 100) {
                            updateNum(goodsInfo.getServiceId(), 100);
                            goodsInfo.setServiceAmount(100);
                            notifyDataSetChanged();
                            calculate();
                        } else {
                            updateNum(goodsInfo.getServiceId(), amount);
                            goodsInfo.setServiceAmount(amount);
                            notifyDataSetChanged();
                            calculate();
                        }
                    }
                });

                cholder.cb_check.setChecked(goodsInfo.isChoosed());
                cholder.cb_check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goodsInfo.setChoosed(((CheckBox) v).isChecked());
                        cholder.cb_check.setChecked(((CheckBox) v).isChecked());
                        checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                    }
                });

                cholder.serviceAvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ServiceDetailActivity.class);
                        intent.putExtra("id", goodsInfo.getServiceId());
                        context.startActivity(intent);
                    }
                });

                cholder.detail.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ShowDialog.showSelectNoTitlePopup(ShoppingCartActivity.this, "确定删除该条服务吗?", R.string.sure, R.string.cancel, new OperationCallback() {
                            @Override
                            public void execute() {
                                delete(goodsInfo.getServiceId());
                            }
                        }, new OperationCallback() {
                            @Override
                            public void execute() {

                            }
                        });
                        return false;
                    }
                });
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        private void updateNum(int serviceId, int amount) {
            Map<String, String> params = new HashMap<>();
            params.put("token", SPUtils.getToken(ShoppingCartActivity.this));
            params.put("serviceId", serviceId + "");
            params.put("amount", amount + "");
            HttpUtils.post(ShoppingCartActivity.this, MyConfig.UPDATE_CART, params, new HttpUtils.HttpPostCallBackListener() {
                @Override
                public void onSuccess(String response) {
                }

                @Override
                public void onFailure(String response) {
                }
            });
        }

        private void delete(int serviceId) {
            Map<String, String> params = new HashMap<>();
            params.put("token", SPUtils.getToken(ShoppingCartActivity.this));
            params.put("serviceId", serviceId + "");
            params.put("amount", 0 + "");
            HttpUtils.post(ShoppingCartActivity.this, MyConfig.UPDATE_CART, params, new HttpUtils.HttpPostCallBackListener() {
                @Override
                public void onSuccess(String response) {
                    getCart();
                }

                @Override
                public void onFailure(String response) {
                }
            });
        }


        /**
         * 组元素绑定器
         */
        private class GroupViewHolder {
            CheckBox cb_check;
            SimpleDraweeView shopAvar;
            TextView shopName;
            RelativeLayout shopInfo;
            View v1;
        }

        /**
         * 子元素绑定器
         */
        private class ChildViewHolder {
            CheckBox cb_check;
            ImageView serviceAvar;
            TextView serviceName;
            TextView serviceIntr;
            TextView servicePrice;
            Button add;
            Button minus;
            EditText numEdt;
            RelativeLayout detail;
        }

    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    @Override
    public void onPause() {
        super.onPause();
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
                    getCart();
                    break;
            }
        }
    };
}
