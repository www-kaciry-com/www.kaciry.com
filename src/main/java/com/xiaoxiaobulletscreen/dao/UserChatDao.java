package com.xiaoxiaobulletscreen.dao;

import com.xiaoxiaobulletscreen.entity.UserChatBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserChatDao {
    @Insert("insert into user_private_chat (senderID,receiverID,content,date) values (#{senderID},#{receiverID},#{content},#{date})")
    void addUserChatMsg(UserChatBean userChatBean);

    @Select("select * from user_private_chat where senderID = #{senderID} and receiverID = #{receiverID}")
    List<UserChatBean> queryChatMsg(@Param("senderID")String senderID, @Param("receiverID")String receiverID);

    @Select("select * from user_private_chat where senderID = #{senderID} and userChatID > #{userChatID}")
    List<UserChatBean> getNewMsg(@Param("senderID") String senderID , @Param("userChatID")int userChatID);
}
