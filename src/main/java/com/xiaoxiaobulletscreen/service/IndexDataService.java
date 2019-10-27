package com.xiaoxiaobulletscreen.service;

import com.xiaoxiaobulletscreen.entity.VideoInfo;

import java.util.List;
/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 初始化首页数据Service接口
 */
public interface IndexDataService {
    /**
     * @author kaciry
     * @description  根据视频类型查询视频信息，返回length个视频
     * @date  2019/10/26 13:05
     * @param videoType 视频类型
     * @param length 返回的个数
     * @return java.util.List<com.xiaoxiaobulletscreen.entity.VideoInfo>
    **/
    List<VideoInfo> selectIndexDataByType(String videoType, int length);





}
