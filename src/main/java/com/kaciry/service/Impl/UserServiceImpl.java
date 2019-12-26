package com.kaciry.service.Impl;

import com.kaciry.entity.*;
import com.kaciry.dao.UserDao;
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
        return userDao.selectLogin(username, userPassword);
    }

    public User login(String username) {
        return userDao.selectLogin(username, null);
    }

    @Override
    public ResultBean updateUserPassword(String username, String originPassword, String password) {
        User user = userDao.selectLogin(username, null);
        if (user.getUserPassword().equals(originPassword)) {
            if (userDao.updatePassword(username, password)) {
                return new ResultBean<>("修改成功！");
            } else {
                return new ResultBean<>("出现未知错误，稍后重试！");
            }
        } else {
            return new ResultBean<>("原密码错误，请重试！");
        }

    }

    @Override
    public boolean register(User user) {
        if (userDao.selectLogin(user.getUsername(), user.getUserPassword()) == null) {
            userDao.insertUserAccount(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User changeUserInfo(User user) {
        if (userDao.updateUserInfo(user)) {
            return userDao.selectLogin(user.getUsername(), user.getUserPassword());
        } else {
            return null;
        }
    }

    @Override
    public User selectUserInfoByUsername(String username) {
        return userDao.selectInfo(username);
    }

    @Override
    public boolean uploadVideo(VideoInfoDO videoInfoDO) {

        return userDao.insertVideo(videoInfoDO);
    }

    @Override
    public List<VideoInfoDO> queryVideosByUsername(String username) {
        return userDao.selectVideos(username);
    }

    @Override
    public boolean updatePassword(String email, String password) {
        return userDao.updatePasswordByEmail(email, password);
    }

    @Override
    public boolean updateUserHeadIcon(String userHeadIcon, String username) {
        return userDao.updateUserHeadIcon(userHeadIcon, username);
    }

    @Override
    public List<VideoInfoDO> queryCollectionsByUsername(String username) {
        List<VideoInfoDO> videoInfoDOList = new ArrayList<>();
        List<String> videoName = userDao.selectCollections(username, 1);
        for (String item : videoName) {
            VideoInfoDO videoInfoDO = userDao.selectVideosByVideoFilename(item);
            videoInfoDOList.add(videoInfoDO);
        }
        return videoInfoDOList;
    }

    @Override
    public VideoInfoDO selectVideoInfoByVideoFilename(String videoFilename) {

        return userDao.selectVideosByVideoFilename(videoFilename);
    }

    @Override
    public ResultBean followOthers(String username, String hisUsername) {
        boolean flag;
        ResultBean resultBean = new ResultBean();
        if (userDao.selectFansInfo(username, hisUsername) != null) {
            flag = userDao.deleteFansInfo(username, hisUsername);
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
            FansDO fansDO = new FansDO(username, hisUsername, df.format(new Date()));
            flag = userDao.insertFansInfo(fansDO);
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
    public FansDO selectFollowsState(String username, String hisUsername) {
        return userDao.selectFansInfo(username, hisUsername);
    }

    @Override
    public List<UnionFansDO> queryFollows(String username) {
        return userDao.selectMyFollows(username);
    }

    @Override
    public ResultBean addReportCommentData(ReportCommentDO reportCommentDO) {
        if (userDao.selectReportCommentData(reportCommentDO) == null) {
            if (userDao.insertReportCommentData(reportCommentDO)) {
                return new ResultBean<>("举报成功，感谢您的支持！");
            } else
                return new ResultBean<>("举报失败，请稍后重试！");
        } else return new ResultBean<>("您已举报过该评论，请勿重复操作！");
    }

    @Override
    public CommentDO queryCommentByIdentityDocument(long commentIdentityDocument) {
        return userDao.selectCommentByIdentityDocument(commentIdentityDocument);
    }
}
