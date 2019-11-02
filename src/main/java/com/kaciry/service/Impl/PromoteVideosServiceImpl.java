package com.kaciry.service.Impl;

import com.kaciry.Utils.InitPromoteVideos;
import com.kaciry.Utils.TimeUtils;
import com.kaciry.dao.PromoteVideosDao;
import com.kaciry.dao.UserDao;
import com.kaciry.entity.PromoteVideosBean;
import com.kaciry.entity.ResultBean;
import com.kaciry.entity.VideoInfo;
import com.kaciry.service.PromoteVideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/11/1 13:57
 * @description 推广视频实现类
 */
@Service
public class PromoteVideosServiceImpl implements PromoteVideosService {
    @Autowired
    private PromoteVideosDao promoteVideosDao;
    @Autowired
    private UserDao userDao;

    @Override
    public String queryWaitTime() {
        PromoteVideosBean promoteVideosBean = promoteVideosDao.selectPromoteVideo();
        String res = TimeUtils.getTimeDifference(promoteVideosBean.getSurplusDuration(), new Timestamp(System.currentTimeMillis()));
        if ("false".equals(res)) {
            return "您是排队的第一个，确认信息支付后即可推广!";
        } else {
            return res;
        }
    }

    @Override
    public ResultBean addPromoteVideo(PromoteVideosBean promoteVideosBean) {
        if (promoteVideosDao.selectVideoIsPromoted(promoteVideosBean) == null) {
            if (promoteVideosDao.addPromoteVideo(promoteVideosBean) &&
                    promoteVideosDao.setUserVideoState(promoteVideosBean.getVideoFilename(), promoteVideosBean.getPromoteType() + 1)) {
                return new ResultBean<>("预定推广成功！");
            } else return new ResultBean<>("预定推广失败，请重试！");
        } else {
            return new ResultBean<>("该视频已在推荐列表！");
        }
    }

    @Override
    public List<PromoteVideosBean> analysisDataIsOvertime() {
        return promoteVideosDao.selectPromotedVideos();
    }

    @Override
    public boolean setPromoteVideoTimeOver(String videoFilename) {
        //更改user_video表中的视频状态信息
        promoteVideosDao.setUserVideoState(videoFilename, 1);
        return promoteVideosDao.setPromoteVideoTimeOver(videoFilename);
    }

    @Override
    public List<VideoInfo> getPromoteVideos4Carousel() {
        List<PromoteVideosBean> res =  InitPromoteVideos.initPromoteVideos4Carousel(promoteVideosDao.selectPromotedVideos4Carousel());
        List<VideoInfo> resultList = new ArrayList<>();
        for (PromoteVideosBean re : res) {
            VideoInfo videoInfo = userDao.queryVideosByVideoFileName(re.getVideoFilename());
            resultList.add(videoInfo);
        }
        return resultList;
    }

    @Override
    public List<VideoInfo> getPromoteVideos4List() {
        List<PromoteVideosBean> res =  InitPromoteVideos.initPromoteVideos4List(promoteVideosDao.selectPromotedVideos4List());
        List<VideoInfo> resultList = new ArrayList<>();
        for (PromoteVideosBean re : res) {
            VideoInfo videoInfo = userDao.queryVideosByVideoFileName(re.getVideoFilename());
            resultList.add(videoInfo);
        }
        return resultList;
    }

    @Override
    public boolean setPromoteVideoDuration(String videoFilename,Timestamp timestamp) {
        return promoteVideosDao.setPromoteVideoDuration(videoFilename,timestamp);
    }
}
