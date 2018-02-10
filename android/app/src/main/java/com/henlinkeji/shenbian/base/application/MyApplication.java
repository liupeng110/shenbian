package com.henlinkeji.shenbian.base.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.CrashHandler;
import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.bean.Address;
import com.henlinkeji.shenbian.bean.Sub;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rong.imkit.RongIM;
import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    private List<Activity> activitys = new LinkedList<>();
    private static MyApplication instance;

    private ArrayList<Sub> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<Sub>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Sub>>> options3Items = new ArrayList<>();

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        //网络连接配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(50000L, TimeUnit.MILLISECONDS).readTimeout(50000L, TimeUnit.MILLISECONDS).build();
        OkHttpUtils.initClient(okHttpClient);

        Fresco.initialize(this);

        RongIM.init(this);

        Gson gson = new Gson();

        InputStream is = getResources().openRawResource(R.raw.addresslist);
        String addresslist = null;
        try {
            addresslist = Utils.readTextFromSDcard(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Address address = gson.fromJson(addresslist, Address.class);

        for (int i = 0; i < address.getAdmin_division().size(); i++) {
            ArrayList<Sub> list2 = new ArrayList<>();
            ArrayList<ArrayList<Sub>> list3 = new ArrayList<>();
            for (int j = 0; j < address.getAdmin_division().get(i).getSub().size(); j++) {
                Sub sub = new Sub();
                sub.setId(address.getAdmin_division().get(i).getSub().get(j).getId());
                sub.setName(address.getAdmin_division().get(i).getSub().get(j).getName());
                list2.add(sub);
                ArrayList<Sub> list4 = new ArrayList<>();
                if (address.getAdmin_division().get(i).getSub().get(j).getSub() != null) {
                    for (int k = 0; k < address.getAdmin_division().get(i).getSub().get(j).getSub().size(); k++) {
                        Sub sub1 = new Sub();
                        sub1.setId(address.getAdmin_division().get(i).getSub().get(j).getSub().get(k).getId());
                        sub1.setName(address.getAdmin_division().get(i).getSub().get(j).getSub().get(k).getName());
                        list4.add(sub1);
                    }
                } else {
                    Sub sub2 = new Sub();
                    sub2.setName("");
                    sub2.setId(0);
                    list4.add(sub2);
                }
                list3.add(list4);
            }
            Sub sub = new Sub();
            sub.setId(address.getAdmin_division().get(i).getId());
            sub.setName(address.getAdmin_division().get(i).getName());
            options1Items.add(sub);
            options2Items.add(list2);
            options3Items.add(list3);
        }

        setOptions1Items(options1Items);
        setOptions2Items(options2Items);
        setOptions3Items(options3Items);
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
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

    public ArrayList<Sub> getOptions1Items() {
        return options1Items;
    }

    public void setOptions1Items(ArrayList<Sub> options1Items) {
        this.options1Items = options1Items;
    }

    public ArrayList<ArrayList<Sub>> getOptions2Items() {
        return options2Items;
    }

    public void setOptions2Items(ArrayList<ArrayList<Sub>> options2Items) {
        this.options2Items = options2Items;
    }

    public ArrayList<ArrayList<ArrayList<Sub>>> getOptions3Items() {
        return options3Items;
    }

    public void setOptions3Items(ArrayList<ArrayList<ArrayList<Sub>>> options3Items) {
        this.options3Items = options3Items;
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

}
