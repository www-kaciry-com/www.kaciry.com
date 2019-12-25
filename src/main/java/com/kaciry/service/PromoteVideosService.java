package com.kaciry.service;

import com.kaciry.entity.PromoteVideosDO;
import com.kaciry.entity.ResultBean;
import com.kaciry.entity.VideoInfo;

import java.sql.Timestamp;
import java.util.List;

public interface PromoteVideosService {

    List<VideoInfo> selectNormalVideos(String username);

    /**
     * @return java.lang.String
     * @author kaciry
     * @description 查询推广视频需要等待的时间
     * @date 2019/11/1 15:00
     **/
    String queryWaitTime(int option);

    /**
     * @param promoteVideosDO PromoteVideosBean实体，详情见PromoteVideosBean类
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 添加一条视频推荐信息
     * @date 2019/11/1 22:45
     **/
    ResultBean addPromoteVideo(PromoteVideosDO promoteVideosDO);

    /**
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
     * @author kaciry
     * @description 查询所有未到期的推广视频
     * @date 2019/11/2 13:45
     **/
    List<PromoteVideosDO> analysisDataIsOvertime();

    /**
     * @param videoFilename 视频文件名
     * @return boolean
     * @author kaciry
     * @description 失效视频
     * @date 2019/11/2 14:54
     **/
    boolean setPromoteVideoTimeOver(String videoFilename);

    /**
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
     * @author kaciry
     * @description 获取正在生效的推广视频
     * @date 2019/11/2 14:55
     **/
    List<VideoInfo> getPromoteVideos4Carousel();

    List<VideoInfo> getPromoteVideos4List();

    Timestamp queryLastTime(int option);

}
