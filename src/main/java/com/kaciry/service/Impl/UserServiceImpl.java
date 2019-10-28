package com.kaciry.service.Impl;

import com.kaciry.dao.UserDao;
import com.kaciry.entity.*;
import com.kaciry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 搜索数据Service实现类
 */
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
    public User selectUserInfoByUsername(String username) {
        return userDao.selectInfo(username);
    }

    @Override
    public boolean uploadVideo(VideoInfo videoInfo) {

        return userDao.insertVideo(videoInfo);
    }

    @Override
    public List<VideoInfo> selectVideosByUsername(String username) {
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
    public List<VideoInfo> selectCollectionsByUsername(String username) {
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
    public VideoInfo selectVideoInfoByVideoFilename(String videoFilename) {

        return userDao.queryVideosByVideoFileName(videoFilename);
    }

    @Override
    public ResultBean followOthers(String username, String hisUsername) {
        boolean flag;
        ResultBean resultBean = new ResultBean();
        if (userDao.queryFansInfo(username, hisUsername) != null) {
            flag = userDao.cancelFollow(username, hisUsername);
            if (flag) {
                resultBean.setCode(502);
                resultBean.setMsg("关注");
            } else {
                // TODO: 2019/10/11 打印日志
                System.out.println("数据库操作发生未知错误！");
                resultBean.setCode(500);
                resultBean.setMsg("服务器未知错误");
            }

        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            FansBean fansBean = new FansBean(username, hisUsername, df.format(new Date()));
            flag = userDao.addFansInfo(fansBean);
            if (flag) {
                resultBean.setCode(200);
                resultBean.setMsg("取消关注");
            } else {
                // TODO: 2019/10/11 打印日志
                System.out.println("数据库操作发生未知错误！");
                resultBean.setCode(500);
                resultBean.setMsg("服务器未知错误");
            }

        }

        return resultBean;
    }

    @Override
    public FansBean selectFollowsState(String username, String hisUsername) {
        return userDao.queryFansInfo(username, hisUsername);
    }

    @Override
    public List<FansBean> queryFollows(String username) {
        return userDao.queryFollows(username);
    }

    public List<UnionFansBean> queryFollows1(String username) {
        return userDao.queryMyFollows(username);
    }

    @Override
    public ResultBean reportComment(ReportCommentBean reportCommentBean) {
        if (userDao.queryReportComment(reportCommentBean) == null) {
            if (userDao.addReportComment(reportCommentBean)) {
                return new ResultBean<>("举报成功，感谢您的支持！");
            } else
                return new ResultBean<>("举报失败，请稍后重试！");
        } else return new ResultBean<>("您已举报过该评论，请勿重复操作！");
    }

    @Override
    public CommentBean queryCommentByIdentityDocument(int commentIdentityDocument) {
        return userDao.queryCommentByIdentityDocument(commentIdentityDocument);
    }
}
