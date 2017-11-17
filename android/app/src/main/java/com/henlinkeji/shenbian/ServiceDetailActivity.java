package com.henlinkeji.shenbian;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.blankj.utilcode.utils.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
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
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.bean.GetUpToken;
import com.henlinkeji.shenbian.bean.HeadTop;
import com.henlinkeji.shenbian.bean.ServiceDetail;
import com.sendtion.xrichtext.RichTextView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

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

public class ServiceDetailActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView backImg;
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

    private int serviceId;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_service_detail);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        serviceId = getIntent().getIntExtra("id", 0);
        loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("身边");
    }

    @Override
    protected void initData() {
        getDetail();
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
        params.put("serviceId", serviceId+"");
        HttpUtils.post(this, MyConfig.SERVICE_DETAIL, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                loadingDialog.exit();
                ServiceDetail serviceDetail = new Gson().fromJson(response, ServiceDetail.class);
                if (serviceDetail.getStatus().equals("0000")) {
                    initService(serviceDetail.getData());
                }
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
            }
        });
    }

    private void initService(final ServiceDetail.DataBean serviceDetail) {
        if (serviceDetail.getUserName() != null) {
            if (!TextUtils.isEmpty(serviceDetail.getUserName())) nameTv.setText(serviceDetail.getUserName());
        }
        if (serviceDetail.getServiceTitle() != null) {
            if (!TextUtils.isEmpty(serviceDetail.getServiceTitle())) titleTv.setText(serviceDetail.getServiceTitle());
        }
        if (serviceDetail.getServicePrice() != null) {
            if (!TextUtils.isEmpty(serviceDetail.getServicePrice())) priceTv.setText(serviceDetail.getServicePrice());
        }
        if (serviceDetail.getServiceType() == 0) {
            typeTv.setText("在线服务");
        } else if (serviceDetail.getServiceType() == 1) {
            typeTv.setText("上门服务");
        } else if (serviceDetail.getServiceType() == 2) {
            typeTv.setText("到店服务");
        }
        show(serviceDetail.getServiceDescription());
        LocationUtil.getCurrentLocation(new LocationUtil.MyLocationListener() {
            @Override
            public void result(AMapLocation location) {
                LogUtil.e("=====" + location.getLatitude());
                LatLng latLng1 = new LatLng(location.getLatitude(), location.getLongitude());
                String[] locs = serviceDetail.getLocation().split(",");
                LatLng latLng2 = null;
                if (locs.length >= 1) {
                    latLng2 = new LatLng(Double.valueOf(locs[1]), Double.valueOf(locs[0]));
                }
                float dis = AMapUtils.calculateLineDistance(latLng1, latLng2);
                if (dis < 1000) {
                    distanceTv.setText(dis + "m");
                } else {
                    distanceTv.setText(Utils.mToKm(dis));
                }
            }
        });
        sendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance() != null) {
                    if (serviceDetail.getUserName() != null) {
                        if (!TextUtils.isEmpty(serviceDetail.getUserName())) {
                            RongIM.getInstance().startPrivateChat(ServiceDetailActivity.this, serviceDetail.getUserId() + "", serviceDetail.getUserName());
                        }else {
                            RongIM.getInstance().startPrivateChat(ServiceDetailActivity.this, serviceDetail.getUserId() + "","身边"+ serviceDetail.getUserId());
                        }
                    }else {
                        RongIM.getInstance().startPrivateChat(ServiceDetailActivity.this, serviceDetail.getUserId() + "", "身边"+ serviceDetail.getUserId());
                    }
                }
            }
        });
        carLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCart();
            }
        });
    }
    private void addCart() {
        final LoadingDialog  loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("身边");
        Map<String, String> params = new HashMap<>();
        params.put("serviceId", serviceId+"");
        params.put("token", SPUtils.getToken(this));
        params.put("amount", 1+"");
        HttpUtils.post(this, MyConfig.ADD_CART, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                loadingDialog.exit();
                GetUpToken getUpToken = new Gson().fromJson(response, GetUpToken.class);
                if (getUpToken.getStatus().equals("0000")) {
                    ToastUtils.disPlayShort(ServiceDetailActivity.this,"添加成功");
                }else {
                    ShowDialog.showTipPopup(ServiceDetailActivity.this,getUpToken.getError(), R.string.sure, new OperationCallback() {
                        @Override
                        public void execute() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
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
}
