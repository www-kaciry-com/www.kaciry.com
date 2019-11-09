package com.kaciry.controller;

import com.kaciry.entity.PromoteVideosBean;
import com.kaciry.entity.ResultBean;
import com.kaciry.entity.VideoInfo;
import com.kaciry.service.Impl.PromoteVideosServiceImpl;
import com.kaciry.utils.GetAuthorization;
import com.kaciry.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/11/1 13:54
 * @description 推广视频Controller
 */
@Controller
public class PromoteVideosController {
    @Autowired
    private PromoteVideosServiceImpl promoteVideosService;

    @PostMapping(value = "/selectNormalVideo")
    @ResponseBody
    public List<VideoInfo> selectNormalVideo(String token, String username) {
        if (GetAuthorization.isAuthorization(username, token)) {
            return promoteVideosService.selectNormalVideos(username);
        } else {
            return null;
        }

    }

    /**
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 查询推荐视频需要等待的时间
     * @date 2019/11/1 22:01
     **/
    @PostMapping(value = "/queryWaitTime")
    @ResponseBody
    public ResultBean queryWaitTime(String options) {
        return new ResultBean<>(promoteVideosService.queryWaitTime(Integer.parseInt(options)));
    }

    /**
     * @param videoFilename 视频文件名
     * @param promoteType   推广类型
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 推广视频
     * @date 2019/11/1 22:38
     **/
    @PostMapping(value = "/promoteVideo")
    @ResponseBody
    public ResultBean promoteVideo(String token, String username, String videoFilename, int promoteType) {
        if (GetAuthorization.isAuthorization(username, token)) {
            PromoteVideosBean promoteVideosBean = new PromoteVideosBean(videoFilename, TimeUtils.analysisTime(new Timestamp(System.currentTimeMillis())), promoteType);
            return promoteVideosService.addPromoteVideo(promoteVideosBean);
        } else {
            return null;
        }
    }

    /**
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
     * @author kaciry
     * @description 获取轮播图区域的推荐视频
     * @date 2019/11/2 15:26
     **/
    @PostMapping(value = "/initPromoteVideos4Carousel")
    @ResponseBody
    public List<VideoInfo> initPromoteVideos4Carousel() {
        return promoteVideosService.getPromoteVideos4Carousel();
    }

    /**
     * @return java.util.List<com.kaciry.entity.PromoteVideosBean>
     * @author kaciry
     * @description 获取列表区域的推荐视频
     * @date 2019/11/2 15:27
     **/
    @PostMapping(value = "/initPromoteVideos4List")
    @ResponseBody
    public List<VideoInfo> initPromoteVideos4List() {
        return promoteVideosService.getPromoteVideos4List();
    }

}
