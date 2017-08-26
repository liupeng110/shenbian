package com.sbh.authorization.resolvers;

import com.sbh.authorization.annotation.CurrentUser;
import com.sbh.authorization.interceptor.AuthorizationInterceptor;
import com.sbh.authorization.repository.UserModelRepository;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 澧炲姞鏂规硶娉ㄥ叆锛屽皢鍚湁CurrentUser娉ㄨВ鐨勬柟娉曞弬鏁版敞鍏ュ綋鍓嶇櫥褰曠敤鎴�
<<<<<<< HEAD
 * @see com.scienjus.authorization.annotation.CurrentUser
=======
 * @see
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
 * @author ScienJus
 * @date 2015/7/31.
 */
public class CurrentUserMethodArgumentResolver<T> implements HandlerMethodArgumentResolver {

<<<<<<< HEAD
    //鐢ㄦ埛妯″�?�鐨勭被鍚�
    private Class<T> userModelClass;

    //閫氳繃Key鑾峰緱鐢ㄦ埛妯�?��?�鐨勫疄鐜扮�?
=======
    //
    private Class<T> userModelClass;

    //
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
    private UserModelRepository<T> userModelRepository;

    public void setUserModelClass(String className) {
        try {
            this.userModelClass = (Class<T>) Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setUserModelClass(Class<T> clazz) {
        this.userModelClass = clazz;
    }

    public void setUserModelRepository(UserModelRepository<T> userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    public boolean supportsParameter(MethodParameter parameter) {
<<<<<<< HEAD
        //濡傛灉鍙傛暟绫诲瀷鏄疷ser骞朵笖鏈塁urrentUser娉ㄨВ鍒欐敮鎸�?
=======
        //濡傛灉鍙傛暟绫诲瀷鏄疷ser骞朵笖鏈塁urrentUser娉ㄨВ鍒欐敮鎸�?
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
        return parameter.getParameterType().isAssignableFrom(userModelClass) &&
                parameter.hasParameterAnnotation(CurrentUser.class);
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //鍙栧嚭閴存潈鏃跺瓨鍏ョ殑鐧诲綍鐢ㄦ埛Id
        Object object = webRequest.getAttribute(AuthorizationInterceptor.REQUEST_CURRENT_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object != null) {
            String key = String.valueOf(object);
<<<<<<< HEAD
            //浠庢暟鎹簱涓煡璇㈠苟杩斿�?
=======
            //浠庢暟鎹簱涓煡璇㈠苟杩斿�?
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
            Object userModel = userModelRepository.getCurrentUser(key);
            if (userModel != null) {
                return userModel;
            }
            //鏈塳ey浣嗘槸寰椾笉鍒扮敤鎴凤紝鎶涘嚭寮傚父
            throw new MissingServletRequestPartException(AuthorizationInterceptor.REQUEST_CURRENT_KEY);
        }
<<<<<<< HEAD
        //娌℃湁key灏辩洿鎺ヨ繑鍥�?�ull
=======
        //娌℃湁key灏辩洿鎺ヨ繑鍥�?�ull
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
        return null;
    }
}
