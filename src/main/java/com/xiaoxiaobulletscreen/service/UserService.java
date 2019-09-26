package com.xiaoxiaobulletscreen.service;

import com.xiaoxiaobulletscreen.entity.User;
import com.xiaoxiaobulletscreen.entity.VideoInfo;

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
    boolean updateUserHeadIcon(String userHeadIcon , String username);

    List<VideoInfo> queryCollect(String username);

}
