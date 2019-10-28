package com.kaciry.service;

import com.kaciry.entity.VideoInfo;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 搜索数据Service接口
 */
public interface SearchDataService {
    /**
     * @author kaciry
     * @description  通过关键字搜索视频信息，模糊查询(videoName)
     * @date  2019/10/26 13:13
     * @param keyword 关键字
     * @return java.util.List<com.kaciry.entity.VideoInfo>
    **/
    List<VideoInfo> searchKeyword(String keyword);
    /**
     * @author kaciry
     * @description  通过视频类型查询视频信息
     * @date  2019/10/26 13:13
     * @param keyword 关键词
     * @return java.util.List<com.kaciry.entity.VideoInfo>
    **/
    List<VideoInfo> searchByType(String keyword);

}
