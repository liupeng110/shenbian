package com.henlinkeji.shenbian.base.utils;

import android.util.Log;

import com.henlinkeji.shenbian.BuildConfig;
import com.henlinkeji.shenbian.base.config.MyConfig;


/**
 * Created by hjm on 2016/1/18.
 */
public class MyLog {

    public static void i(String flag, String content) {
        if (BuildConfig.IS_LOG) {
            Log.i(MyConfig.TAG + flag, content + "");
        }
    }

    public static void i(String content) {
        if (BuildConfig.IS_LOG) {
            Log.i(MyConfig.TAG, content + "");
        }
    }

    public static void e(String flag, String content) {
        if (BuildConfig.IS_LOG) {
            Log.e(MyConfig.TAG + flag, content + "");
        }
    }

    public static void e(String content) {
        if (BuildConfig.IS_LOG) {
            Log.e(MyConfig.TAG, content + "");
        }
    }

    public static void d(String flag, String content) {
        if (BuildConfig.IS_LOG) {
            Log.d(MyConfig.TAG + flag, content + "");
        }
    }

    public static void d(String content) {
        if (BuildConfig.IS_LOG) {
            Log.d(MyConfig.TAG, content + "");
        }
    }

    public static void w(String flag, String content) {
        if (BuildConfig.IS_LOG) {
            Log.w(MyConfig.TAG + flag, content + "");
        }
    }

    public static void w(String content) {
        if (BuildConfig.IS_LOG) {
            Log.w(MyConfig.TAG, content + "");
        }
    }
}
