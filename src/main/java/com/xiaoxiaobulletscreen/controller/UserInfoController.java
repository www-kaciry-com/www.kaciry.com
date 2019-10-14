package com.xiaoxiaobulletscreen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiaobulletscreen.entity.*;
import com.xiaoxiaobulletscreen.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
public class UserInfoController {
    private final String PREFIX = "User/";

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = {"/userhomepage"})
    public String userInfo() {
        return PREFIX + "userhomepage";
    }

    @RequestMapping(value = {"/reply"})
    public String userReply() {
        return "reply";
    }

    @PostMapping(value = "/changeInfo")
    @ResponseBody
    public User changeInfo(User user) {
        return userService.changeInfo(user);
    }

    @PostMapping(value = "modifyHeadIcon")
    @ResponseBody
    public boolean modifyHeadIcon(MultipartFile file, HttpSession session) {
        try {
            //获取文件名
            String fileName = session.getAttribute("username").toString();
            String fileSuffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
            //Linux下上传目录
            //String filePathCover = "/www/wwwroot/www.kaciry.com/upload/HeadIcon/";
            //Windows下上传目录
            String filePath = "F:/upload/HeadIcon/";
            //创建文件
            File files = new File(filePath + fileName + fileSuffix);
            //上传文件
            file.transferTo(files);
            //创建文件路径
            String userHeadIconPathName = "/files/HeadIcon/" + fileName + fileSuffix;
            //修改数据库中头像路径
            return userService.updateUserHeadIcon(userHeadIconPathName, session.getAttribute("username").toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @PostMapping("/postUpdatePwd")
    @ResponseBody
    public boolean updatePassword(String email, String password) {

        return userService.updatePassword(email, password);
    }

    @RequestMapping(value = "/selectInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public User selectInfo(HttpSession session) {
        return userService.selectInfo(session.getAttribute("username").toString());
    }

    @PostMapping(value = "/selectMyVideo")
    @ResponseBody
    public PageInfo<VideoInfo> selectMyVideo(HttpSession session, Integer pageNum, Integer pageSize) {
        List<VideoInfo> videoInfoList;
        if (pageNum == null || pageSize == null) {
            PageHelper.startPage(1, 20);
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }

        videoInfoList = userService.selectMyVideos(session.getAttribute("username").toString());
        return new PageInfo<>(videoInfoList);
    }

    @PostMapping("/postQueryCollect")
    @ResponseBody
    public PageInfo<VideoInfo> queryCollect(HttpSession session, Integer pageNum, Integer pageSize) {

        List<VideoInfo> videoInfoList;
        if (pageNum == null || pageSize == null) {
            PageHelper.startPage(1, 20);
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }

        videoInfoList = userService.queryCollect(session.getAttribute("username").toString());
        return new PageInfo<>(videoInfoList);
    }

    @PostMapping(value = "/postQueryFollows")
    @ResponseBody
    public PageInfo<UnionFansBean>  queryFollows(String username, Integer pageNum, Integer pageSize) {

//        List<FansBean> list = userService.queryFollows(username);
//        List<User> userList = new ArrayList<>();
//
//        for (FansBean fansBean : list) {
//            userList.add(userService.selectInfo(fansBean.getFollowedUser()));
//        }
////        Map<List<FansBean>, List<User>> map = new HashMap<>();
////        map.put(list,userList);
//
//        return new FollowsBean(list,userList);
        PageHelper.startPage(pageNum, pageSize);
        List<UnionFansBean> list = userService.queryFollows1(username);
        return new PageInfo<>(list);
    }

    @PostMapping(value = "/followHim")
    @ResponseBody
    public ResultBean followOthers(String username, String hisUsername) {

        return userService.followOthers(username, hisUsername);
    }

}
