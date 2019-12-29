package com.kaciry.service;

import com.kaciry.entity.*;

import java.util.List;

public interface AdminService {

    int isLoginSuccess(String adminAccount, String password);



    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 查看用户信息
     * @date 2019/10/31 0:25
     **/
    List<User> findUserInfo(Integer star, Integer pageSize);

    /**
     * @param searchedUsername 用户输入的用户名（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 根据用户输入的用户名查询用户详细信息
     * @date 2019/10/31 0:34
     **/
    List<User> findCurrUserByUsername(String searchedUsername);

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.BannedVideo>
     * @author FLLH
     * @description 查看视频举报
     * @date 2019/10/30 23:43
     **/
    List<ReportVideoDO> findVideoReport(Integer star, Integer pageSize);

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.BannedUser>
     * @author FLLH
     * @description 查看评论举报
     * @date 2019/10/30 23:49
     **/
    List<ReportCommentDO> findCommentReport(Integer star, Integer pageSize);

    /**
     * @param reportVideoId 用户输入的举报编号（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.BannedVideo>
     * @author FLLH
     * @description 根据用户输入的举报编号查询视频举报详细信息
     * @date 2019/10/30 11:44
     **/
    List<ReportVideoDO> searchVideoReportDetailsInfoByReportVideoId(int reportVideoId);

    /**
     * @param reportCommentId 用户输入的举报编号（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.BannedUser>
     * @author FLLH
     * @description 根据用户输入的举报编号查询评论举报详细信息
     * @date 2019/10/30 11:58
     **/
    List<ReportCommentDO> searchCommentReportDetailsInfoByReportCommentId(int reportCommentId);

    /**
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 根据isVip分组计数绘制饼状图
     * @date 2019/11/11 19:34
     **/
    String findUserInfoForEcharts();

    /**
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 各类视频种类数量 柱状图
     * @date 2019/11/11 19:34
     **/
    String findVideoInfoForEcharts();

    String findVideoInfoDataForEcharts();

   String findUserIpInfoDataForEcharts();

    /**
     * @param videoFilename 视频Id 表（user_video）
     * @return int
     * @author FLLH
     * @description 将选中的视频封禁
     * @date 2019/11/11 19:36
     **/
    int resetVideoState(String videoFilename);

    int resetCommentState(int commentIdentityDocument);

    int resetVideoState(int videoIdentityDocument);

    int findVideoReportNum();

    int findCommentReportNum();

    int findUserInfoNum();

    List<VideoInfoDO> findVideoInfo(Integer star, Integer pageSize);

    List<Integer> findTotal();

    int findVideoNum();

    List<VideoInfoDO> findUserVideos(String username);

    List<Music> findMusicInfo(Integer star, Integer pageSize);

    int findMusicNum();

    List<Music> searchUserMusics(String username);

    List<VideoInfoDO> findUncheckVideoInfo(Integer star, Integer pageSize);

    int findUncheckVideoNum();


}
