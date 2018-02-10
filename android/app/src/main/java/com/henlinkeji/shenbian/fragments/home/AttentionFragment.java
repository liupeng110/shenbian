package com.henlinkeji.shenbian.fragments.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.BuildConfig;
import com.henlinkeji.shenbian.LoginActivity;
import com.henlinkeji.shenbian.OrderListActivity;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.ui.BaseFragment;
import com.henlinkeji.shenbian.base.utils.GlideImageLoader;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.bean.BannerBean;
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
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.MessageContent;
import io.rong.message.ImageMessage;
import io.rong.message.InformationNotificationMessage;
import io.rong.message.LocationMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;


/**
 * Created by smyh on 2015/4/28.
 */
public class AttentionFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;

    @BindView(R.id.chat_rl)
    LinearLayout chatRl;
    @BindView(R.id.desc)
    TextView descTv;
    @BindView(R.id.time)
    TextView timeTv;
    @BindView(R.id.user_name)
    TextView nameTv;
    @BindView(R.id.img)
    SimpleDraweeView userAvatorImg;

    @BindView(R.id.order_rl)
    RelativeLayout orderRl;
    @BindView(R.id.order_desc)
    TextView orderDescTv;
    @BindView(R.id.order_time)
    TextView orderTimeTv;


    @BindView(R.id.attention_rl)
    RelativeLayout attentionRl;
    @BindView(R.id.attention_desc)
    TextView attentionDescTv;
    @BindView(R.id.attention_time)
    TextView attentionTimeTv;


    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initInstence() {
        titleTv.setText("消 息");
        titleRl.setBackgroundColor(Color.parseColor("#009698"));
    }

    @Override
    protected void initData() {
        getChat();
        getBanner();
    }

    private void show(Conversation item) {
        MessageContent messageContent = item.getLatestMessage();
        String textMessageContent = null;
        if (messageContent instanceof TextMessage) {// 文本消息
            TextMessage textMessage = (TextMessage) messageContent;
            textMessageContent = textMessage.getContent();
        } else if (messageContent instanceof ImageMessage) {// 图片消息
            textMessageContent = "[图片]";
        } else if (messageContent instanceof VoiceMessage) {// 语音消息
            textMessageContent = "[语音]";
        } else if (messageContent instanceof InformationNotificationMessage) {// 小灰条消息
            textMessageContent = "[小灰条消息]";
        } else if (messageContent instanceof LocationMessage) {// 位置消息
            textMessageContent = "[位置]";
        }
        if (!TextUtils.isEmpty(item.getConversationTitle())) {
            nameTv.setText(item.getConversationTitle());
        }else {
            nameTv.setText("有新消息");
        }
        if (!TextUtils.isEmpty(item.getPortraitUrl())) {
            userAvatorImg.setImageURI(item.getPortraitUrl());
        }
        if (textMessageContent == null) {
            descTv.setText("");
        } else {
            descTv.setText(textMessageContent);
        }
        if (item.getReceivedTime() != 0) {
            timeTv.setText(Utils.formatTime(item.getReceivedTime()));
        } else {
            timeTv.setText("");
        }
    }

    @Override
    protected void initListener() {
        titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BuildConfig.DEBUG) {
                    SPUtils.setToken("", getActivity());
                }
            }
        });

        chatRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance() != null) RongIM.getInstance().startConversationList(getActivity());
            }
        });

        orderRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtils.getToken(getActivity()))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), OrderListActivity.class));
                }
            }
        });
    }

    private void getBanner() {
        Map<String, String> params = new HashMap<>();
        HttpUtils.post(getActivity(), MyConfig.GET_BANNER, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                BannerBean bannerBean = new Gson().fromJson(response, BannerBean.class);
                if (bannerBean.getStatus().equals("0000")) {
                    for (int i = 0; i < bannerBean.getData().size(); i++) {
                        titles.add(bannerBean.getData().get(i).getAdvDesc());
                        images.add(bannerBean.getData().get(i).getAdvImgUrl());
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
                        banner.setDelayTime(3000);
                        //设置指示器位置（当banner模式中有指示器时）
                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();
                    }
                }
            }

            @Override
            public void onFailure(String response) {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            getChat();
        }
        super.onHiddenChanged(hidden);
    }

    private void getChat() {
        Conversation.ConversationType[] mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE, Conversation.ConversationType.GROUP, Conversation.ConversationType.DISCUSSION, Conversation.ConversationType.SYSTEM};
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                if (conversations != null && conversations.size() > 0) {
                    show(conversations.get(0));
                    descTv.setVisibility(View.VISIBLE);
                } else {
                    timeTv.setText("");
                    nameTv.setText("暂无消息");
                    descTv.setText("");
                    descTv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                timeTv.setText("");
                nameTv.setText("暂无消息");
                descTv.setText("");
                descTv.setVisibility(View.GONE);
            }
        }, mConversationsTypes);
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
                    getChat();
                    break;
            }
        }
    };
}
