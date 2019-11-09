package com.kaciry.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author kaciry
 * @date 2019/11/8 17:36
 * @description 是否授权的账户
 */
@Component
public class GetAuthorization {

    private static RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        GetAuthorization.redisTemplate = redisTemplate;
    }

    public static boolean isAuthorization(String token) {
        String redisUsername;
        //待验证账户名
        String username = TokenUtils.checkToken(token);
        System.out.println("isAuthorization -->" + username);
        //redis中的账户名
        Object flag = redisTemplate.opsForValue().get(username + "_token");
        if (flag != null) {
            redisUsername = (String) flag;
            return redisUsername.equals(token);
        }else {
            return false;
        }

    }

}
