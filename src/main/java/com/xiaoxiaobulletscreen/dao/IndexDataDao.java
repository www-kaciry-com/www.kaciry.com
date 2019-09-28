package com.xiaoxiaobulletscreen.dao;

import com.xiaoxiaobulletscreen.entity.VideoInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IndexDataDao {
    //查询视频类型为VideoType排序后的前8条数据(降序)
    @Select("select * from user_video where videoType = #{videoType} ORDER BY videoPlayNum DESC limit #{length}")
    List<VideoInfo> SelectVideoDataByType(@Param("videoType") String videoType,@Param("length") int length);

    @Select("select * from user_video where videoType = #{videoType}")
    List<VideoInfo> SelectVideoData(String videoType);



    @Select("select * from user_video where videoName like '%${keyword}%'")
    List<VideoInfo> FuzzySearch(@Param("keyword") String keyword);

    @Select("select * from user_video where videoName = #{keyword}")
    List<VideoInfo> SearchByType(@Param("keyword") String keyword);

}
