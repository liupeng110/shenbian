package com.henlinkeji.shenbian.shenbian;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT > 16) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_LAYOUT_FLAGS|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
            getWindow().getDecorView().setFitsSystemWindows(true);
        }
    }

    public static void setStatusBarTranslucent(ViewGroup vg, Activity activity) {
        // 如果版本在4.4以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏高度
            int height = getStatusBarHeight(activity);
            if (height <= 0) {
                return;
            }
            // 设置距离顶部状态栏垂直距离
            vg.setPadding(0, height, 0, 0);
            // 状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 导航栏透明
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        int height = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            height = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }


}
