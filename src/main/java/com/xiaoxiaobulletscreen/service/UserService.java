package com.xiaoxiaobulletscreen.service;

import com.xiaoxiaobulletscreen.entity.*;

import java.util.List;

public interface UserService {

    User login(String username, String password);

    String register(User user);

    User changeInfo(User user);

    User selectInfo(String username);

    boolean uploadVideo(VideoInfo videoInfo);

    List<VideoInfo> selectMyVideos(String username);

    boolean updatePassword(String email, String password);

    //修改用户头像
    boolean updateUserHeadIcon(String userHeadIcon, String username);

    List<VideoInfo> queryCollect(String username);

    VideoInfo queryVideoInfo(String videoFilename);

    //关注他人
    ResultBean followOthers(String username, String hisUsername);

    //查询关注状态
    FansBean queryFollowsState(String username, String hisUsername);

    List<FansBean> queryFollows(String username);

    ResultBean reportComment(ReportCommentBean reportCommentBean);

    Comment queryCommentByID(int commentID);
}
