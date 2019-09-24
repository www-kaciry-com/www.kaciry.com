package com.xiaoxiaobulletscreen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiaobulletscreen.entity.User;
import com.xiaoxiaobulletscreen.entity.VideoInfo;
import com.xiaoxiaobulletscreen.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public User changeInfo(User user){
        System.out.println(user);
        return userService.changeInfo(user);
    }

    @PostMapping("/postUpdatePwd")
    @ResponseBody
    public boolean updatePassword (String email,String password){

        return userService.updatePassword(email,password);
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
    public PageInfo<VideoInfo> queryCollect(HttpSession session, Integer pageNum, Integer pageSize){

        List<VideoInfo> videoInfoList;
        if (pageNum == null || pageSize == null) {
            PageHelper.startPage(1, 20);
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }

        videoInfoList = userService.queryCollect(session.getAttribute("username").toString());
        return new PageInfo<>(videoInfoList);
    }

}
