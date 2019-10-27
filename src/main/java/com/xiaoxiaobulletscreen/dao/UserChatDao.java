package com.xiaoxiaobulletscreen.dao;

import com.xiaoxiaobulletscreen.entity.UserChatBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/25 18:08
 * @description 用户聊天Dao
 */
@Component
public interface UserChatDao {
    /**
     * @param userChatBean 用户聊天实体类
     * @return void
     * @author kaciry
     * @description 添加一条用户间的聊天信息
     * @date 2019/10/25 18:09
     **/
    @Insert("insert into user_private_chat (senderIdentityDocument,receiverIdentityDocument,content,date) values (#{senderIdentityDocument},#{receiverIdentityDocument},#{content},#{date})")
    void addUserChatMsg(UserChatBean userChatBean);

    /**
     * @param senderIdentityDocument   发送方ID
     * @param receiverIdentityDocument 接收方ID
     * @return java.util.List<com.xiaoxiaobulletscreen.entity.UserChatBean>
     * @author kaciry
     * @description 根据双方用户名查询聊天记录
     * @date 2019/10/25 18:10
     **/
    @Select("select * from user_private_chat where senderIdentityDocument = #{senderIdentityDocument} and receiverIdentityDocument = #{receiverIdentityDocument}")
    List<UserChatBean> queryChatMsg(@Param("senderIdentityDocument") String senderIdentityDocument, @Param("receiverIdentityDocument") String receiverIdentityDocument);

    @Select("select * from user_private_chat where senderIdentityDocument = #{senderIdentityDocument} and userChatIdentityDocument > #{userChatIdentityDocument}")
    List<UserChatBean> getNewMsg(@Param("senderIdentityDocument") String senderIdentityDocument, @Param("userChatIdentityDocument") int userChatIdentityDocument);
}
