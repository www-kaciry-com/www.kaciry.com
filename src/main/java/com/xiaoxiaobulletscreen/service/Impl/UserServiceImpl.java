package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.dao.UserDao;
import com.xiaoxiaobulletscreen.entity.User;
import com.xiaoxiaobulletscreen.entity.VideoInfo;
import com.xiaoxiaobulletscreen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;

    @Override
    public User login(String username, String userPassword) {
        return userDao.login(username, userPassword);
    }

    @Override
    public String register(User user) {
        if (userDao.login(user.getUsername(), user.getUserPassword()) == null) {
            userDao.setOneUser(user);
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public User changeInfo(User user) {
        if (userDao.updateUserInfo(user)) {
            return userDao.login(user.getUsername(), user.getUserPassword());
        } else {
            return null;
        }
    }

    @Override
    public User selectInfo(String username) {
        return userDao.selectInfo(username);
    }

    @Override
    public boolean uploadVideo(VideoInfo videoInfo) {

        return userDao.insertVideo(videoInfo);
    }

    @Override
    public List<VideoInfo> selectMyVideos(String username) {
        return userDao.selectVideos(username);
    }

    @Override
    public boolean updatePassword(String email, String password) {
        return userDao.updatePassword(email, password);
    }

    @Override
    public boolean updateUserHeadIcon(String userHeadIcon, String username) {
        return userDao.updateUserHeadIcon(userHeadIcon,username);
    }

    @Override
    public List<VideoInfo> queryCollect(String username) {
        List<VideoInfo> videoInfoList = new ArrayList() ;
        List<String> videoName = userDao.queryCollect(username,1);
        for (String s : videoName) {
            System.out.println(s);
            VideoInfo videoInfo = userDao.queryVideosByVideoFileName(s);
            videoInfoList.add(videoInfo);
        }
        return videoInfoList;
    }
}
