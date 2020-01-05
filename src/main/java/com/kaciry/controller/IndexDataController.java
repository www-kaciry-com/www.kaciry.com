package com.kaciry.controller;

import com.kaciry.entity.VideoInfoDO;
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
@ResponseBody
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
    public List<VideoInfoDO> indexDataInit(String videoType, int length) {
        return indexDataService.selectIndexDataByType(videoType, length);
    }

    /**
     * @param length    视频个数
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 初始化首页视频播放排行榜
     * @date 2019/10/25 17:12
     **/
    @PostMapping(value = "/playRank")
    public List<VideoInfoDO> indexPlayRankInit(int length) {
        return indexDataService.selectIndexDataByType(length);
    }

    @PostMapping(value = "/indexSynthesizeVideos")
    @ResponseBody
    public List<VideoInfoDO> indexSynthesizeVideos(int length) {
        return indexDataService.querySynthesizeVideos(length);
    }

    /**
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 初始化首页视频数量
     * @date 2019/12/8 15:18
     **/
    @PostMapping(value = "/countVideoNum")
    public List<VideoInfoDO> indexVideoNumInit() {
        return indexDataService.countVideoType();
    }

    @PostMapping(value = "/getIPAddress")
    @ResponseBody
    public void getIPAddress(String ip, String city) {
        indexDataService.collectUserInfos(ip, city);
    }

}
