package com.kaciry.scheduledTask;

import com.kaciry.service.Impl.IndexDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author kaciry
 * @date 2020/1/1 09:52
 * @description 定时失效IP数据
 */
@Component
@Configuration
@EnableScheduling
public class InvalidIPDataTask {

    @Autowired
    private IndexDataServiceImpl indexDataService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void invalidIPData() {
        if (!indexDataService.invalidIPData()) {
            // TODO: 2020/1/1 日志
            System.out.println(false);
        }
    }
}
