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
    public final static String SP_IMTOKEN = "sp_imtoken";
    public static final String IMAGE_JPG_SUFFIX = ".jpg";

    public final static String HTTP = "http://60.205.220.80:8081/";
//    public final static String HTTP = "http://60.205.220.80:8081/";
//    public final static String HTTP = "http://192.168.1.3:8080/shenbianhao/";

    public final static String HEAD_TOP = HTTP + "v1/index/query.htm";
    public final static String HEAD_BOTTOM = HTTP + "v1/index/service/query.htm";
    public final static String SEND_CODE = HTTP + "v1/login/send.htm";
    public final static String LOGIN = HTTP + "v1/login/verifyLogin.htm";
    public final static String GET_UPLOAD_TOKEN = HTTP + "v1/service/materialUploadToken.htm";
    public final static String ADD_ARTICLE_SERVICE = HTTP + "v1/service/addService.htm";
    public final static String SERVICE_DETAIL = HTTP + "/v1/service/details.htm";
    public final static String EDIT_INFO = HTTP + "/v1/user/update.htm";
    public final static String QUERY_INFO = HTTP + "v1/user/query.htm";
    public final static String ADD_CART = HTTP + "v1/user/cart/add.htm";
    public final static String QUERY_CART = HTTP + "v1/user/cart/query.htm";
    public final static String UPDATE_CART = HTTP + "v1/user/cart/update.htm";
//    收藏和取消收藏接口 v1/collect/addOrRemove.htm   参数:serviceId 收藏文章或者服务的ID    collectionStatus:收藏状态 0：取消 1：收藏 token:系统token
//关注接口 v1/user/concern.htm 参数 interestedUserId：被关注人ID token：系统token       concernStatus：关注状态  0：取消   1：关注
}
