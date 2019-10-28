package com.kaciry.controller;

import com.kaciry.entity.ResultBean;
import com.kaciry.entity.UserChatBean;
import com.kaciry.service.Impl.UserChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kaciry
 * @date 2019/10/25 17:29
 * @description 有关用户聊天的Controller
 */
@Controller
public class UserChatController {

    @Autowired
    UserChatServiceImpl userChatService;

    /**
     * @param senderIdentityDocument   发送方的ID(用户名)
     * @param receiverIdentityDocument 接收方的ID(用户名)
     * @param content                  发送的内容
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 用户发送私信
     * @date 2019/10/25 17:29
     **/
    @PostMapping(value = "/privateChat")
    @ResponseBody
    public ResultBean privateChat(String senderIdentityDocument, String receiverIdentityDocument, String content) {
        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserChatBean userChatBean = new UserChatBean(senderIdentityDocument, receiverIdentityDocument, content, simpleDateFormat.format(new Date()));
        return userChatService.saveUserChatMsg(userChatBean);
    }

    /**
     * @param senderIdentityDocument   发送方的ID(用户名)
     * @param receiverIdentityDocument 接收方的ID(用户名)
     * @return java.util.List<com.kaciry.entity.UserChatBean>
     * @author kaciry
     * @description 获取私信记录
     * @date 2019/10/25 17:32
     **/
    @PostMapping(value = "/getPrivateMsg")
    @ResponseBody
    public List<UserChatBean> getPrivateMessage(String senderIdentityDocument, String receiverIdentityDocument) {
        return userChatService.getPrivateMsg(senderIdentityDocument, receiverIdentityDocument);
    }

    /**
     * @param senderIdentityDocument   发送方的ID(用户名)
     * @param userChatIdentityDocument 聊天记录的ID
     * @return java.util.List<com.kaciry.entity.UserChatBean>
     * @author kaciry
     * @description 获取新的私信
     * @date 2019/10/25 17:35
     **/
    @PostMapping(value = "/getNewMsg")
    @ResponseBody
    public List<UserChatBean> getNewMessage(String senderIdentityDocument, int userChatIdentityDocument) {
        return userChatService.getNewMsg(senderIdentityDocument, userChatIdentityDocument);
    }

}
