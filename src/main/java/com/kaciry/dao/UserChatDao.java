package com.kaciry.dao;

import com.kaciry.entity.UserChatDO;
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
     * @param userChatDO 用户聊天实体类
     * @author kaciry
     * @description 添加一条用户间的聊天信息
     * @date 2019/10/25 18:09
     **/
    @Insert("insert into user_private_chat (senderIdentityDocument,receiverIdentityDocument,content,date) " +
            "values (#{senderIdentityDocument},#{receiverIdentityDocument},#{content},#{date})")
    boolean insertUserChatMsg(UserChatDO userChatDO);

    /**
     * @param senderIdentityDocument   发送方ID
     * @param receiverIdentityDocument 接收方ID
     * @return java.util.List<com.kaciry.entity.UserChatBean>
     * @author kaciry
     * @description 根据双方用户名查询聊天记录
     * @date 2019/10/25 18:10
     **/
    @Select("select * from user_private_chat where senderIdentityDocument = #{senderIdentityDocument} and receiverIdentityDocument = #{receiverIdentityDocument}")
    List<UserChatDO> queryChatMsg(@Param("senderIdentityDocument") String senderIdentityDocument, @Param("receiverIdentityDocument") String receiverIdentityDocument);

    /**
     * @param senderIdentityDocument   发送方ID
     * @param userChatIdentityDocument 接收方ID
     * @return java.util.List<com.kaciry.entity.UserChatBean>
     * @author kaciry
     * @description 根据双方用户名查询新聊天记录
     * @date 2019/10/28 18:03
     **/
    @Select("SELECT * FROM user_private_chat WHERE senderIdentityDocument = #{senderIdentityDocument} and receiverIdentityDocument = #{receiverIdentityDocument} and userChatIdentityDocument > #{userChatIdentityDocument}")
    List<UserChatDO> getNewMsg(@Param("senderIdentityDocument") String senderIdentityDocument, @Param("receiverIdentityDocument") String receiverIdentityDocument, @Param("userChatIdentityDocument") int userChatIdentityDocument);
}
