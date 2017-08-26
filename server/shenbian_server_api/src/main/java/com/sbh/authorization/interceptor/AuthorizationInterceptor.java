package com.sbh.authorization.interceptor;


import com.sbh.authorization.annotation.Authorization;
import com.sbh.authorization.manager.TokenManager;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，对请求进行身份验证
 * @see
 * @author ScienJus
 * @date 2015/7/30.
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    //管理身份验证操作的对
    private TokenManager manager;

    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权信息的无用前�?，默认为�?
    private String httpHeaderPrefix = "";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认�?401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    public void setManager(TokenManager manager) {
        this.manager = manager;
    }

    public void setHttpHeaderName(String httpHeaderName) {
        this.httpHeaderName = httpHeaderName;
    }

    public void setHttpHeaderPrefix(String httpHeaderPrefix) {
        this.httpHeaderPrefix = httpHeaderPrefix;
    }

    public void setUnauthorizedErrorMessage(String unauthorizedErrorMessage) {
        this.unauthorizedErrorMessage = unauthorizedErrorMessage;
    }

    public void setUnauthorizedErrorCode(int unauthorizedErrorCode) {
        this.unauthorizedErrorCode = unauthorizedErrorCode;
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接�?�过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String token = request.getHeader(httpHeaderName);
        if (token != null && token.startsWith(httpHeaderPrefix) && token.length() > 0) {
            token = token.substring(httpHeaderPrefix.length());
            //验证token
            String key = manager.getKey(token);
            if (key != null) {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(REQUEST_CURRENT_KEY, key);
                return true;
            }
        }
        //如果验证token失败，并且方法注明了Authorization，返�?401错误
        if (method.getAnnotation(Authorization.class) != null   //查看方法上是否有注解
                || handlerMethod.getBeanType().getAnnotation(Authorization.class) != null) {    //查看方法�?在的Controller是否有注�?
            response.setStatus(unauthorizedErrorCode);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            writer.write(unauthorizedErrorMessage);
            writer.close();
            return false;
        }
        //为了防止以恶意操作直接在REQUEST_CURRENT_KEY写入key，将其设为null
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }
}
