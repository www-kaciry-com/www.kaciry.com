package com.xiaoxiaobulletscreen.controller;

import com.xiaoxiaobulletscreen.entity.ResultBean;
import com.xiaoxiaobulletscreen.entity.UserChatBean;
import com.xiaoxiaobulletscreen.service.Impl.UserChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserChatController {

    @Autowired
    UserChatServiceImpl userChatService;

    @PostMapping(value = "/privatechat")
    @ResponseBody
    public ResultBean privateChat(String senderID, String receiverID, String content) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        UserChatBean userChatBean = new UserChatBean(senderID, receiverID, content, df.format(new Date()));
        return userChatService.saveUserChatMsg(userChatBean);
    }

}
