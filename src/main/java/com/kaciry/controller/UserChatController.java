package com.kaciry.controller;

import com.kaciry.entity.ResultBean;
import com.kaciry.entity.UnionFansDO;
import com.kaciry.entity.UserChatDO;
import com.kaciry.service.Impl.UserChatServiceImpl;
import com.kaciry.service.Impl.UserServiceImpl;
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
    public List<UnionFansDO> getChatList(String username) {
        return userService.queryFollows(username);
    }

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
        UserChatDO userChatDO = new UserChatDO(senderIdentityDocument, receiverIdentityDocument, content, simpleDateFormat.format(new Date()));
        return userChatService.saveUserChatMsg(userChatDO);
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
    public List<UserChatDO> getPrivateMessage(String senderIdentityDocument, String receiverIdentityDocument) {
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
    public List<UserChatDO> getNewMessage(String senderIdentityDocument, String receiverIdentityDocument, int userChatIdentityDocument) {
        return userChatService.getNewMsg(senderIdentityDocument, receiverIdentityDocument, userChatIdentityDocument);
    }

}
