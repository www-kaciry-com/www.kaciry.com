package com.kaciry.service;

import com.kaciry.entity.*;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/26 13:57
 * @description 有关视频信息的Service接口
 */
public interface VideoService {

    /**
     * @param videoFilename 视频文件名
     * @return void
     * @author kaciry
     * @description 根据视频文件名增加视频的播放量
     * @date 2019/11/4 14:28
     **/
    void addVideoPlayNumByVideoFilename(String videoFilename);

    /**
     * @param commentBean Comment实体，包含信息见实体类
     * @return com.kaciry.entity.Comment
     * @author kaciry
     * @description 添加一条新评论
     * @date 2019/10/26 13:57
     **/
    CommentBean addComment(CommentBean commentBean);

    /**
     * @param videoFileName 视频文件名
     * @return java.util.List<com.kaciry.entity.Comment>
     * @author kaciry
     * @description 根据视频文件名查询视频评论
     * @date 2019/10/26 13:57
     **/
    List<CommentBean> selectVideoCommentsByVideoFilename(String videoFileName);

    /**
     * @param videoFileName 视频文件名
     * @param username      用户名
     * @return com.kaciry.entity.VideoPage
     * @author kaciry
     * @description 初始化用户视频信息
     * @date 2019/10/26 13:57
     **/
    VideoPage initVideoInfo(String videoFileName, String username);

    /**
     * @param ops Ops实体，包含信息见实体类
     * @return boolean
     * @author kaciry
     * @description 用户点赞
     * @date 2019/10/26 13:57
     **/
    boolean opsStar(Ops ops);

    /**
     * @param ops Ops实体，包含信息见实体类
     * @return boolean
     * @author kaciry
     * @description 用户收藏
     * @date 2019/10/26 13:57
     **/
    boolean opsCollect(Ops ops);

    /**
     * @param ops Ops实体，包含信息见实体类
     * @return boolean
     * @author kaciry
     * @description 用户分享
     * @date 2019/10/26 13:57
     **/
    boolean opsShare(Ops ops);

    /**
     * @param ops Ops实体，包含信息见实体类
     * @author kaciry
     * @description 删除用户对视频的操作，以节约数据库空间
     * @date 2019/10/26 13:57
     **/
    void deleteOpsData(Ops ops);

    /**
     * @param reportVideoBean ReportVideoBean实体，包含信息见实体类
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 添加一条视频举报信息
     * @date 2019/10/26 13:57
     **/
    ResultBean addOneReportVideoData(ReportVideoBean reportVideoBean);

    /**
     * @param videoFilename 视频文件名
     * @param videoBarrages 弹幕数量
     * @return void
     * @author kaciry
     * @description
     * @date 2019/11/11 18:03
     **/
    void addVideoBarrages(String videoFilename, int videoBarrages);

    /**
     * @param videoFilename 视频文件名
     * @return int
     * @author kaciry
     * @description 根据用户名和视频文件名删除视频文件
     * @date 2019/11/13 12:56
     **/
    int removeVideoByVideoFilename(String videoFilename);
}
