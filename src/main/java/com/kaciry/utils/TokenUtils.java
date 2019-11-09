package com.kaciry.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author kaciry
 * @date 2019/11/8 14:17
 * @description token的校验
 */
public class TokenUtils {

    private static Map<String,String> MAP_TOKENS = new HashMap<>();
    private static final int VALID_TIME = 60*60*2; // token有效期(秒)
    private static final String TOKEN_ERROR = "F"; // 非法
    private static final String TOKEN_OVERDUE = "G"; // 过期
    private static final String TOKEN_FAILURE = "S"; // 失效

    /**
     * 生成token,该token长度不一致,如需一致,可自行MD5或者其它方式加密一下
     * 该方式的token只存在磁盘上,如果项目是分布式,最好用redis存储
     * @param str: 该字符串可自定义,在校验token时要保持一致
     * @return
     */
    public static String getToken(String str) {
        String token = TokenEncryptUtils.encoded(getCurrentTime()+","+str);
        MAP_TOKENS.put(str, token);
        return token;
    }

    /**
     * 校验token的有效性
     * @param token
     * @return
     */
    public static String checkToken(String token) {
        if (token == null) {
            return TOKEN_ERROR;
        }
        try{
            String[] tArr = TokenEncryptUtils.decoded(token).split(",");
            if (tArr.length != 2) {
                return TOKEN_ERROR;
            }
//            // token生成时间戳
//            int tokenTime = Integer.parseInt(tArr[0]);
//            // 当前时间戳
//            int currentTime = getCurrentTime();
//            if (currentTime-tokenTime < VALID_TIME) {
//                String tokenStr = tArr[1];
////                String mToken = MAP_TOKENS.get(tokenStr);
////                if (mToken == null) {
////                    return TOKEN_OVERDUE;
////                } else if(!mToken.equals(token)) {
////                    return TOKEN_FAILURE;
////                }
//
//            } else {
//                return TOKEN_OVERDUE;
//            }
            return tArr[1];
        }catch (Exception e) {
            e.printStackTrace();
        }
        return TOKEN_ERROR;
    }

    /**获取当前时间戳（10位整数）*/
    private static int getCurrentTime() {
        return (int)(System.currentTimeMillis()/1000);
    }

    /**
     * 移除过期的token
     */
    public static void removeInvalidToken() {
        int currentTime = getCurrentTime();
        for (Entry<String,String> entry : MAP_TOKENS.entrySet()) {
            String[] tArr = TokenEncryptUtils.decoded(entry.getValue()).split(",");
            int tokenTime = Integer.parseInt(tArr[0]);
            if(currentTime-tokenTime > VALID_TIME){
                MAP_TOKENS.remove(entry.getKey());
            }
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String str = "1";
        // 获取token
        String token = TokenUtils.getToken(str);
        System.out.println("token Result: " + token);

        // 校验token
        String checkToken = TokenUtils.checkToken(token);
        System.out.println("checkToken Result: " + checkToken);
        if(str.equals(checkToken)) {
            System.out.println("==>token verification succeeded!");
        }

    }

}