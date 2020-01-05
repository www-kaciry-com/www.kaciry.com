package com.kaciry.dao;

import com.kaciry.entity.PromoteVideosDO;
import com.kaciry.entity.VideoInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/11/1 14:05
 * @description 推广视频Dao
 */
@Component
public interface PromoteVideosDao {

    @Select("SELECT * FROM user_video WHERE videoState = 1 AND username = #{username}")
    List<VideoInfoDO> selectNormalVideos(String username);
    /**
     * @author kaciry
     * @description  查询推广视频需要等待的时间
     * @date  2019/11/1 22:39
     * @return com.kaciry.entity.PromoteVideosBean
    **/
    @Select("SELECT surplusDuration from promote_videos WHERE promoteType = #{option} LIMIT #{limitNum}")
    List<PromoteVideosDO> selectPromoteVideo(@Param("option") int option, @Param("limitNum") int limitNum);

    /**
     * @author kaciry
     * @description  添加一条推广视频信息
     * @date  2019/11/2 12:35
     * @param promoteVideosDO PromoteVideosBean实体，详情见PromoteVideosBean类
     * @return boolean
    **/
    @Insert("INSERT INTO promote_videos (videoFilename,surplusDuration,promoteType) VALUES (#{videoFilename},#{surplusDuration},#{promoteType})")
    boolean insertPromoteVideo(PromoteVideosDO promoteVideosDO);

    /**
     * @author kaciry
     * @description  查询一条推荐视频信息是否存在
     * @date  2019/11/2 14:46
     * @param promoteVideosDO PromoteVideosBean实体，详情见PromoteVideosBean类
     * @return java.lang.Integer
    **/
    @Select("SELECT tableIndex FROM promote_videos WHERE videoFilename = #{videoFilename} AND promoteType = #{promoteType}")
    Integer selectVideoIsPromoted(PromoteVideosDO promoteVideosDO);

    /**
     * @author kaciry
     * @description  获取正在生效的推广视频信息
     * @date  2019/11/2 14:47
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
    **/
    @Select("SELECT * FROM promote_videos WHERE promoteType <> 0")
    List<PromoteVideosDO> selectPromotedVideos();

    /**
     * @author kaciry
     * @description  获取promoteType为1的视频信息(轮播图区域)
     * @date  2019/11/2 15:27
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
    **/
    @Select("SELECT * FROM promote_videos WHERE promoteType = 1")
    List<PromoteVideosDO> selectPromotedVideos4Carousel();

    /**
     * @author kaciry
     * @description  获取promoteType为2的视频信息(列表区域)
     * @date  2019/11/2 15:28
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
    **/
    @Select("SELECT * FROM promote_videos WHERE promoteType = 2")
    List<PromoteVideosDO> selectPromotedVideos4List();

    /**
     * @author kaciry
     * @description  失效视频文件名为VideoFilename的视频
     * @date  2019/11/2 14:48
     * @param videoFilename 视频文件名
     * @return boolean
    **/
    @Update("UPDATE promote_videos SET promoteType = 0 WHERE videoFilename = #{videoFilename}")
    boolean setPromoteVideoTimeOver(@Param("videoFilename") String videoFilename);

    /**
     * @author kaciry
     * @description
     * @date  2019/11/2 15:28
     * @param videoFilename 视频文件
     * @param videoState 视频状态
     * @return boolean
    **/
    @Update("UPDATE user_video SET videoState = #{videoState} WHERE videoFilename = #{videoFilename}")
    boolean updateUserVideoState(@Param("videoFilename") String videoFilename, @Param("videoState") int videoState);

}
