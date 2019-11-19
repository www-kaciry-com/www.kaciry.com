package com.kaciry.service.Impl;

import com.kaciry.entity.*;
import com.kaciry.mapper.UserMapper;
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
    public UserMapper userMapper;

    @Override
    public User login(String username, String userPassword) {
        return userMapper.login(username, userPassword);
    }
    public User login(String username) {
        return userMapper.login(username, null);
    }


    @Override
    public ResultBean updateUserPassword(String username, String originPassword, String password) {
        User user = userMapper.login(username, null);
        if (user.getUserPassword().equals(originPassword)){
            if (userMapper.updateUserPassword(username, password)) {
                return new ResultBean<>("修改成功！");
            }else {
                return new ResultBean<>("出现未知错误，稍后重试！");
            }
        }else {
            return new ResultBean<>("原密码错误，请重试！");
        }

    }

    @Override
    public String register(User user) {
        if (userMapper.login(user.getUsername(), user.getUserPassword()) == null) {
            userMapper.setOneUser(user);
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public User changeInfo(User user) {
        if (userMapper.updateUserInfo(user)) {
            return userMapper.login(user.getUsername(), user.getUserPassword());
        } else {
            return null;
        }
    }

    @Override
    public User selectUserInfoByUsername(String username) {
        return userMapper.selectInfo(username);
    }

    @Override
    public boolean uploadVideo(VideoInfo videoInfo) {

        return userMapper.insertVideo(videoInfo);
    }

    @Override
    public List<VideoInfo> selectVideosByUsername(String username) {
        return userMapper.selectVideos(username);
    }

    @Override
    public boolean updatePassword(String email, String password) {
        return userMapper.updatePassword(email, password);
    }

    @Override
    public boolean updateUserHeadIcon(String userHeadIcon, String username) {
        return userMapper.updateUserHeadIcon(userHeadIcon, username);
    }

    @Override
    public List<VideoInfo> selectCollectionsByUsername(String username) {
        List<VideoInfo> videoInfoList = new ArrayList<>();
        List<String> videoName = userMapper.queryCollect(username, 1);
        for (String s : videoName) {
            //System.out.println(s);
            VideoInfo videoInfo = userMapper.queryVideosByVideoFileName(s);
            videoInfoList.add(videoInfo);
        }
        return videoInfoList;
    }

    @Override
    public VideoInfo selectVideoInfoByVideoFilename(String videoFilename) {

        return userMapper.queryVideosByVideoFileName(videoFilename);
    }

    @Override
    public ResultBean followOthers(String username, String hisUsername) {
        boolean flag;
        ResultBean resultBean = new ResultBean();
        if (userMapper.queryFansInfo(username, hisUsername) != null) {
            flag = userMapper.cancelFollow(username, hisUsername);
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
            flag = userMapper.addFansInfo(fansBean);
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
        return userMapper.queryFansInfo(username, hisUsername);
    }

    @Override
    public List<FansBean> queryFollows(String username) {
        return userMapper.queryFollows(username);
    }

    public List<UnionFansBean> queryFollows1(String username) {
        return userMapper.queryMyFollows(username);
    }

    @Override
    public ResultBean reportComment(ReportCommentBean reportCommentBean) {
        if (userMapper.queryReportComment(reportCommentBean) == null) {
            if (userMapper.addReportComment(reportCommentBean)) {
                return new ResultBean<>("举报成功，感谢您的支持！");
            } else
                return new ResultBean<>("举报失败，请稍后重试！");
        } else return new ResultBean<>("您已举报过该评论，请勿重复操作！");
    }

    @Override
    public CommentBean queryCommentByIdentityDocument(long commentIdentityDocument) {
        return userMapper.queryCommentByIdentityDocument(commentIdentityDocument);
    }
}
