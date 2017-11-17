package com.henlinkeji.shenbian.base.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.rong.imkit.RongContext;

/**
 * Created by Miracler on 17/3/21.
 */

public class Utils {

    public static boolean isMobileNumber(String mobile) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static String hideMobileNumber(String mobile) {
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }


    public static String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public static void setBackgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        context.getWindow().setAttributes(lp);
    }

    public static String mToKm(double m) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(m / 1000) + "km";
    }

    @SuppressLint("StringFormatInvalid")
    public static String formatTime(long timeMillis) {
        if(timeMillis == 0L) {
            return "";
        } else {
            String result = null;
            int targetDay = (int)(timeMillis / 86400000L);
            int nowDay = (int)(System.currentTimeMillis() / 86400000L);
            if(targetDay == nowDay) {
                result = fromatDate(timeMillis, "HH:mm");
            } else if(targetDay + 1 == nowDay) {
                Context context = RongContext.getInstance().getBaseContext();
                String formatString = context.getResources().getString(io.rong.imkit.R.string.rc_yesterday_format);
                result = String.format(formatString, new Object[]{fromatDate(timeMillis, "HH:mm")});
            } else {
                result = fromatDate(timeMillis, "yyyy-MM-dd HH:mm");
            }

            return result;
        }
    }

    private static String fromatDate(long timeMillis, String fromat) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        return sdf.format(new Date(timeMillis));
    }

}
