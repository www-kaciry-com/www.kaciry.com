package com.kaciry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;

/**
 * @author kaciry
 * @date 2019/10/25 18:52
 * @description 项目配置类
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index", "/", "/indexDataInit", "/playRank", "/login", "/register", "/initVideo",
                        "/selectVideoComment", "/getVideoUser", "/rsaKey1", "/rsaKey2", "/static/**", "/search", "/video",
                        "/initPromoteVideos4Carousel", "/initPromoteVideos4List", "/files/**", "/postResetPwd",
                        "/resetPassword", "/countVideoNum", "/signup", "/getIPAddress", "/searchMsg");
    }

    @Bean
    public HttpMessageConverter responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射 /static 的请求到 classpath 下的 static 目录
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        //Linux 下目录配置
        //registry.addResourceHandler("/files/**").addResourceLocations("file:/www/wwwroot/www.kaciry.com/upload/");
        //Windows下目录配置
        registry.addResourceHandler("/files/**").addResourceLocations("file:F://upload/");

    }
}