package com.kaciry.mapper;

import com.kaciry.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/25 18:27
 * @description 用户聊天Dao
 */
@Component
public interface UserDao {
    /**
     * @param username     用户名
     * @param userPassword 密码
     * @return com.kaciry.entity.user
     * @author kaciry
     * @description 查询一个User
     * @date 2019/10/25 18:28
     **/
    @Select("select * from user  where BINARY username=#{username}")
    User login(@Param("username") String username, @Param("userPassword") String userPassword);

    @Update("UPDATE user SET userPassword = #{password} WHERE username = #{username}")
    boolean updateUserPassword(@Param("username")String username,@Param("password")String password);

    /**
     * @param user User实体
     * @author kaciry
     * @description 添加一个User
     * @date 2019/10/25 18:29
     **/
    @Insert("insert into user (username,userPassword,userEmail,userPhoneNumber,userSex,userCoins,isVip,userLevel,userNickName,userHeadIcon) " +
            "values(#{username},#{userPassword},#{userEmail},#{userPhoneNumber},#{userSex},#{userCoins},#{isVip},#{userLevel},#{userNickName},#{userHeadIcon})")
    void setOneUser(User user);

    /**
     * @param user User实体
     * @return boolean
     * @author kaciry
     * @description 修改一个User
     * @date 2019/10/25 18:31
     **/
    @Update({"update user set userEmail = #{userEmail},userPhoneNumber = #{userPhoneNumber},userNickName = #{userNickName},userSex = #{userSex} where username = #{username}"})
    boolean updateUserInfo(User user);

    /**
     * @param userHeadIcon 头像路径
     * @param username     用户名
     * @return boolean
     * @author kaciry
     * @description 修改头像
     * @date 2019/10/25 18:33
     **/
    @Update("update user set userHeadIcon = #{userHeadIcon} where username = #{username}")
    boolean updateUserHeadIcon(@Param("userHeadIcon") String userHeadIcon, @Param("username") String username);

    /**
     * @param userEmail    用户邮箱
     * @param userPassword 用户密码
     * @return boolean
     * @author kaciry
     * @description 用户重置密码
     * @date 2019/10/25 18:34
     **/
    @Update("update user set userPassword = #{userPassword} where userEmail = #{userEmail}")
    boolean updatePassword(@Param("userEmail") String userEmail, @Param("userPassword") String userPassword);

    /**
     * @param username 用户名
     * @return com.kaciry.entity.user
     * @author kaciry
     * @description 查询一个User
     * @date 2019/10/25 18:36
     **/
    @Select("select * from user  where username=#{username}")
    User selectInfo(@Param("username") String username);

    /**
     * @param videoInfo VideoInfo实体类
     * @return boolean
     * @author kaciry
     * @description 将用户上传的文件名保存到数据库
     * @date 2019/10/25 18:36
     **/
    @Insert("insert into user_video (username,videoTitle,videoType,videoState,videoName,videoFilename,videoDescription,videoCover,videoData,videoStars,videoCoins,videoConnections,videoShares,videoPlayNum,videoBarrages)" +
            " values(#{username},#{videoTitle},#{videoType},#{videoState},#{videoName},#{videoFilename},#{videoDescription},#{videoCover},#{videoData},#{videoStars},#{videoCoins},#{videoConnections},#{videoShares},#{videoPlayNum},#{videoBarrages})")
    boolean insertVideo(VideoInfo videoInfo);

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 查询用户的投稿, 查询相关视频信息
     * @date 2019/10/25 18:37
     **/
    @Select("select * from user_video where username = #{username} AND videoState <> 0")
    List<VideoInfo> selectVideos(String username);

    /**
     * @param username 用户名
     * @param state    是否收藏过该视频
     * @return java.util.List<java.lang.String>
     * @author kaciry
     * @description 查询用户的收藏
     * @date 2019/10/25 18:37
     **/
    @Select("select videoFileName from ops where username = #{username} and isCollect = #{state}")
    List<String> queryCollect(@Param("username") String username, @Param("state") int state);

