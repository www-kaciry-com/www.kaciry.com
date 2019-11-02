package com.kaciry.service;

import com.kaciry.entity.PromoteVideosBean;
import com.kaciry.entity.ResultBean;
import com.kaciry.entity.VideoInfo;

import java.sql.Timestamp;
import java.util.List;

public interface PromoteVideosService {
    /**
     * @return java.lang.String
     * @author kaciry
     * @description 查询推广视频需要等待的时间
     * @date 2019/11/1 15:00
     **/
    String queryWaitTime();

    /**
     * @param promoteVideosBean PromoteVideosBean实体，详情见PromoteVideosBean类
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 添加一条视频推荐信息
     * @date 2019/11/1 22:45
     **/
    ResultBean addPromoteVideo(PromoteVideosBean promoteVideosBean);

    /**
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
     * @author kaciry
     * @description 查询所有未到期的推广视频
     * @date 2019/11/2 13:45
     **/
    List<PromoteVideosBean> analysisDataIsOvertime();

    /**
     * @author kaciry
     * @description  失效视频
     * @date  2019/11/2 14:54
     * @param videoFilename 视频文件名
     * @return boolean
    **/
    boolean setPromoteVideoTimeOver(String videoFilename);
    /**
     * @author kaciry
     * @description  获取正在生效的推广视频
     * @date  2019/11/2 14:55
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
    **/
    List<VideoInfo> getPromoteVideos4Carousel();


    List<VideoInfo> getPromoteVideos4List();

    boolean setPromoteVideoDuration(String videoFilename, Timestamp timestamp);
}
