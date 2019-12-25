package com.kaciry.dao;

import com.kaciry.entity.CommentDO;
import com.kaciry.entity.OperationsDO;
import com.kaciry.entity.ReportVideoDO;
import com.kaciry.entity.VideoInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/25 18:46
 * @description 用户视频操作Dao
 */
@Component
public interface VideoDao {

    @Update("update user_video set videoPlayNum = videoPlayNum + 1 where videoFilename = #{videoFilename}")
    int addVideoPlayNumByVideoFilename(String videoFilename);

    /**
     * @param commentDO Comment实体
     * @return boolean
     * @author kaciry
     * @description 添加新评论
     * @date 2019/10/25 18:46
     **/
    @Insert("insert into comment (videoFilename,username,content,sendDate,commentStars) values(#{videoFilename},#{username},#{content},#{sendDate},#{commentStars})")
    boolean insertComment(CommentDO commentDO);

    /**
     * @param videoFilename 视频文件名
     * @return java.util.List<com.kaciry.entity.Comment>
     * @author kaciry
     * @description 查询视频评论信息
     * @date 2019/10/25 18:46
     **/
    @Select("select * from comment LEFT JOIN user on user.username = comment.username WHERE videoFilename=#{videoFilename}")
    List<CommentDO> selectVideoComment(String videoFilename);

    /**
     * @param videoAddress 视频文件名
     * @return com.kaciry.entity.VideoInfo
     * @author kaciry
     * @description 查询视频相关信息
     * @date 2019/10/25 18:47
     **/
    @Select("select * from user_video LEFT JOIN user ON user.username = user_video.username WHERE videoFilename = #{videoAddress}")
    VideoInfo selectVideoInfo(String videoAddress);

    /**
     * @param videoFilename 视频文件名
     * @author kaciry
     * @description 使user_video表中videoFilename对应的star数量加一
     * @date 2019/10/25 18:47
     **/
    @Update("update user_video set videoStars = videoStars + 1 where videoFilename = #{videoFilename}")
    void updateVideoStarAdd(String videoFilename);

    /**
     * @param videoFilename 视频文件名
     * @author kaciry
     * @description 使user_video表中videoFilename对应的star数量加一
     * @date 2019/10/25 18:47
     **/
    @Update("update user_video set videoShares = videoShares + 1 where videoFilename = #{videoFilename}")
    void updateVideoShareAdd(String videoFilename);

    /**
     * @param videoFilename 视频文件名
     * @author kaciry
     * @description 使user_video表中videoFilename对应的collection数量加一
     * @date 2019/10/25 18:47
     **/
    @Update("update user_video set videoConnections = videoConnections + 1 where videoFilename = #{videoFilename}")
    void updateVideoCollectAdd(String videoFilename);

    /**
     * @param videoFilename 视频文件名
     * @author kaciry
     * @description 使user_video表中videoFilename对应的star数量减一
     * @date 2019/10/25 18:47
     **/
    @Update("update user_video set videoStars = videoStars - 1 where videoFilename = #{videoFilename}")
    void updateVideoStarSub(String videoFilename);

    /**
     * @param videoFilename 视频文件名
     * @author kaciry使user_video表中videoFilename对应的star数量减一
     * @description
     * @date 2019/10/25 18:48
     **/
    @Update("update user_video set videoConnections = videoConnections - 1 where videoFilename = #{videoFilename}")
    void updateVideoCollectSub(String videoFilename);

    /**
     * @param videoFilename 视频文件名
     * @param videoBarrages 视频弹幕数量
     * @author kaciry
     * @description 根据视频文件名更新弹幕数量
     * @date 2019/11/11 17:53
     **/
    @Update("update user_video set videoBarrages = #{videoBarrages} where videoFilename = #{videoFilename}")
    void updateVideoBarragesAdd(@Param("videoFilename") String videoFilename, @Param("videoBarrages") int videoBarrages);

    /**
     * @param operationsDO Ops实体
     * @return com.kaciry.entity.Ops
     * @author kaciry
     * @description 初始化视频页面时，查询用户是否对该视频进行过点赞收藏投币操作
     * @date 2019/10/25 18:48
     **/
    @Select("select * from ops where username=#{username} and videoFilename=#{videoFilename}")
    OperationsDO selectOperationsState(OperationsDO operationsDO);

    /**
     * @param operationsDO Ops实体
     * @author kaciry
     * @description 添加一条数据，状态符为参数
     * @date 2019/10/25 18:48
     **/
    @Insert("insert into ops (username,videoFilename,isStar,isCoin,isCollect,isShare) values(#{username},#{videoFilename},#{isStar},#{isCoin},#{isCollect},#{isShare})")
    void insertOperationsData(OperationsDO operationsDO);

    /**
     * @param operationsDO Ops实体
     * @author kaciry
     * @description 更改参数状态
     * @date 2019/10/25 18:48
     **/
    @Update("update ops set isStar = #{isStar} where username=#{username} and videoFilename=#{videoFilename}")
    void updateStarState(OperationsDO operationsDO);

    /**
     * @param operationsDO Ops实体
     * @return java.lang.Integer
     * @author kaciry
     * @description 查询是否存在数据项
     * @date 2019/10/25 18:48
     **/
    @Select("select * from ops where username=#{username} and videoFilename=#{videoFilename}")
    OperationsDO selectOperationsData(OperationsDO operationsDO);

    /**
     * @param operationsDO Ops实体
     * @author kaciry
     * @description 更改参数状态
     * @date 2019/10/25 18:49
     **/
    @Update("update ops set isCollect = #{isCollect} where username=#{username} and videoFilename=#{videoFilename}")
    void changeCollectState(OperationsDO operationsDO);

    /**
     * @param operationsDO Ops实体
     * @return boolean
     * @author kaciry
     * @description 数据项都为0时，删除该条数据
     * @date 2019/10/25 18:49
     **/
    @Delete("delete from ops where username=#{username} and videoFilename=#{videoFilename}")
    boolean deleteOpsData(OperationsDO operationsDO);

    /**
     * @param operationsDO Ops实体
     * @author kaciry
     * @description 更改ops表中，分享状态
     * @date 2019/10/25 18:49
     **/
    @Update("update ops set isShare = #{isShare} where username=#{username} and videoFilename=#{videoFilename}")
    void updateShareData(OperationsDO operationsDO);

    /**
     * @param reportVideoDO ReportVideoBean实体
     * @return boolean
     * @author kaciry
     * @description 添加一条视频举报信息数据
     * @date 2019/10/25 18:50
     **/
    @Insert("insert reportVideo (videoFileName,reportedType,beReportedUser,reportedUser,reportedTime,reportedReason)" +
            " values (#{videoFileName},#{reportedType},#{beReportedUser},#{reportedUser},#{reportedTime},#{reportedReason})")
    boolean insertReportVideoData(ReportVideoDO reportVideoDO);

    /**
     * @param reportVideoDO ReportVideoBean实体
     * @return java.lang.Integer
     * @author kaciry
     * @description 查询该用户是否举报过该视频
     * @date 2019/10/25 18:50
     **/
    @Select("select * from reportVideo where reportedUser=#{reportedUser} and videoFileName=#{videoFileName}")
    Integer selectReportData(ReportVideoDO reportVideoDO);

    @Delete("DELETE user_video,comment,ops FROM user_video " +
            "LEFT JOIN (comment LEFT JOIN ops ON comment.videoFilename = ops.videoFilename) ON user_video.videoFilename = comment.videoFilename " +
            "WHERE user_video.videoFilename = #{videoFilename}")
    int deleteVideoByVideoFilename(String videoFilename);
}
