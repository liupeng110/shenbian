package com.henlinkeji.shenbian.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ShareModel implements Serializable {
    private String title;
    private String text;
    private String url;
    private String imageUrl;

    private Bitmap bm;
    private Bitmap thumbData;

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Bitmap getThumbData() {
        return thumbData;
    }

    public void setThumbData(Bitmap thumbData) {
        this.thumbData = thumbData;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}