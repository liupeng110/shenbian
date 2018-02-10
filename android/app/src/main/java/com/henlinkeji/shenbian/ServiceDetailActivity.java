package com.henlinkeji.shenbian;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.GlideImageLoader;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.ImageUtils;
import com.henlinkeji.shenbian.base.utils.LocationUtil;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.StringUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.base.view.FullyLinearLayoutManager;
import com.henlinkeji.shenbian.base.view.NoScrollRecyclerView;
import com.henlinkeji.shenbian.base.view.SharePopupWindow;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.base.view.SpacesItemDecoration;
import com.henlinkeji.shenbian.base.view.rvadapter.CommonAdapter;
import com.henlinkeji.shenbian.base.view.rvadapter.base.ViewHolder;
import com.henlinkeji.shenbian.bean.GetUpToken;
import com.henlinkeji.shenbian.bean.HeadTop;
import com.henlinkeji.shenbian.bean.QueryCart;
import com.henlinkeji.shenbian.bean.ServiceDetail;
import com.henlinkeji.shenbian.bean.ShareModel;
import com.sendtion.xrichtext.RichTextView;
import com.squareup.picasso.Picasso;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ServiceDetailActivity extends AutoLayoutActivity {
    @BindView(R.id.back)
    ImageView backImg;
    @BindView(R.id.collection)
    ImageView collectImg;
    @BindView(R.id.share)
    ImageView shareImg;
    @BindView(R.id.service_content)
    LinearLayout contentTv;
    @BindView(R.id.user_name)
    TextView nameTv;
    @BindView(R.id.distance)
    TextView distanceTv;
    @BindView(R.id.service_title)
    TextView titleTv;
    @BindView(R.id.price)
    TextView priceTv;
    @BindView(R.id.type)
    TextView typeTv;
    @BindView(R.id.send)
    TextView sendTv;
    @BindView(R.id.order)
    TextView orderTv;
    @BindView(R.id.join_car)
    LinearLayout carLin;
    @BindView(R.id.user_avator)
    SimpleDraweeView userAvatorImg;
    @BindView(R.id.recommend_recy)
    RecyclerView recommendRecy;
    @BindView(R.id.num)
    TextView numTv;
    @BindView(R.id.score)
    TextView scoreTv;
    RatingBar ratingBar;

    @BindView(R.id.banner)
    Banner banner;

    private int serviceId;

    private LoadingDialog loadingDialog;

    private ServiceDetail.DataBean serviceDetail;
    private CommonAdapter<ServiceDetail.DataBean.RecommendsBean> recommendAdapter;

    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    private int collectFlag;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        MyApplication.getInstance().addActivity(this);
        initView();
        initInstence();
        initData();
        initListener();
    }


    protected void initView() {
        backImg = (ImageView) findViewById(R.id.back);
        collectImg = (ImageView) findViewById(R.id.collection);
        shareImg = (ImageView) findViewById(R.id.share);
        contentTv = (LinearLayout) findViewById(R.id.service_content);
        nameTv = (TextView) findViewById(R.id.user_name);
        distanceTv = (TextView) findViewById(R.id.distance);
        titleTv = (TextView) findViewById(R.id.service_title);
        priceTv = (TextView) findViewById(R.id.price);
        typeTv = (TextView) findViewById(R.id.type);
        sendTv = (TextView) findViewById(R.id.send);
        orderTv = (TextView) findViewById(R.id.order);
        carLin = (LinearLayout) findViewById(R.id.join_car);
        userAvatorImg = (SimpleDraweeView) findViewById(R.id.user_avator);
        recommendRecy = (RecyclerView) findViewById(R.id.recommend_recy);
        numTv = (TextView) findViewById(R.id.num);
        scoreTv = (TextView) findViewById(R.id.score);
        banner = (Banner) findViewById(R.id.banner);
        ratingBar = (RatingBar) findViewById(R.id.rb_normal);
    }

    protected void initInstence() {
        serviceId = getIntent().getIntExtra("id", 0);
        loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("获取服务详情");

        //设置布局管理器
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommendRecy.setLayoutManager(linearLayoutManager);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        // 将该app注册到微信
        api.registerApp(Constants.APP_ID);
    }

    protected void initData() {
        getDetail();
    }

    protected void initListener() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recommendAdapter = new CommonAdapter<ServiceDetail.DataBean.RecommendsBean>(this, R.layout.imageview_layout) {
            @Override
            protected void convert(ViewHolder holder, final ServiceDetail.DataBean.RecommendsBean bean, int position) {
                ImageView img = holder.getView(R.id.img);
                if (!TextUtils.isEmpty(bean.getHomeUrl())) {
                    Picasso.with(ServiceDetailActivity.this).load(bean.getHomeUrl()).into(img);
                }
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ServiceDetailActivity.this, ServiceDetailActivity.class);
                        intent.putExtra("id", bean.getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView) {
                super.onViewHolderCreated(holder, itemView);
                AutoUtils.autoSize(itemView);
            }
        };
        recommendRecy.setAdapter(recommendAdapter);

        userAvatorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDetailActivity.this, MerchantPageActivity.class);
                intent.putExtra("id", serviceDetail.getUserId() + "");
                startActivity(intent);
            }
        });

        shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void getDetail() {
        Map<String, String> params = new HashMap<>();
        params.put("serviceId", serviceId + "");
        if (!TextUtils.isEmpty(SPUtils.getToken(
                this))) {
            params.put("token", SPUtils.getToken(this));
        }
        HttpUtils.post(this, MyConfig.SERVICE_DETAIL, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                loadingDialog.exit();
                ServiceDetail service = new Gson().fromJson(response, ServiceDetail.class);
                if (service.getStatus().equals("0000")) {
                    serviceDetail = service.getData();
                    initService();
                } else {
                    ToastUtils.disPlayShort(ServiceDetailActivity.this, "服务详情查询失败");
                    finish();
                }
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
                ToastUtils.disPlayShort(ServiceDetailActivity.this, "服务详情查询失败");
                finish();
            }
        });
    }

    private void initService() {
        if (serviceDetail.getUserName() != null) {
            if (!TextUtils.isEmpty(serviceDetail.getUserName())) nameTv.setText(serviceDetail.getUserName());
        }
        if (serviceDetail.getServiceTitle() != null) {
            if (!TextUtils.isEmpty(serviceDetail.getServiceTitle())) titleTv.setText(serviceDetail.getServiceTitle());
        }
        if (serviceDetail.getStarRating() != null) {
            if (!TextUtils.isEmpty(serviceDetail.getStarRating())) {
                scoreTv.setText("(" + serviceDetail.getStarRating() + ")");
                ratingBar.setRating(Float.valueOf(serviceDetail.getStarRating()));
            }
        }

        numTv.setText(serviceDetail.getEvaluateCount() + "评论");
        priceTv.setText(serviceDetail.getServicePrice() + "");
        if (serviceDetail.getServiceType() == 0) {
            typeTv.setText("在线服务");
        } else if (serviceDetail.getServiceType() == 1) {
            typeTv.setText("上门服务");
        } else if (serviceDetail.getServiceType() == 2) {
            typeTv.setText("到店服务");
        }
        if (serviceDetail.getUserIcon() != null) {
            if (!TextUtils.isEmpty(serviceDetail.getUserIcon())) {
                Uri uri = Uri.parse(serviceDetail.getUserIcon());
                userAvatorImg.setImageURI(uri);
            }
        }

        for (int i = 0; i < serviceDetail.getAdvs().size(); i++) {
            titles.add(serviceDetail.getAdvs().get(i).getAdvDesc());
            images.add(serviceDetail.getAdvs().get(i).getAdvImgUrl());
        }

        if (serviceDetail.getCollectFlag() == 0) {
            collectFlag = 0;
            collectImg.setImageResource(R.mipmap.collection3);
        } else {
            collectFlag = 1;
            collectImg.setImageResource(R.mipmap.collection1);
        }

        if (images.size() > 0) {
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置banner样式
            banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(images);
            //设置banner动画效果
            banner.setBannerAnimation(Transformer.ForegroundToBackground);
            //设置标题集合（当banner样式有显示title时）
            banner.setBannerTitles(titles);
            //设置自动轮播，默认为true
            banner.isAutoPlay(true);
            //设置轮播时间
            banner.setDelayTime(1500);
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }

        recommendAdapter.setDatas(serviceDetail.getRecommends());

        show(serviceDetail.getServiceDescription());
        LatLng latLng1 = new LatLng(Double.valueOf(SPUtils.getLatitude(this)), Double.valueOf(SPUtils.getLongitude(this)));
        if (!TextUtils.isEmpty(serviceDetail.getLocation())) {
            if (serviceDetail.getLocation().length() > 0) {
                String[] locs = serviceDetail.getLocation().split(",");
                LatLng latLng2 = null;
                if (locs.length >= 1) {
                    latLng2 = new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0]));
                }
                float dis = AMapUtils.calculateLineDistance(latLng1, latLng2);
                if (dis < 1000) {
                    DecimalFormat decimalFormat = new DecimalFormat("0");
                    distanceTv.setText(decimalFormat.format(dis) + "m");
                } else {
                    distanceTv.setText(Utils.mToKm(dis));
                }
            }
        }
        sendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtils.getToken(ServiceDetailActivity.this))) {
                    startActivity(new Intent(ServiceDetailActivity.this, LoginActivity.class));
                } else {
                    if (RongIM.getInstance() != null) {
                        if (serviceDetail.getUserName() != null) {
                            if (!TextUtils.isEmpty(serviceDetail.getUserName())) {
                                RongIM.getInstance().startPrivateChat(ServiceDetailActivity.this, serviceDetail.getUserId() + "", serviceDetail.getUserName());
                            } else {
                                RongIM.getInstance().startPrivateChat(ServiceDetailActivity.this, serviceDetail.getUserId() + "", "身边" + serviceDetail.getUserId());
                            }
                        } else {
                            RongIM.getInstance().startPrivateChat(ServiceDetailActivity.this, serviceDetail.getUserId() + "", "身边" + serviceDetail.getUserId());
                        }
                    }
                }
            }
        });
        carLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtils.getToken(ServiceDetailActivity.this))) {
                    startActivity(new Intent(ServiceDetailActivity.this, LoginActivity.class));
                } else {
                    addCart();
                }
            }
        });
        orderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtils.getToken(ServiceDetailActivity.this))) {
                    startActivity(new Intent(ServiceDetailActivity.this, LoginActivity.class));
                } else {
                    if (serviceDetail != null) {
                        List<QueryCart.DataBean> orderList = new ArrayList<>();
                        QueryCart.DataBean cartListEntity = new QueryCart.DataBean();
                        List<QueryCart.DataBean.CartsBean> goods = new ArrayList<>();
                        QueryCart.DataBean.CartsBean cartsBean = new QueryCart.DataBean.CartsBean();
                        cartsBean.setServiceAmount(1);
                        cartsBean.setServiceId(serviceDetail.getId());
                        cartsBean.setChoosed(true);
                        cartsBean.setPrice(serviceDetail.getServicePrice() + "");
                        cartsBean.setServiceTitle(serviceDetail.getServiceTitle());
                        goods.add(cartsBean);
                        cartListEntity.setUserIcon(serviceDetail.getUserIcon());
                        cartListEntity.setShopName(serviceDetail.getUserName());
                        cartListEntity.setShopUserId(serviceDetail.getUserId());
                        cartListEntity.setCarts(goods);

                        if (goods.size() != 0) {
                            orderList.add(cartListEntity);
                        }
                        if (orderList.size() > 0) {
                            Intent intent = new Intent(ServiceDetailActivity.this, CommitOrderActivity.class);
                            intent.putExtra("orderlist", (Serializable) orderList);
                            startActivity(intent);
                        }
                    } else {
                        getDetail();
                    }
                }
            }
        });
        collectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtils.getToken(ServiceDetailActivity.this))) {
                    startActivity(new Intent(ServiceDetailActivity.this, LoginActivity.class));
                } else {
                    if (collectFlag == 0) {
                        addCollect(1);
                    } else {
                        addCollect(0);
                    }
                }
            }
        });
    }

    private void addCollect(final int collectionStatus) {
        final LoadingDialog loadingDialog = new LoadingDialog(this, true);
        if (collectionStatus == 0) {
            loadingDialog.show("取消收藏中……");
        } else {
            loadingDialog.show("添加收藏中……");
        }
        Map<String, String> params = new HashMap<>();
        params.put("serviceId", serviceId + "");
        params.put("token", SPUtils.getToken(this));
        params.put("collectionStatus", collectionStatus + "");
        LogUtils.e("==collectionStatus==="+collectionStatus);
        HttpUtils.post(this, MyConfig.COLLECT, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                GetUpToken getUpToken = new Gson().fromJson(response, GetUpToken.class);
                if (getUpToken.getStatus().equals("0000")) {
                    if (collectionStatus == 0) {
                        collectFlag = 0;
                        collectImg.setImageResource(R.mipmap.collection3);
                        ToastUtils.disPlayShort(ServiceDetailActivity.this, "已取消收藏");
                    } else {
                        collectFlag = 1;
                        collectImg.setImageResource(R.mipmap.collection1);
                        ToastUtils.disPlayShort(ServiceDetailActivity.this, "收藏成功");
                    }
                } else {
                    ShowDialog.showTipPopup(ServiceDetailActivity.this, getUpToken.getError(), R.string.sure, new OperationCallback() {
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
            }
        });
    }

    private void addCart() {
        final LoadingDialog loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("添加购物车中");
        Map<String, String> params = new HashMap<>();
        params.put("serviceId", serviceId + "");
        params.put("token", SPUtils.getToken(this));
        params.put("amount", 1 + "");
        HttpUtils.post(this, MyConfig.ADD_CART, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                GetUpToken getUpToken = new Gson().fromJson(response, GetUpToken.class);
                if (getUpToken.getStatus().equals("0000")) {
                    ToastUtils.disPlayShort(ServiceDetailActivity.this, "添加成功");
                } else {
                    ShowDialog.showTipPopup(ServiceDetailActivity.this, getUpToken.getError(), R.string.sure, new OperationCallback() {
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
            }
        });
    }

    private void show(String html) {
        List<String> textList = StringUtils.cutStringByImgTag(html);
        for (int i = 0; i < textList.size(); i++) {
            String text = textList.get(i);
            if (text.contains("<img") && text.contains("src=")) {
                String imagePath = StringUtils.getImgSrc(text);
                final ImageView imageView = new ImageView(this);
                //获取图片真正的宽高
                Glide.with(this).load(imagePath).asBitmap()//强制Glide返回一个Bitmap对象
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                                int width = bitmap.getWidth();
                                int height = bitmap.getHeight();
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                params.setMargins(0, 10, 0, 10);
                                params.width = ScreenUtils.getScreenWidth(ServiceDetailActivity.this);
                                params.height = height * params.width / width;
                                imageView.setLayoutParams(params);
                            }
                        });
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(this).load(imagePath).into(imageView);
                contentTv.addView(imageView);
            } else {
                final TextView showText = new TextView(this);
                showText.setTextColor(Color.parseColor("#4f5965"));
                showText.setTextSize(13);
                showText.setText(text);
                // set 文本大小
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //set 四周距离
                params.setMargins(0, 10, 0, 10);
                showText.setLayoutParams(params);
                contentTv.addView(showText);
            }
        }
    }

    private void share() {
        ShareModel model = new ShareModel();
        if (serviceDetail != null) {
            if (!TextUtils.isEmpty(serviceDetail.getCoverImage())) {
                model.setImageUrl(serviceDetail.getCoverImage());
            }
            if (!TextUtils.isEmpty(serviceDetail.getServiceDescription())) {
                model.setText(serviceDetail.getServiceDescription());
            }else {
                model.setText("这里有个好东西分享给你");
            }
            if (!TextUtils.isEmpty(serviceDetail.getServiceTitle())) {
                model.setTitle(serviceDetail.getServiceTitle());
            }else {
                model.setTitle("U服");
            }
        }
        model.setUrl("http://shouji.baidu.com/software/23158103.html");

        SharePopupWindow share = new SharePopupWindow(ServiceDetailActivity.this, model, api);

        share.showShareWindow();

        // 显示窗口 (设置layout在PopupWindow中显示的位置)
        share.showAtLocation(ServiceDetailActivity.this.findViewById(R.id.rel), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
