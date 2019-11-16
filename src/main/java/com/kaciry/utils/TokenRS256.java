package com.kaciry.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class TokenRS256 {

    /**
     * 这个方法采取的是RS256 非对称加密算法
     */

    public static String TokenTest(String uid, RSAKey rsaJWK, int exp) {
        //获取生成token
        Map<String, Object> map = new HashMap<>();
        exp = exp * (1000 * 60 * 60);
        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", uid);
        //生成时间
        map.put("sta", System.currentTimeMillis());
        //过期时间
        map.put("exp", System.currentTimeMillis() + exp);
        try {
            return TokenUtils.creatTokenRS256(map, rsaJWK);
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    //处理解析的业务逻辑
    static String ValidToken(String token, RSAKey rsaJWK) {
        //解析token
        try {
            if (token != null) {
                Map<String, Object> validMap = TokenUtils.validRS256(token, rsaJWK);
                int i = (int) validMap.get("Result");
                if (i == 0) {
                    System.out.println("token解析成功");
                    JSONObject jsonObject = (JSONObject) validMap.get("data");
                    System.out.println("uid是：" + jsonObject.get("uid") + " sta是：" + jsonObject.get("sta") + " exp是：" + jsonObject.get("exp"));
                    return jsonObject.get("uid").toString();
                } else if (i == 2) {
                    System.out.println("token已经过期");
                    return null;
                }
            }
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }
}
