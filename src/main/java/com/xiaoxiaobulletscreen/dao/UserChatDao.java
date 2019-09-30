package com.xiaoxiaobulletscreen.dao;

import com.xiaoxiaobulletscreen.entity.UserChatBean;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface UserChatDao {
    @Insert("insert into user_chat (u_username,f_username,content,date) values (#{senderID},#{receiverID},#{content},#{date})")
    void addUserChatMsg(UserChatBean userChatBean);
}
