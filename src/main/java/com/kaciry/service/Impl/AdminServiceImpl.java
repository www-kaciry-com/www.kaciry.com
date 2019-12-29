package com.kaciry.service.Impl;

import com.alibaba.fastjson.JSON;
import com.kaciry.dao.AdminDao;
import com.kaciry.entity.*;
import com.kaciry.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;


    @Override
    public int isLoginSuccess(String adminAccount, String password) {
        return  adminDao.selectAdminInfo(adminAccount, password);

    }


    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 查看用户信息
     * @date 2019/10/31 0:25
     **/
    @Override
    public List<User> findUserInfo(Integer star, Integer pageSize) {
        return adminDao.selectUserInfo(star, pageSize);
    }

    @Override
    public List<VideoInfoDO> findVideoInfo(Integer star, Integer pageSize) {
        return adminDao.selectVideoInfo(star, pageSize);
    }

    @Override
    public List<Integer> findTotal() {
        List<Integer> totalList = new ArrayList<>();
        int userTotal = adminDao.selectUserTotal();
        int memberTotal = adminDao.selectMemberTotal();
        int videoTotal = adminDao.selectVideoTotal();
        int musicTotal = adminDao.selectMusicTotal();
        totalList.add(userTotal);
        totalList.add(memberTotal);
        totalList.add(videoTotal);
        totalList.add(musicTotal);
        return totalList;
    }

    /**
     * @param searchedUsername 用户输入的用户名（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 根据用户输入的用户名查询用户详细信息
     * @date 2019/10/31 0:34
     **/
    @Override
    public List<User> findCurrUserByUsername(String searchedUsername) {
        return adminDao.selectCurrUserByUsername(searchedUsername);
    }

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.BannedVideo>
     * @author FLLH
     * @description 查看视频举报
     * @date 2019/10/30 23:43
     **/
    @Override
    public List<ReportVideoDO> findVideoReport(Integer star, Integer pageSize) {
        return adminDao.selectVideoReport(star, pageSize);

    }

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.BannedUser>
     * @author FLLH
     * @description 查看评论举报
     * @date 2019/10/30 23:49
     **/
    @Override
    public List<ReportCommentDO> findCommentReport(Integer star, Integer pageSize) {
        return adminDao.selectCommentReport(star, pageSize);
    }

    /**
     * @param reportVideoId 用户输入的举报编号（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.BannedVideo>
     * @author FLLH
     * @description 根据用户输入的举报编号查询视频举报详细信息
     * @date 2019/10/30 11:44
     **/
    @Override
    public List<ReportVideoDO> searchVideoReportDetailsInfoByReportVideoId(int reportVideoId) {
        List<ReportVideoDO> videoReportDetailsList = adminDao.searchVideoReportDetailsInfoByReportVideoId(reportVideoId);
        return videoReportDetailsList;
    }

    /**
     * @param reportCommentId 用户输入的举报编号（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.BannedUser>
     * @author FLLH
     * @description 根据用户输入的举报编号查询评论举报详细信息
     * @date 2019/10/30 11:58
     **/
    @Override
    public List<ReportCommentDO> searchCommentReportDetailsInfoByReportCommentId(int reportCommentId) {
        List<ReportCommentDO> commentReportDetailsList = adminDao.searchCommentReportDetailsInfoByReportCommentId(reportCommentId);
        return commentReportDetailsList;
    }

    /**
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 根据isVip分组计数绘制饼状图
     * @date 2019/11/11 19:34
     **/
    @Override
    public String findUserInfoForEcharts() {
        List<User> userInfoList = adminDao.selectUserInfoForEcharts();
        List list = new ArrayList();
        EChartData re;
        for (User user : userInfoList) {
            re = new EChartData(user.getIsVip(), user.getNum());
            // System.out.println(re);
            list.add(re);
        }
        return JSON.toJSONString(list);
    }

    /**
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 各类视频种类数量 柱状图
     * @date 2019/11/28 14:08
     **/
    @Override
    public String findVideoInfoForEcharts() {
        List<VideoInfoDO> res = adminDao.selectVideoInfoForEcharts();
        List list = new ArrayList();
        //Map<String,String> map;
        EChartData re;
        for (VideoInfoDO videoInDO : res) {
            re = new EChartData(videoInDO.getVideoType(), videoInDO.getVideoIdentityDocument());
            list.add(re);
        }
        String str = JSON.toJSONString(list);

        return str;

    }

    /**
     * @param
     * @return java.lang.String
     * @author FLLH
     * @description 视频点赞 评论 播放量等功能统计  柱状图
     * @date 2019/12/24 14:54
     **/
    @Override
    public String findVideoInfoDataForEcharts() {
        List<VideoInfoDO> res = adminDao.selectVideoInfoDataForEcharts();
        List list1 = new ArrayList();
        //Map<String,String> map;
        VideoInfoDO videoData;
        for (VideoInfoDO videoInfo : res) {
            List list = new ArrayList();
            list.add(videoInfo.getVideoType());
            list.add(videoInfo.getVideoStars());
            list.add(videoInfo.getVideoCoins());
            list.add(videoInfo.getVideoConnections());
            list.add(videoInfo.getVideoShares());
            list.add(videoInfo.getVideoPlayNum());
            list.add(videoInfo.getVideoBarrages());

            list1.add(list);
            list = null;
        }
        String str = JSON.toJSONString(list1);

        return str;

    }

    /**
     * @param
     * @return java.util.List<java.lang.String>
     * @author FLLH
     * @description 地图  今日登录用户
     * @date 2019/12/24 14:55
     **/
    @Override
    public String findUserIpInfoDataForEcharts() {
        List<UserIp> res = adminDao.selectUserIpInfoDataForEcharts();
        List<String> userIpList = new ArrayList<>();

        for (UserIp re : res) {
            String userIpAddress = re.getUserAddress();
            int num = re.getNum();
            String userAddress = "";

            int indexOf = userIpAddress.indexOf("省");
            int indexOf2 = userIpAddress.indexOf("自");
            if (indexOf > 0) {
                userAddress = userIpAddress.substring(0, indexOf);
                for (int j = 0; j < num; j++) {
                    userIpList.add(userAddress);
                }
            } else if (indexOf < 0 && indexOf2 > 0) {
                userAddress = userIpAddress.substring(0, indexOf2);
                for (int j = 0; j < num; j++) {
                    userIpList.add(userAddress);
                }
            } else {
                userAddress = userIpAddress.substring(0, 2);
                for (int j = 0; j < num; j++) {
                    userIpList.add(userAddress);
                }
            }
        }
        String str = JSON.toJSONString(userIpList);

        return str;
    }

    /**
     * @param videoFilename 视频Id 表（user_video）
     * @return int
     * @author FLLH
     * @description 将选中的视频封禁
     * @date 2019/11/11 19:36
     **/
    @Override
    public int resetVideoState(String videoFilename) {
        return adminDao.updateVideoState(videoFilename);
    }

    @Override
    public int resetCommentState(int commentIdentityDocument) {
        return adminDao.updateCommentState(commentIdentityDocument);
    }

    @Override
    public int resetVideoState(int videoIdentityDocument) {
        return adminDao.updateVideoStateToPass(videoIdentityDocument);
    }

    @Override
    public int findVideoReportNum() {
        return adminDao.selectVideoReportNum();
        //System.out.println(num);
    }

    @Override
    public int findCommentReportNum() {
        return adminDao.selectCommentReportNum();
    }

    @Override
    public int findUserInfoNum() {
        return adminDao.selectUserInfoNum();
    }

    @Override
    public int findVideoNum() {
        return adminDao.selectVideoNum();
    }

    @Override
    public List<VideoInfoDO> findUserVideos(String username) {
        List<VideoInfoDO> userAllVideoList = adminDao.selectUserVideos(username);
        return userAllVideoList;
    }

    @Override
    public List<Music> findMusicInfo(Integer star, Integer pageSize) {
        return adminDao.selectMusicInfo(star, pageSize);
    }

    @Override
    public int findMusicNum() {
        return adminDao.selectMusicNum();
    }

    @Override
    public List<Music> searchUserMusics(String username) {
        List<Music> userAllMusicList = adminDao.searchUserMusics(username);
        return userAllMusicList;
    }

    @Override
    public List<VideoInfoDO> findUncheckVideoInfo(Integer star, Integer pageSize) {
        return adminDao.selectUncheckVideoInfo(star, pageSize);
    }

    @Override
    public int findUncheckVideoNum() {
        return adminDao.selectUncheckVideoNum();
    }

//    @Override
//    public List<VideoInfo> searchUserVideos(int username) {
//        List<VideoInfo> userAllVideoList = adminDao.searchUserVideos(username);
//        return userAllVideoList;
//    }

}
