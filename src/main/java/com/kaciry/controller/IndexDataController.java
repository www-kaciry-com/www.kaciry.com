package com.kaciry.controller;

import com.kaciry.entity.VideoInfo;
import com.kaciry.service.Impl.IndexDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/25 17:05
 * @description 初始化首页数据Controller
 */
@Controller
public class IndexDataController {
    @Autowired
    private IndexDataServiceImpl indexDataService;

    /**
     * @param videoType 视频类型
     * @param length    视频个数
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 初始化首页视频数据
     * @date 2019/10/25 17:06
     **/
    @PostMapping(value = "/indexDataInit")
    @ResponseBody
    public List<VideoInfo> indexDataInit(String videoType, int length) {
        return indexDataService.selectIndexDataByType(videoType, length);
    }

    /**
     * @param videoType 视频类型
     * @param length    视频个数
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 初始化首页视频播放排行榜
     * @date 2019/10/25 17:12
     **/
    @PostMapping(value = "/playRank")
    @ResponseBody
    public List<VideoInfo> indexPlayRankInit(String videoType, int length) {
        return indexDataService.selectIndexDataByType(videoType, length, true);
    }

}