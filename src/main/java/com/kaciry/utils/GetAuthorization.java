package com.kaciry.utils;

import com.nimbusds.jose.jwk.RSAKey;
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

    public static boolean isAuthorization(String username, String token) {
        return TokenRS256.ValidToken(token, (RSAKey) redisTemplate.opsForValue().get(username + "_key")) != null;
    }

    public static String getUsername(String username, String token) {
        return TokenRS256.ValidToken(token, (RSAKey) redisTemplate.opsForValue().get(username + "_key"));
    }
}
