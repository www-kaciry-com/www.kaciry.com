package com.kaciry.service;

import com.kaciry.entity.ResultBean;
import com.kaciry.entity.UserChatDO;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 用户聊天私信Service接口
 */
public interface UserChatService {
    /**
     * @param userChatDO UserChatBean实体
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 保存用户聊天记录
     * @date 2019/10/26 13:16
     **/
    ResultBean saveUserChatMsg(UserChatDO userChatDO);

    /**
     * @param senderIdentityDocument   发送方用户名
     * @param receiverIdentityDocument 接收方用户名
     * @return java.util.List<com.kaciry.entity.UserChatBean>
     * @author kaciry
     * @description 获取聊天记录
     * @date 2019/10/26 13:18
     **/
    List<UserChatDO> getPrivateMsg(String senderIdentityDocument, String receiverIdentityDocument);

    /**
     * @param senderIdentityDocument   发送方用户名
     * @param receiverIdentityDocument 接收方用户名
     * @param userChatIdentityDocument 当前聊天索引
     * @return java.util.List<com.kaciry.entity.UserChatBean>
     * @author kaciry
     * @description 获取新私信
     * @date 2019/10/26 13:18
     **/
    List<UserChatDO> getNewMsg(String senderIdentityDocument, String receiverIdentityDocument, int userChatIdentityDocument);

}
