package com.kaciry.utils;

import com.kaciry.entity.PromoteVideosBean;
import com.kaciry.service.Impl.PromoteVideosServiceImpl;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/11/2 13:29
 * @description TestQuartz
 */
public class AutoAnalysisDataOvertime extends QuartzJobBean {

    @Autowired
    private PromoteVideosServiceImpl promoteVideosService;

    /**
     * @param jobExecutionContext 上下文
     * @author kaciry
     * @description 每*分钟执行一次推广视频是否失效方法，失效的更改数据项promoteType为0，打印日志
     * @date 2019/11/2 13:31
     **/
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        List<PromoteVideosBean> list = promoteVideosService.analysisDataIsOvertime();
        for (PromoteVideosBean promoteVideosBean : list) {
            if (TimeUtils.getTimeDifference(promoteVideosBean.getSurplusDuration(), new Timestamp(System.currentTimeMillis())).equals("false")) {
                boolean flag = promoteVideosService.setPromoteVideoTimeOver(promoteVideosBean.getVideoFilename());
                System.out.println("执行了一次推广视频失效--> " + flag + ": " + promoteVideosBean.toString());
                // TODO: 2019/11/2 打印日志
            }
        }
        // TODO: 2019/11/2 减少时间
//        for (int i = 0; i <= list.size(); i++) {
//            Timestamp timestamp = TimeDifference.analysisTime(list.get(i).getSurplusDuration());
//            boolean flag = promoteVideosService.setPromoteVideoDuration(list.get(i).getVideoFilename(),timestamp);
//            System.out.println("执行了一次视频时间减少-->" + flag);
//        }
    }
}