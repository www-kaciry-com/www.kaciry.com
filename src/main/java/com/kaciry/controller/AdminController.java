package com.kaciry.controller;

import com.kaciry.entity.*;
import com.kaciry.service.Impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping(value = {"/adminLogin"}, method = RequestMethod.GET)
    public String login() {
        return "admin/adminLogin";
    }

    @PostMapping("/adminLogin")
    @ResponseBody
    public int adminLogin(String adminAccount, String password) {
        return adminServiceImpl.isLoginSuccess(adminAccount, password);
    }

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 查看用户信息
     * @date 2019/10/31 0:25
     **/
    @PostMapping("/adminSelectUserInfo")
    @ResponseBody
    public List<User> queryUserInfo(int star, int pageSize) {
        return adminServiceImpl.findUserInfo(star, pageSize);
    }

    /**
     * @param searchedUsername 用户输入的用户名（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 根据用户输入的用户名查询用户详细信息
     * @date 2019/10/31 0:34
     **/
    @PostMapping("/adminSearchUserDetailsInfo")
    @ResponseBody
    public List<User> queryCurrUserByUsername(String searchedUsername) {
        return adminServiceImpl.findCurrUserByUsername(searchedUsername);
    }

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.BannedVideo>
     * @author FLLH
     * @description 查看视频举报
     * @date 2019/10/30 23:43
     **/
    @PostMapping("/adminSelectVideoReport")
    @ResponseBody
    public List<ReportVideoDO> queryVideoReport(int star, int pageSize) {
        return adminServiceImpl.findVideoReport(star, pageSize);
    }

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.BannedUser>
     * @author FLLH
     * @description 查看评论举报
     * @date 2019/10/30 23:49
     **/
    @PostMapping("/adminSelectCommentReport")
    @ResponseBody
    public List<ReportCommentDO> queryCommentReport(int star, int pageSize) {
        return adminServiceImpl.findCommentReport(star, pageSize);
    }

    /**
     * @param reportVideoId 用户输入的举报编号（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.BannedVideo>
     * @author FLLH
     * @description 根据用户输入的举报编号查询视频举报详细信息
     * @date 2019/10/30 11:44
     **/
    @PostMapping("/adminSearchVideoReportDetailsInfo")
    @ResponseBody
    public List<ReportVideoDO> searchVideoReportDetailsInfoByReportVideoId(int reportVideoId) {
        List<ReportVideoDO> videoReportDetailsList = adminServiceImpl.searchVideoReportDetailsInfoByReportVideoId(reportVideoId);
        return videoReportDetailsList;
    }

    /**
     * @param reportCommentId 用户输入的举报编号（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.BannedUser>
     * @author FLLH
     * @description 根据用户输入的举报编号查询评论举报详细信息
     * @date 2019/10/30 11:58
     **/
    @PostMapping("/adminSearchCommentReportDetailsInfo")
    @ResponseBody
    public List<ReportCommentDO> searchCommentReportDetailsInfoByReportCommentId(int reportCommentId) {
        List<ReportCommentDO> commentReportDetailsList = adminServiceImpl.searchCommentReportDetailsInfoByReportCommentId(reportCommentId);
        return commentReportDetailsList;
    }

    /**
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 根据isVip分组计数绘制饼状图
     * @date 2019/11/11 19:34
     **/
    @PostMapping(value = "/adminSelectUserInfo_eCharts", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryUserInfoForEcharts() {
        return adminServiceImpl.findUserInfoForEcharts();
    }

    /**
     * @param
     * @return java.lang.String
     * @author FLLH
     * @description 各类视频数量统计 柱状图
     * @date 2019/12/24 14:24
     **/
    @PostMapping(value = "/adminSelectVideoInfo_eCharts", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryVideoInfoForEcharts() {
        return adminServiceImpl.findVideoInfoForEcharts();
    }

    /**
     * @param
     * @return java.lang.String
     * @author FLLH
     * @description 视频点赞 评论 播放量等功能统计  柱状图
     * @date 2019/12/24 14:33
     **/
    @PostMapping(value = "/adminSelectVideoInfoData_eCharts", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryVideoInfoDataForEcharts() {
        return adminServiceImpl.findVideoInfoDataForEcharts();
    }

    /**
     * @param
     * @return java.util.List<java.lang.String>
     * @author FLLH
     * @description 地图  今日登录用户
     * @date 2019/12/24 14:45
     **/
    @PostMapping(value = "/adminSelectUserIp_eCharts", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryUserIpInfoDataForEcharts() {
        return adminServiceImpl.findUserIpInfoDataForEcharts();
    }

    /**
     * @param videoFilename 视频Id 表（user_video）
     * @return int
     * @author FLLH
     * @description 将选中的视频封禁
     * @date 2019/11/11 19:36
     **/
    @PostMapping("/adminBanVideoById")
    @ResponseBody
    public int banVideo(String videoFilename) {
        return adminServiceImpl.resetVideoState(videoFilename);
    }

    /**
     * @param commentIdentityDocument 评论Id
     * @return int
     * @author FLLH
     * @description 封禁评论 根据评论Id
     * @date 2019/12/7 17:17
     **/
    @PostMapping("/adminBanCommentById")
    @ResponseBody
    public int banComment(int commentIdentityDocument) {
        return adminServiceImpl.resetCommentState(commentIdentityDocument);
    }

    /**
     * @param videoIdentityDocument videoId
     * @return int
     * @author FLLH
     * @description 审核视频通过 修改videoState
     * @date 2019/12/7 17:19
     **/
    @PostMapping("/passedCheck")
    @ResponseBody
    public int passedCheck(int videoIdentityDocument) {
        return adminServiceImpl.resetVideoState(videoIdentityDocument);

    }

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 查看举报功能 计算视频举报条数
     * @date 2019/12/7 17:20
     **/
    @PostMapping("/selectVideoReportNum")
    @ResponseBody
    public int queryVideoReportNum() {
        return adminServiceImpl.findVideoReportNum();
        // System.out.println(num);
    }

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 查看举报功能 计算评论举报条数
     * @date 2019/12/7 17:21
     **/
    @PostMapping("/selectCommentReportNum")
    @ResponseBody
    public int queryCommentReportNum() {
        return adminServiceImpl.findCommentReportNum();
    }

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 查看用户信息 计算总条数
     * @date 2019/12/10 15:49
     **/
    @PostMapping("/selectUserInfoNum")
    @ResponseBody
    public int queryUserInfoNum() {
        return adminServiceImpl.findUserInfoNum();
    }

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author FLLH
     * @description 视频信息功能
     * @date 2019/12/7 17:37
     **/
    @PostMapping("/adminSelectVideoInfo")
    @ResponseBody
    public List<VideoInfoDO> queryVideoInfo(int star, int pageSize) {
        return adminServiceImpl.findVideoInfo(star, pageSize);
    }

    /**
     * @param
     * @return java.util.List<java.lang.Integer>
     * @author FLLH
     * @description 查询用户 视频 音乐数量
     * @date 2019/12/24 16:52
     **/
    @PostMapping("/selectTotal")
    @ResponseBody
    public List<Integer> queryTotal() {
        return adminServiceImpl.findTotal();
    }

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 视频信息功能 计算视频条数
     * @date 2019/12/7 17:29
     **/
    @PostMapping("/selectVideoNum")
    @ResponseBody
    public int queryVideoNum() {
        return adminServiceImpl.findVideoNum();
    }

    /**
     * @param username 用户名 String 不是int 不是int 不是int
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author FLLH
     * @description 根据用户名查看用户的全部视频
     * @date 2019/12/7 17:30
     **/
    @PostMapping("/adminSearchUserVideos")
    @ResponseBody
    public List<VideoInfoDO> queryUserVideos(String username) {
        List<VideoInfoDO> userAllVideoList = adminServiceImpl.findUserVideos(username);
        return userAllVideoList;
    }

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.Music>
     * @author FLLH
     * @description 音乐信息
     * @date 2019/12/7 17:36
     **/
    @PostMapping("/adminSelectMusicInfo")
    @ResponseBody
    public List<Music> queryMusicInfo(int star, int pageSize) {
        return adminServiceImpl.findMusicInfo(star, pageSize);
    }

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 音乐信息功能 计算总条数
     * @date 2019/12/7 17:32
     **/
    @PostMapping("/selectMusicNum")
    @ResponseBody
    public int queryMusicNum() {
        return adminServiceImpl.findMusicNum();
    }

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.Music>
     * @author FLLH
     * @description 根据用户名查看用户的全部音乐
     * @date 2019/12/7 17:33
     **/
    @PostMapping("/adminSearchUserMusics")
    @ResponseBody
    public List<Music> searchUserMusics(String username) {
        List<Music> userAllMusicList = adminServiceImpl.searchUserMusics(username);
        return userAllMusicList;
    }

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author FLLH
     * @description 审核视频功能
     * @date 2019/12/7 17:34
     **/
    @PostMapping("/adminSelectUncheckVideoInfo")
    @ResponseBody
    public List<VideoInfoDO> queryUncheckVideoInfo(int star, int pageSize) {
        return adminServiceImpl.findUncheckVideoInfo(star, pageSize);
    }

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 审核视频功能计算总条数
     * @date 2019/12/7 17:35
     **/
    @PostMapping("/selectUncheckVideoNum")
    @ResponseBody
    public int queryUncheckVideoNum() {
        return adminServiceImpl.findUncheckVideoNum();
    }

}
