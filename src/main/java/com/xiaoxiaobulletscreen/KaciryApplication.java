package com.xiaoxiaobulletscreen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//批量扫描所有的mapper接口
@MapperScan("com.xiaoxiaobulletscreen")
public class KaciryApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(KaciryApplication.class, args);
    }
    // 继承SpringBootServletInitializer 实现configure 方便打war 外部服务器部署。
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(KaciryApplication.class);
    }
}
