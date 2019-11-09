package com.kaciry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kaciry
 * @date 2019/11/9 22:17
 * @description 页面跳转Controller
 */
@Controller
public class WebpageController {
    @RequestMapping(value = {"/login"})
    public String login() {
        return "public/signin";
    }

    @RequestMapping(value = "/register")
    public String registerGet() {
        return "public/signup";
    }

    @RequestMapping(value = {"/index"})
    public String index() {
        return "index/index";
    }

    @GetMapping(value = "/")
    public String loginIndex() {
        return "redirect:/index";
    }

    @GetMapping("/resetPassword")
    public String resetPassword() {
        return "user/resetPassword";
    }

    @GetMapping("/search")
    public String search() {
        return "public/search";
    }

    @RequestMapping(value = {"/homePage"})
    public String userInfo() {
        return "user/homePage";
    }

    @RequestMapping(value = {"/reply"})
    public String userReply() {
        return "user/message";
    }

    @RequestMapping(value = {"/video"})
    public String videoPlay() {
        return "public/video";
    }

    @RequestMapping(value = {"/contribute"})
    public String contribute() {
        return "user/uploadResource";
    }

    @RequestMapping(value = {"/promoteVideo"})
    public String promoteVideo() {
        return "user/promoteVideo";
    }

}
