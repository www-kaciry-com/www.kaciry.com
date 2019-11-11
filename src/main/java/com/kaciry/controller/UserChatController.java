package com.kaciry.controller;

import com.kaciry.entity.ResultBean;
import com.kaciry.entity.UnionFansBean;
import com.kaciry.entity.UserChatBean;
import com.kaciry.service.Impl.UserChatServiceImpl;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.utils.GetAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/25 17:29
 * @description 有关用户聊天的Controller
 */
@Controller
public class UserChatController {

    @Autowired
    private UserChatServiceImpl userChatService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.UnionFansBean>
     * @author kaciry
     * @description 获取username的所有关注
     * @date 2019/10/30 13:50
     **/
    @PostMapping(value = "/getChatList")
    @ResponseBody
    public List<UnionFansBean> getChatList(String username) {
        return userService.queryFollows1(username);
    }

    /**
     * @param token   登陆成功后分发给客户端的一个加密token
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
    public ResultBean privateChat(String token, String senderIdentityDocument, String receiverIdentityDocument, String content) {
        if (GetAuthorization.isAuthorization(senderIdentityDocument, token)) {
            //设置日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            UserChatBean userChatBean = new UserChatBean(senderIdentityDocument, receiverIdentityDocument, content, simpleDateFormat.format(new Date()));
            return userChatService.saveUserChatMsg(userChatBean);
        } else {
            return new ResultBean<>("请勿非法操作！");
        }

    }

    /**
     * @param token   登陆成功后分发给客户端的一个加密token
     * @param senderIdentityDocument   发送方的ID(用户名)
     * @param receiverIdentityDocument 接收方的ID(用户名)
     * @return java.util.List<com.kaciry.entity.UserChatBean>
     * @author kaciry
     * @description 获取私信记录
     * @date 2019/10/25 17:32
     **/
    @PostMapping(value = "/getPrivateMsg")
    @ResponseBody
    public List<UserChatBean> getPrivateMessage(String token, String senderIdentityDocument, String receiverIdentityDocument) {
        if (GetAuthorization.isAuthorization(senderIdentityDocument, token)) {
            return userChatService.getPrivateMsg(senderIdentityDocument, receiverIdentityDocument);
        } else {
            return null;
        }

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
    public List<UserChatBean> getNewMessage(String senderIdentityDocument, String receiverIdentityDocument, int userChatIdentityDocument) {
        return userChatService.getNewMsg(senderIdentityDocument, receiverIdentityDocument, userChatIdentityDocument);
    }

}
