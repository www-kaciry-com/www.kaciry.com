package com.kaciry.dao;

import com.kaciry.entity.User;
import com.kaciry.entity.VideoInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
     * @param length    视频的个数
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 查询视频类型为VideoType排序后的前8条数据(降序)
     * @date 2019/10/25 17:59
     **/
    @Select("select videoFilename,videoName from user_video ORDER BY videoPlayNum DESC limit #{length}")
    List<VideoInfoDO> selectVideoDataByType(int length);

    /**
     * @param videoType 视频类型
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 通过视频类型查询视频相关信息
     * @date 2019/10/25 18:02
     **/
    @Select("select * from user_video LEFT JOIN user ON user.username = user_video.username WHERE videoType = #{videoType} AND videoState = 1")
    List<VideoInfoDO> selectVideoData(String videoType);

    @Select("SELECT * FROM user_video where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(videoData) AND videoState = 1")
    List<VideoInfoDO> selectVideos();

    /**
     * @param keyword 关键词
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 通过关键词搜索视频信息
     * @date 2019/10/25 18:03
     **/
    @Select("select * from user_video LEFT JOIN user ON user.username = user_video.username WHERE videoName like '%${keyword}%' AND videoState <> 0")
    List<VideoInfoDO> selectFuzzySearch(@Param("keyword") String keyword);

    @Select("Select videoType,COUNT(videoIdentityDocument) As videoCoins FROM user_video group by videoType")
    List<VideoInfoDO> selectVideoNum();

    @Insert("INSERT INTO user_ip (userIp,userAddress) VALUES (#{ip},#{city})")
    void insertUserIPAndAddress(@Param("ip") String ip, @Param("city") String city);

    @Update("DELETE FROM user_ip")
    boolean invalidIPData();

    @Select("SELECT username,userNickName,userSignature,userHeadIcon FROM user WHERE INSTR(username,#{keyword})>0")
    List<User> selectUserByUsername(String keyword);

    @Select("SELECT username,userNickName,userSignature,userHeadIcon FROM user WHERE INSTR(userNickName,#{keyword})>0")
    List<User> selectUserByUserNickname(String keyword);

}
