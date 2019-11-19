package com.kaciry.mapper;

import com.kaciry.entity.PromoteVideosBean;
import com.kaciry.entity.VideoInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/11/1 14:05
 * @description 推广视频Dao
 */
@Component
public interface PromoteVideosMapper {

    @Select("SELECT * FROM user_video WHERE videoState = 1 AND username = #{username}")
    List<VideoInfo> selectNormalVideos(String username);
    /**
     * @author kaciry
     * @description  查询推广视频需要等待的时间
     * @date  2019/11/1 22:39
     * @return com.kaciry.entity.PromoteVideosBean
    **/
    //@Select("SELECT * from promote_videos WHERE promoteType = #{option} ORDER BY tableIndex DESC  LIMIT 1")
    @Select("SELECT * from promote_videos WHERE promoteType = #{option} ORDER BY tableIndex DESC  LIMIT #{limitNum}")
    List<PromoteVideosBean>  selectPromoteVideo(@Param("option") int option,@Param("limitNum")int limitNum);

    /**
     * @author kaciry
     * @description  添加一条推广视频信息
     * @date  2019/11/2 12:35
     * @param promoteVideosBean PromoteVideosBean实体，详情见PromoteVideosBean类
     * @return boolean
    **/
    @Insert("INSERT INTO promote_videos (videoFilename,surplusDuration,promoteType) VALUES (#{videoFilename},#{surplusDuration},#{promoteType})")
    boolean addPromoteVideo(PromoteVideosBean promoteVideosBean);

    /**
     * @author kaciry
     * @description  查询一条推荐视频信息是否存在
     * @date  2019/11/2 14:46
     * @param promoteVideosBean PromoteVideosBean实体，详情见PromoteVideosBean类
     * @return java.lang.Integer
    **/
    @Select("SELECT * FROM promote_videos WHERE videoFilename = #{videoFilename} AND promoteType = #{promoteType}")
    Integer selectVideoIsPromoted(PromoteVideosBean promoteVideosBean);

    /**
     * @author kaciry
     * @description  获取正在生效的推广视频信息
     * @date  2019/11/2 14:47
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
    **/
    @Select("SELECT * FROM promote_videos WHERE promoteType <> 0")
    List<PromoteVideosBean> selectPromotedVideos();

    /**
     * @author kaciry
     * @description  获取promoteType为1的视频信息(轮播图区域)
     * @date  2019/11/2 15:27
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
    **/
    @Select("SELECT * FROM promote_videos WHERE promoteType = 1")
    List<PromoteVideosBean> selectPromotedVideos4Carousel();

    /**
     * @author kaciry
     * @description  获取promoteType为2的视频信息(列表区域)
     * @date  2019/11/2 15:28
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
    **/
    @Select("SELECT * FROM promote_videos WHERE promoteType = 2")
    List<PromoteVideosBean> selectPromotedVideos4List();

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
    boolean setUserVideoState(@Param("videoFilename") String videoFilename, @Param("videoState")int videoState);


    @Update("UPDATE promote_videos SET surplusDuration = #{surplusDuration} WHERE videoFilename = #{videoFilename}")
    boolean setPromoteVideoDuration(@Param("videoFilename") String videoFilename,@Param("surplusDuration") Timestamp surplusDuration);

}