    /**
     * @param videoFilename 视频文件名
     * @return com.kaciry.entity.VideoInfo
     * @author kaciry
     * @description 根据视频文件名查询视频相关信息
     * @date 2019/10/25 18:38
     **/
    @Select("select * from user_video where videoFilename = #{videoFilename}")
    VideoInfo queryVideosByVideoFileName(@Param("videoFilename") String videoFilename);

    /**
     * @param username    用户名
     * @param hisUsername 被关注方用户名
     * @return com.kaciry.entity.FansBean
     * @author kaciry
     * @description 查询是否关注过该用户
     * @date 2019/10/25 18:40
     **/
    @Select("select * from follow_others where userIdentityDocument = #{username} and followedUser = #{hisUsername}")
    FansBean queryFansInfo(@Param("username") String username, @Param("hisUsername") String hisUsername);

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.FansBean>
     * @author kaciry
     * @description 查询用户关注
     * @date 2019/10/25 18:41
     **/
    @Select("select * from follow_others where userIdentityDocument = #{username}")
    List<FansBean> queryFollows(@Param("username") String username);

    /**
     * @param fansBean FansBean实体
     * @return boolean
     * @author kaciry
     * @description 添加关注信息
     * @date 2019/10/25 18:42
     **/
    @Insert("insert into follow_others(userIdentityDocument,followedUser,followedDate) values(#{userIdentityDocument},#{followedUser},#{followedDate})")
    boolean addFansInfo(FansBean fansBean);

    /**
     * @param username    用户名
     * @param hisUsername 取消关注的对象的用户名
     * @return boolean
     * @author kaciry
     * @description 取消关注
     * @date 2019/10/25 18:42
     **/
    @Delete("delete from follow_others where userIdentityDocument=#{username} and followedUser=#{hisUsername}")
    boolean cancelFollow(@Param("username") String username, @Param("hisUsername") String hisUsername);

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.UnionFansBean>
     * @author kaciry
     * @description 查询我关注的所有用户
     * @date 2019/10/25 18:43
     **/
    @Select("SELECT * FROM user LEFT JOIN follow_others on user.username = follow_others.followedUser WHERE userIdentityDocument = #{username} and  user.username = follow_others.followedUser")
    List<UnionFansBean> queryMyFollows(@Param("username") String username);

    /**
     * @param reportCommentBean ReportCommentBean实体
     * @return java.lang.Integer
     * @author kaciry
     * @description 查询是否存在该用户对同一评论重复举报
     * @date 2019/10/25 18:43
     **/
    @Select("SELECT * FROM reportComments WHERE commentIdentityDocument = #{commentIdentityDocument} AND reportedUser = #{reportedUser}")
    Integer queryReportComment(ReportCommentBean reportCommentBean);

    /**
     * @param reportCommentBean ReportCommentBean实体
     * @return boolean
     * @author kaciry
     * @description 添加举报评论信息
     * @date 2019/10/25 18:44
     **/
    @Insert("INSERT INTO reportComments (commentIdentityDocument,reportedType,beReportedUser,reportedUser,reportedTime,reportedReason,commentContent)" +
            " VALUES (#{commentIdentityDocument},#{reportedType},#{beReportedUser},#{reportedUser},#{reportedTime},#{reportedReason},#{commentContent})")
    boolean addReportComment(ReportCommentBean reportCommentBean);

    /**
     * @param commentIdentityDocument 评论ID
     * @return com.kaciry.entity.Comment
     * @author kaciry
     * @description 根据评论ID查询信息
     * @date 2019/10/25 18:44
     **/
    @Select("SELECT * FROM comment WHERE commentId = #{commentIdentityDocument}")
    CommentBean queryCommentByIdentityDocument(int commentIdentityDocument);
}
