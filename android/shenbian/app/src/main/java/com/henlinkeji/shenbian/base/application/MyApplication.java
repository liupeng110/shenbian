package com.henlinkeji.shenbian.base.application;

import android.app.Activity;
import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static okhttp3.internal.Internal.instance;

/**
 * Created by hjm on 2016/9/4.
 */
public class MyApplication extends Application {
    private List<Activity> activitys = new LinkedList<>();
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //网络连接配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(50000L, TimeUnit.MILLISECONDS).readTimeout(50000L, TimeUnit.MILLISECONDS).build();
        OkHttpUtils.initClient(okHttpClient);
    }

    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }
    }

    public void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
