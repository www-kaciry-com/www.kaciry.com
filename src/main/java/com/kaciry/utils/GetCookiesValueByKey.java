package com.kaciry.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author kaciry
 * @date 2019/11/8 17:12
 * @description 通过Cookie的key获取value
 */
public class GetCookiesValueByKey {
    public static String getValue(HttpServletRequest request){
        String res = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Token")) {
                    res = TokenUtils.checkToken(cookie.getValue());
                    break;
                }
            }
        }
        return res;
    }

    public static String getToken(HttpServletRequest request){
        String res = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Token")) {
                    res = cookie.getValue();
                    break;
                }
            }
        }
        return res;

    }
}
