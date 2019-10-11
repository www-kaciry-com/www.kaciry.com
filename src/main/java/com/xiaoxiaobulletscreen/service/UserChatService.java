package com.xiaoxiaobulletscreen.service;

import com.xiaoxiaobulletscreen.entity.ResultBean;
import com.xiaoxiaobulletscreen.entity.UserChatBean;

import java.util.List;

public interface UserChatService {

    ResultBean saveUserChatMsg(UserChatBean userChatBean);

    List<UserChatBean> getPrivateMsg(String senderID, String receiverID);

    List<UserChatBean> getNewMsg(String senderID,int userChatID);

}
