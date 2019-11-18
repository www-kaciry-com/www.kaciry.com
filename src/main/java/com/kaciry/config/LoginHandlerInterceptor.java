package com.kaciry.config;

import com.kaciry.utils.GetAuthorization;
import com.kaciry.utils.GetCookiesValueByKey;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kaciry
 * @date 2019/11/9 19:56
 * @description 登陆拦截器
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = GetCookiesValueByKey.getValue(request, "Token");
        String username = GetCookiesValueByKey.getValue(request, "username");
        if (username.equals("") || token.equals("") || !GetAuthorization.isAuthorization(username, token)) {
            //未登录,返回登录页面
            System.out.println("执行了一次拦截-->");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        } else {
            //放行
            System.out.println("放行了一次请求-->");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
