package com.henlinkeji.shenbian.base.config;

/**
 * Created by hjm on 16/9/6.
 */

public class MyConfig {

    //=====================Log配置========================
    public final static String TAG = "lgq===";

    //==============sharepreference配置===================
    public final static String SP_NAME = "sp_name";
    public final static String SP_TOKEN = "sp_token";
    public static final String IMAGE_JPG_SUFFIX = ".jpg";

    public final static String HTTP = "http://60.205.220.80:8081/";

    public final static String HEAD_TOP = HTTP + "v1/index/query.htm";
    public final static String HEAD_BOTTOM = HTTP + "v1/service/query.htm";
    public final static String SEND_CODE = HTTP + "v1/login/send.htm";
    public final static String LOGIN = HTTP + "v1/login/verifyLogin.htm";


}
