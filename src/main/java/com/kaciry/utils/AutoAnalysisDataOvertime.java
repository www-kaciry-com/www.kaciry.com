package com.kaciry.utils;

import com.kaciry.entity.PromoteVideosDO;
import com.kaciry.service.Impl.PromoteVideosServiceImpl;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/11/2 13:29
 * @description 定时任务Quartz
 */
public class AutoAnalysisDataOvertime extends QuartzJobBean {

    @Autowired
    private PromoteVideosServiceImpl promoteVideosService;

    /**
     * @param jobExecutionContext 上下文
     * @author kaciry
     * @description 每5分钟执行一次推广视频是否失效方法，失效的更改数据项promoteType为0，打印日志
     * @date 2019/11/2 13:31
     **/
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        List<PromoteVideosDO> list = promoteVideosService.analysisDataIsOvertime();
        for (PromoteVideosDO promoteVideosDO : list) {
            if (TimeUtils.getTimeDifference(promoteVideosDO.getSurplusDuration(), new Timestamp(System.currentTimeMillis())).equals("false")) {
                boolean flag = promoteVideosService.setPromoteVideoTimeOver(promoteVideosDO.getVideoFilename());
                System.err.println("执行了一次推广视频失效--> " + flag + ": " + promoteVideosDO.toString());
                // TODO: 2019/11/2 打印日志
            }
        }
    }

}