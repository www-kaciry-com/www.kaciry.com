package com.kaciry.dao;

import com.kaciry.entity.Music;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MusicDao {
    @Select("SELECT * FROM user_music WHERE musicFilename = #{musicFilename}")
    List<Music> selectMusicInfo(@Param("musicFilename") String musicFilename);

    @Select("SELECT * FROM user_music ORDER BY musicPlayNum DESC LIMIT 0,12")
    List<Music> selectHotMusic();

    @Select("SELECT * FROM user_music WHERE musicType = #{musicType} ORDER BY musicPlayNum DESC LIMIT 0,24")
    List<Music> selectMoreMusic(@Param("musicType") String musicType);
/**
 * @author FLLH
 * @description  音乐搜索模糊查询  like 要用  $  不能用  #
 * @date  2019/12/14 10:41
 * @param musicName
 * @return java.util.List<com.kaciry.entity.Music>
**/
    @Select("SELECT * FROM user_music WHERE musicName LIKE '%${musicName}%' ORDER BY musicPlayNum DESC LIMIT 0,24")
    List<Music> selectSearchedMusic(@Param("musicName") String musicName);

    @Select("SELECT * FROM user_music WHERE musicType = '网络歌曲' ORDER BY musicPlayNum DESC LIMIT 0,12")
    List<Music> selectNetMusic();

    @Select("SELECT * FROM user_music WHERE musicType = '网络歌曲' ORDER BY musicPlayNum DESC LIMIT 0,12")
    List<Music> selectEnglishMusic();

    @Select("SELECT * FROM user_music WHERE musicType = '网络歌曲' ORDER BY musicPlayNum DESC LIMIT 0,12")
    List<Music> selectOldMusic();

    @Select("SELECT * FROM user_music WHERE musicType = '流行歌曲' ORDER BY musicPlayNum DESC LIMIT 0,8")
    List<Music> selectPopMusic();

    @Update("UPDATE user_music SET musicPlayNum = musicPlayNum+1 WHERE musicFilename = #{musicFilename}")
    int updatePlayNum(@Param("musicFilename") String musicFilename);
}
