package com.xiaoxiaobulletscreen.dao;

import com.xiaoxiaobulletscreen.entity.Comment;
import com.xiaoxiaobulletscreen.entity.Ops;
import com.xiaoxiaobulletscreen.entity.VideoInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoDao {

    //添加新评论
    @Insert("insert into comment (videoFilename,username,content,sendDate,commentStars,userNickName,userHeadIcon) values(#{videoFilename},#{username},#{content},#{sendDate},#{commentStars},#{userNickName},#{userHeadIcon})")
    boolean addNewComment(Comment comment);

    //查询视频评论信息
    @Select("select * from comment  where videoFilename=#{videoFilename}")
    List<Comment> selectVideoComment(String videoFilename);

    //查询视频相关信息
    @Select("select * from user_video where videoFilename = #{videoAddress}")
    VideoInfo initVideoInfo(String videoAddress);

    //使user_video表中videoFilename对应的star数量加一
    @Update("update user_video set videoStars = videoStars + 1 where videoFilename = #{videoFilename}")
    void updateVideoStarAdd(String videoFilename);

    //使user_video表中videoFilename对应的collection数量加一
    @Update("update user_video set videoConnections = videoConnections + 1 where videoFilename = #{videoFilename}")
    void updateVideoCollectAdd(String videoFilename);

    //使user_video表中videoFilename对应的star数量减一
    @Update("update user_video set videoStars = videoStars - 1 where videoFilename = #{videoFilename}")
    void updateVideoStarSub(String videoFilename);

    //使user_video表中videoFilename对应的star数量减一
    @Update("update user_video set videoConnections = videoConnections - 1 where videoFilename = #{videoFilename}")
    void updateVideoCollectSub(String videoFilename);

    //初始化视频页面时，查询用户是否对该视频进行过点赞收藏投币操作
    @Select("select * from ops where username=#{username} and videoFilename=#{videoFilename}")
    Ops queryOpsState(Ops ops);

    //添加一条数据，状态符为参数
    @Insert("insert into ops (username,videoFilename,isStar,isCoin,isCollect) values(#{username},#{videoFilename},#{isStar},#{isCoin},#{isCollect})")
    void addOpsData(Ops ops);

//    //查询是否点赞过
//    // Mybatis的mapper文件的返回类型resultType为Integer,返回类型设置为封装类型Integer而不是基本类型int。
//    @Select("select * from ops where username=#{username} and videoFilename=#{videoFilename} and star = #{star}")
//    Integer queryStarState(Ops ops);

    //更改参数状态
    @Update("update ops set isStar = #{isStar} where username=#{username} and videoFilename=#{videoFilename}")
    void changeStarState(Ops ops);

    //查询是否存在数据项
    @Select("select * from ops where username=#{username} and videoFilename=#{videoFilename}")
    Integer queryOpsData(Ops ops);

    //更改参数状态
    @Update("update ops set isCollect = #{isCollect} where username=#{username} and videoFilename=#{videoFilename}")
    void changeCollectState(Ops ops);

//    //点赞，增加一条数据
//    @Insert("insert into ops (username,videoFilename,star) values(#{username},#{videoFilename},#{star})")
//    void insertStarState(Ops ops);
//
//    @Select("select * from ops where username=#{username} and videoFilename=#{videoFilename} and star = #{star}")
//    Integer queryCollectState(Ops ops);

    //数据项都为0时，删除该条数据
    @Delete("delete from ops where username=#{username} and videoFilename=#{videoFilename}")
    boolean deleteOpsData(Ops ops);


}
