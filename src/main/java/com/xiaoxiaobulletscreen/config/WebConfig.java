package com.xiaoxiaobulletscreen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/indexDataInit","/login","/loginPage","/static/**");
//    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 映射 /static 的请求到 classpath 下的 static 目录
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        //Linux 下目录配置
//        registry.addResourceHandler("/files/**").addResourceLocations("file:/www/wwwroot/www.kaciry.com/upload/");
        //Windows下目录配置
        registry.addResourceHandler("/files/**").addResourceLocations("file:F://upload/");


    }

}