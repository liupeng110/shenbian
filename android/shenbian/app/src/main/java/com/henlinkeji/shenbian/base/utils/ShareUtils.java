package com.henlinkeji.shenbian.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.henlinkeji.shenbian.base.config.MyConfig;


/**
 * Created by hjm on 2016/2/1.
 */
public class ShareUtils {

    //共享参数默认名字
    private static final String SP_NAME = MyConfig.SP_NAME;

    //获取数据
    public static String getDataString(String key, String defaultObject, Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultObject);
    }

    public static int getDataInt(String key, int defaultObject, Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultObject);
    }

    public static boolean getDataBoolean(String key, boolean defaultObject, Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultObject);
    }

    //保存数据
    public static boolean setDataString(String key, String object, Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, object);
        return editor.commit();
    }

    public static boolean setDataInt(String key, int object, Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, object);
        return editor.commit();
    }

    public static boolean setDataBoolean(String key, boolean object, Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, object);
        return editor.commit();
    }

    //删除数据
    public static boolean delete(String key, Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (sp.contains(key)) {
            editor.remove(key);
        } else {
            ToastUtils.disPlayShort(context, "\"" + key + "\"不存在");
            MyLog.e("\"" + key + "\"不存在");
        }
        return editor.commit();
    }

    //清空数据
    public static boolean clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        return editor.commit();
    }
}
