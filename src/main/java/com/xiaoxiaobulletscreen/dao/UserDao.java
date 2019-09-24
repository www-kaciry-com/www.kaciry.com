package com.xiaoxiaobulletscreen.dao;

import com.xiaoxiaobulletscreen.entity.User;
import com.xiaoxiaobulletscreen.entity.VideoInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    //查询一个User
    @Select("select * from user  where BINARY username=#{username}")
    User login(@Param("username") String username, @Param("userPassword") String userPassword);

    //添加一个User
    @Insert("insert into user (username,userPassword,userEmail,userPhoneNumber,userSex,userCoins,isVip,userLevel,userNickName,userHeadIcon) " +
            "values(#{username},#{userPassword},#{userEmail},#{userPhoneNumber},#{userSex},#{userCoins},#{isVip},#{userLevel},#{userNickName},#{userHeadIcon})")
    void setOneUser(User user);

    //修改一个User
    @Update({"update user set userEmail = #{userEmail},userPhoneNumber = #{userPhoneNumber},userNickName = #{userNickName},userSex = #{userSex} where username = #{username}"})
    boolean updateUserInfo(User user);

    //修改密码
    @Update("update user set userPassword = #{userPassword} where userEmail = #{userEmail}")
    boolean updatePassword(@Param("userEmail") String userEmail, @Param("userPassword") String userPassword);

    //查询一个User
    @Select("select * from user  where username=#{username}")
    User selectInfo(@Param("username") String username);

    //将用户上传的文件名保存到数据库
    @Insert("insert into user_video (username,videoTitle,videoType,videoState,videoName,videoFilename,videoDescription,videoCover,videoData,videoStars,videoCoins,videoConnections,videoShares,videoPlayNum,videoBarrages)" +
            " values(#{username},#{videoTitle},#{videoType},#{state},#{videoName},#{videoFileName},#{videoDescription},#{videoCover},#{videoData},#{videoStars},#{videoCoins},#{videoConnections},#{videoShares},#{playNum},#{videoBarrages})")
    boolean insertVideo(VideoInfo videoInfo);

    //查询用户的投稿,查询相关视频信息
    @Select("select * from user_video where username = #{username}")
    List<VideoInfo> selectVideos(String username);

    @Select("select videoFileName from ops where username = #{username} and isCollect = #{state}")
    List<String> queryCollect(@Param("username") String username, @Param("state") int state);

    @Select("select * from user_video where videoFilename = #{videoFilename}")
    VideoInfo queryVideosByVideoFileName(@Param("videoFilename") String videoFilename);
}
