package com.kaciry.service;

import com.kaciry.entity.*;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 有关用户信息的Service接口
 */
public interface UserService {
    /**
     * @param username 用户名
     * @param password 密码
     * @return com.kaciry.entity.User
     * @author kaciry
     * @description 用户登录
     * @date 2019/10/26 13:22
     **/
    User login(String username, String password);

    ResultBean updateUserPassword(String username,String originPassword,String password);

    /**
     * @param user User实体，包含信息见实体类
     * @return java.lang.String
     * @author kaciry
     * @description 用户注册
     * @date 2019/10/26 13:23
     **/
    String register(User user);

    /**
     * @param user User实体，包含信息见实体类
     * @return com.kaciry.entity.User
     * @author kaciry
     * @description 更改用户信息
     * @date 2019/10/26 13:23
     **/
    User changeInfo(User user);

    /**
     * @param username 用户名
     * @return com.kaciry.entity.User
     * @author kaciry
     * @description 根据用户名查询用户信息
     * @date 2019/10/26 13:23
     **/
    User selectUserInfoByUsername(String username);

    /**
     * @param videoInfo VideoInfo实体，包含信息见实现类
     * @return boolean
     * @author kaciry
     * @description 上传视频
     * @date 2019/10/26 13:23
     **/
    boolean uploadVideo(VideoInfo videoInfo);

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 根据用户名查询用户上传的视频
     * @date 2019/10/26 13:23
     **/
    List<VideoInfo> selectVideosByUsername(String username);

    /**
     * @param email 邮箱
     * @param password 密码
     * @return boolean
     * @author kaciry
     * @description 重置密码
     * @date 2019/10/26 13:23
     **/
    boolean updatePassword(String email, String password);

    /**
     * @author kaciry
     * @description  修改用户头像
     * @date  2019/10/26 13:29
     * @param userHeadIcon 用户头像路径
     * @param username 用户名
     * @return boolean
    **/
    boolean updateUserHeadIcon(String userHeadIcon, String username);

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 通过用户名查询投稿
     * @date 2019/10/26 13:23
     **/
    List<VideoInfo> selectCollectionsByUsername(String username);

    /**
     * @param videoFilename 视频文件名
     * @return com.kaciry.entity.VideoInfo
     * @author kaciry
     * @description 通过视频文件名查询视频信息
     * @date 2019/10/26 13:23
     **/
    VideoInfo selectVideoInfoByVideoFilename(String videoFilename);

    /**
     * @author kaciry
     * @description  关注他人
     * @date  2019/10/26 13:32
     * @param username 当前操作用户的用户名
     * @param hisUsername 需要关注的用户的用户名
     * @return com.kaciry.entity.ResultBean
    **/
    ResultBean followOthers(String username, String hisUsername);

    /**
     * @author kaciry
     * @description  查询关注状态
     * @date  2019/10/26 13:32
     * @param username 当前操作用户的用户名
     * @param hisUsername 需要查询的用户名
     * @return com.kaciry.entity.FansBean
    **/
    FansBean selectFollowsState(String username, String hisUsername);

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.FansBean>
     * @author kaciry
     * @description
     * @date 2019/10/26 13:23
     **/
    List<FansBean> queryFollows(String username);

    /**
     * @param reportCommentBean ReportCommentBean实体，包含信息见实体类
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 举报评论
     * @date 2019/10/26 13:23
     **/
    ResultBean reportComment(ReportCommentBean reportCommentBean);

    /**
     * @param commentIdentityDocument 评论ID
     * @return com.kaciry.entity.Comment
     * @author kaciry
     * @description 根据评论ID查询评论
     * @date 2019/10/26 13:23
     **/
    CommentBean queryCommentByIdentityDocument(int commentIdentityDocument);
}
