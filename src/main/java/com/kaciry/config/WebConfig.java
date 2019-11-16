package com.kaciry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

/**
 * @author kaciry
 * @date 2019/10/25 18:52
 * @description 项目配置类
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/indexDataInit","/playRank","/login","/rsaKey1","/rsaKey2","/static/**");
//    }

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
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(1024000000);
//        factory.setMaxRequestSize(1024000000);
//        String location = System.getProperty("user.dir") + "/data/tmp";
//        File tmpFile = new File(location);
//        if (!tmpFile.exists()) {
//            boolean mkdirs = tmpFile.mkdirs();
//        }
//        factory.setLocation(location);
//        return factory.createMultipartConfig();
//    }
}