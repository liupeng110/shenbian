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
    public final static String SP_LON = "sp_longitude";
    public final static String SP_LAT = "sp_latitude";
    public final static String SP_CITY = "sp_city";
    public static final String IMAGE_JPG_SUFFIX = ".jpg";

//    public final static String HTTP = "http://60.205.220.80:8081/";
    public final static String HTTP = "http://192.168.1.8:8080/shenbianhao/";

    //首页上半部分
    public final static String HEAD_TOP = HTTP + "v1/index/query.htm";
    //首页下半部分
    public final static String HEAD_BOTTOM = HTTP + "v1/index/service/query.htm";
    //发送验证码
    public final static String SEND_CODE = HTTP + "v1/login/send.htm";
    //登录
    public final static String LOGIN = HTTP + "v1/login/verifyLogin.htm";
    //获取七牛token
    public final static String GET_UPLOAD_TOKEN = HTTP + "v1/service/materialUploadToken.htm";
    //添加服务
    public final static String ADD_ARTICLE_SERVICE = HTTP + "v1/service/addService.htm";
    //服务详情
    public final static String SERVICE_DETAIL = HTTP + "/v1/service/details.htm";
    //编辑商户信息
    public final static String EDIT_INFO = HTTP + "/v1/user/update.htm";
    //查询商户信息
    public final static String QUERY_INFO = HTTP + "v1/user/query.htm";
    //添加购物车
    public final static String ADD_CART = HTTP + "v1/user/cart/add.htm";
    //查询购物车
    public final static String QUERY_CART = HTTP + "v1/user/cart/query.htm";
    //更新购物车
    public final static String UPDATE_CART = HTTP + "v1/user/cart/update.htm";
    //发现
    public final static String DISCOVER = HTTP + "v1/service/find/query.htm";
    //下单
    public final static String COMMIT_ORDER = HTTP + "v1/order/add.htm";
    //获取预支付字符串
    public final static String GET_PREPAY = HTTP + "v1/wxpay/unifiedOrder.htm";
//    收藏和取消收藏接口 v1/collect/addOrRemove.htm   参数:serviceId 收藏文章或者服务的ID    collectionStatus:收藏状态 0：取消 1：收藏 token:系统token
//关注接口 v1/user/concern.htm 参数 interestedUserId：被关注人ID token：系统token       concernStatus：关注状态  0：取消   1：关注
}
