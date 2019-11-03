package com.kaciry.dao;

import com.kaciry.entity.VideoInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/25 17:58
 * @description 主页信息的初始化Dao
 */
@Component
public interface IndexDataDao {
    /**
     * @param videoType 视频的类型
     * @param length    视频的个数
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 查询视频类型为VideoType排序后的前8条数据(降序)
     * @date 2019/10/25 17:59
     **/
    @Select("select * from user_video where videoType = #{videoType} ORDER BY videoPlayNum DESC limit #{length}")
    List<VideoInfo> selectVideoDataByType(@Param("videoType") String videoType, @Param("length") int length);

    /**
     * @param videoType 视频类型
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 通过视频类型查询视频相关信息
     * @date 2019/10/25 18:02
     **/
    @Select("select * from user_video LEFT JOIN user ON user.username = user_video.username WHERE videoType = #{videoType} AND videoState = 1")
    List<VideoInfo> selectVideoData(String videoType);

    /**
     * @param keyword 关键词
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 通过关键词搜索视频信息
     * @date 2019/10/25 18:03
     **/
    @Select("select * from user_video LEFT JOIN user ON user.username = user_video.username WHERE videoName like '%${keyword}%' AND videoState <> 0")
    List<VideoInfo> fuzzySearch(@Param("keyword") String keyword);

    @Select("select * from user_video where videoName = #{keyword}")
    List<VideoInfo> searchByType(@Param("keyword") String keyword);

}
