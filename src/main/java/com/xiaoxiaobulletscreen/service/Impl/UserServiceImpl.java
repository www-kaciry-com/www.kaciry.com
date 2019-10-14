package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.dao.UserDao;
import com.xiaoxiaobulletscreen.entity.*;
import com.xiaoxiaobulletscreen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        return userDao.updateUserHeadIcon(userHeadIcon, username);
    }

    @Override
    public List<VideoInfo> queryCollect(String username) {
        List<VideoInfo> videoInfoList = new ArrayList();
        List<String> videoName = userDao.queryCollect(username, 1);
        for (String s : videoName) {
            System.out.println(s);
            VideoInfo videoInfo = userDao.queryVideosByVideoFileName(s);
            videoInfoList.add(videoInfo);
        }
        return videoInfoList;
    }

    @Override
    public VideoInfo queryVideoInfo(String videoFilename) {

        return userDao.queryVideosByVideoFileName(videoFilename);
    }

    @Override
    public ResultBean followOthers(String username, String hisUsername) {
        boolean flag;
        ResultBean resultBean = new ResultBean();
        if (userDao.queryFansInfo(username, hisUsername) != null) {
            flag = userDao.cancelFollow(username, hisUsername);
            if (flag){
                resultBean.setCode(502);
                resultBean.setMsg("关注");
            }else {
                // TODO: 2019/10/11 打印日志
                System.out.println("数据库操作发生未知错误！");
                resultBean.setCode(500);
                resultBean.setMsg("服务器未知错误");
            }

        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            FansBean fansBean = new FansBean(username, hisUsername, df.format(new Date()));
            flag = userDao.addFansInfo(fansBean);
            if (flag){
                resultBean.setCode(200);
                resultBean.setMsg("取消关注");
            }else {
                // TODO: 2019/10/11 打印日志
                System.out.println("数据库操作发生未知错误！");
                resultBean.setCode(500);
                resultBean.setMsg("服务器未知错误");
            }

        }

        return resultBean;
    }

    @Override
    public FansBean queryFollowsState(String username, String hisUsername) {
        return userDao.queryFansInfo(username,hisUsername);
    }

    @Override
    public List<FansBean> queryFollows(String username) {
        return userDao.queryFollows(username);
    }

    public List<UnionFansBean> queryFollows1(String username) {
        return userDao.queryMyFollows(username);
    }
}
