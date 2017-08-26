package com.sbh.authorization.manager;

<<<<<<< HEAD
=======
import com.sbh.authorization.model.TokenModel;

>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
/**
 * 对Token进行管理的接�?
 * @author ScienJus
 * @date 2015/7/31.
 */
public interface TokenManager {

    /**
     * 通过key删除关联关系
     * @param key
     */
    void delRelationshipByKey(String key);

    /**
     * 通过token删除关联关系
     * @param token
     */
    void delRelationshipByToken(String token);

    /**
     * 创建关联关系
     * @param key
     * @param token
     */
    void createRelationship(String key, String token);

    /**
     * 通过token获得对应的key
     * @param token
     * @return
     */
    String getKey(String token);
<<<<<<< HEAD
=======

    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    public TokenModel createToken(long userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken(String authentication);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(long userId);
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
}
