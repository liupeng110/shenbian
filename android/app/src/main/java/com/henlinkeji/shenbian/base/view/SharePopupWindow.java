package com.henlinkeji.shenbian.base.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.util.Util;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.bean.ShareModel;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SharePopupWindow extends PopupWindow {

    private Context context;

    private ShareModel model;

    private IWXAPI api;

    private int isWebShare;

    public SharePopupWindow(Context cx, ShareModel model, IWXAPI api) {
        this.context = cx;
        this.model = model;
        this.api = api;
    }

    public void showShareWindow() {
        View view = LayoutInflater.from(context).inflate(R.layout.share_layout, null);
        GridView gridView = (GridView) view.findViewById(R.id.share_gridview);
        ShareAdapter adapter = new ShareAdapter(context);
        gridView.setAdapter(adapter);

        isWebShare = 0;

        if (model.getImageUrl() != null && !model.getImageUrl().isEmpty() && !model.getImageUrl().equals("") || model.getUrl() != null) {
            if (model.getImageUrl() != null && !model.getImageUrl().isEmpty() && !model.getImageUrl().equals(""))
                loadImage(model.getImageUrl());
            isWebShare = 1;
        }

        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        // 取消按钮
        btn_cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        gridView.setOnItemClickListener(new ShareItemClickListener(this));

    }


    private class ShareItemClickListener implements OnItemClickListener {
        private PopupWindow pop;

        public ShareItemClickListener(PopupWindow pop) {
            this.pop = pop;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            //网页分享
            if (isWebShare == 1) {
                if (position == 0) {
                    wxWebShare(0, model.getUrl(), model.getTitle(), model.getText());
                }
                if (position == 1) {
                    wxWebShare(1, model.getUrl(), model.getTitle(), model.getText());
                }
            }
            //图片分享
            if (isWebShare == 0) {
                if (position == 0) {
                    wxImgShare(0);
                }
                if (position == 1) {
                    wxImgShare(1);
                }
            }
            pop.dismiss();
        }
    }

    /**
     * 分享方法实现
     */
    private void wxWebShare(final int reqType, final String url, final String title, final String content) {
        //检测是否有微信并且微信是否支持该版本
        boolean sIsWXAppInstalledAndSupported = api.isWXAppInstalled() && api.isWXAppSupportAPI();
        if (!sIsWXAppInstalledAndSupported) {
            Toast.makeText(context, "您的手机尚未安装微信或微信版本不支持分享功能", Toast.LENGTH_SHORT).show();
            return;
        }

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = content;

        if (model.getImageUrl() != null && !model.getImageUrl().isEmpty()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            if (bm != null && baos.toByteArray().length / 1024 <= 10) {
                msg.thumbData = bmpToByteArray(bm, true);
            } else {
                Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_144);
                msg.thumbData = bmpToByteArray(bitmap1, true);
            }
        } else {
            Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_144);
            msg.thumbData = bmpToByteArray(bitmap1, true);
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        if (reqType == 0) {
            req.scene = SendMessageToWX.Req.WXSceneSession;//分享到好友
        } else {
            msg.title = title + "  " + content.replaceAll("\\n", " ");
            req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到朋友圈
        }
        req.message = msg;
        boolean isSend = api.sendReq(req);
        if (!isSend) {
            Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 分享方法实现
     */
    private void wxImgShare(final int reqType) {
        //检测是否有微信并且微信是否支持该版本
        boolean sIsWXAppInstalledAndSupported = api.isWXAppInstalled() && api.isWXAppSupportAPI();
        if (!sIsWXAppInstalledAndSupported) {
            Toast.makeText(context, "您的手机尚未安装微信或微信版本不支持分享功能", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap bitmap = model.getBm();
        WXImageObject imgObj = new WXImageObject(bitmap);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        if (model.getThumbData() != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 60, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            if (bm != null && baos.toByteArray().length / 1024 <= 10) {
                msg.thumbData = bmpToByteArray(bm, true);
            } else {
                Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_144);
                msg.thumbData = bmpToByteArray(bitmap1, true);
            }
        } else {
            Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_144);
            msg.thumbData = bmpToByteArray(bitmap1, true);
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        if (reqType == 0) {
            req.scene = SendMessageToWX.Req.WXSceneSession;//分享到好友
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到朋友圈
        }
        req.message = msg;
        boolean isSend = api.sendReq(req);
        if (!isSend) {
            Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
        }

    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    Bitmap bm = null;

    private void loadImage(String imageUrl) {
        //把图片网址转换成bitmap,微信分享要用的
        Glide.with(context).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                //由于微信分享的图片要求在32k一下,所以要转换成缩略图
                bm = createBitmapThumbnail(resource, false);
            }
        });
    }

    /**
     * 设置缩略图
     *
     * @param bitMap
     * @param needRecycle
     * @return
     */
    public static Bitmap createBitmapThumbnail(Bitmap bitMap, boolean needRecycle) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = 80;
        int newHeight = 80;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
        if (needRecycle) bitMap.recycle();
        return newBitMap;
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}