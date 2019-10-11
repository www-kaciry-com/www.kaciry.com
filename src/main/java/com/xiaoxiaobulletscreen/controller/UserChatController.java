package com.xiaoxiaobulletscreen.controller;

import com.xiaoxiaobulletscreen.entity.ResultBean;
import com.xiaoxiaobulletscreen.entity.UserChatBean;
import com.xiaoxiaobulletscreen.service.Impl.UserChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserChatController {

    @Autowired
    UserChatServiceImpl userChatService;

    //发送私信
    @PostMapping(value = "/privateChat")
    @ResponseBody
    public ResultBean privateChat(String senderID, String receiverID, String content) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        UserChatBean userChatBean = new UserChatBean(senderID, receiverID, content, df.format(new Date()));
        return userChatService.saveUserChatMsg(userChatBean);
    }

    //获取私信记录
    @PostMapping(value = "/getPrivateMsg")
    @ResponseBody
    public List<UserChatBean> String (String senderID, String receiverID){

        return userChatService.getPrivateMsg(senderID,receiverID);
    }

    //获取新的私信
    @PostMapping(value = "/getNewMsg")
    @ResponseBody
    public List<UserChatBean> getNewMsg(String senderID , int userChatID){
        System.out.println("senderID: " + senderID);
        System.out.println("userChatID: " + userChatID);
        List<UserChatBean> res = userChatService.getNewMsg(senderID ,userChatID);
        System.out.println(res);
        return res;
    }

}
