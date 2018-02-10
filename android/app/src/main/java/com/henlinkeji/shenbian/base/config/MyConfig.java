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
    public final static String SP_ID= "sp_id";
    public final static String SP_LON = "sp_longitude";
    public final static String SP_LAT = "sp_latitude";
    public final static String SP_CITY = "sp_city";
    public final static String SP_NEED_DO = "need_do";
    public static final String IMAGE_JPG_SUFFIX = ".jpg";

    public final static String HTTP = "https://www.ufushenghuo.com/";
//    public final static String HTTP = "http://192.168.1.10:8080/shenbianhao/";

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
    //预支付字符串
    public final static String GET_PREPAY = HTTP + "v1/wxpay/unifiedOrder.htm";
    //订单列表
    public final static String ORDER_LIST = HTTP + "v1/order/query.htm";
    //添加收藏
    public final static String COLLECT = HTTP + "v1/collect/addOrRemove.htm";
    //个人信息
    public final static String BASIC_INFO = HTTP + "v1/my/query.htm";
    //分类数据
    public final static String CLASS_DATA = HTTP + "v1/classification/service/query.htm";
    //分类
    public final static String GET_CLASSFY = HTTP + "v1/all/classification/query.htm";
    //个人主页服务列表
    public final static String GET_SERVICE = HTTP + "v1/my/all/query.htm";
    //广告
    public final static String GET_BANNER = HTTP + "v1/adv/query.htm";
    //关注
    public final static String ATTENTION = HTTP + "v1/user/concern.htm";
    //帮助
    public final static String HELP = HTTP + "v1/help/query.htm";
    //安全与隐私
    public final static String SECURITY = HTTP + "v1/security/query.htm";
}
