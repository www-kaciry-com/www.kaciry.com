package com.kaciry.service.Impl;

import com.kaciry.utils.InitPromoteVideos;
import com.kaciry.utils.TimeUtils;
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
    public List<VideoInfo> selectNormalVideos(String username) {
        return promoteVideosDao.selectNormalVideos(username);
    }

    @Override
    public String queryWaitTime(int option) {
        List<PromoteVideosBean> list;
        //定义最少推荐视频量
        int index;
        //判断目标为哪一类推广
        if (option == 1) {
            //轮播图，最小数量为3
            int CAROUSEL_LIMIT_NUM = 3;
            index = CAROUSEL_LIMIT_NUM;
            //查询promoteType为1的最后3条数据
            list = promoteVideosDao.selectPromoteVideo(option, CAROUSEL_LIMIT_NUM);
        } else {
            //列表区域推广，最小数量为6
            int LIST_LIMIT_NUM = 6;
            index = LIST_LIMIT_NUM;
            //查询promoteType为1的最后6条数据
            list = promoteVideosDao.selectPromoteVideo(option, LIST_LIMIT_NUM);
        }
        //如果数量小于3个，直接进行推荐
        if (list.size() < index) {
            return "您是排队的第一个，确认信息支付后即可推广!";
        } else {
            //数量大于3个，计算最先失效的视频距离现在所剩余的时间并返回
            String res = TimeUtils.getTimeDifference(list.get(list.size() - 1).getSurplusDuration(), new Timestamp(System.currentTimeMillis()));
            if ("false".equals(res)) {
                return "您是排队的第一个，确认信息支付后即可推广!";
            } else {
                return res;
            }
        }
    }

    @Override
    public Timestamp selectLastTime(int option) {
        List<PromoteVideosBean> list;
        //定义最少推荐视频量
        int index;
        //判断目标为哪一类推广
        if (option == 1) {
            index = 3;
            list = promoteVideosDao.selectPromoteVideo(option, 3);
        } else {
            index = 6;
            list = promoteVideosDao.selectPromoteVideo(option, 6);
        }
        //如果数量小于3个，直接进行推荐
        if (list.size() < index) {
            return new Timestamp(System.currentTimeMillis());
        } else {
            return new Timestamp(list.get(list.size() - 1).getSurplusDuration().getTime());
        }
    }

    @Override
    public ResultBean addPromoteVideo(PromoteVideosBean promoteVideosBean) {
        //查询该类型的推广是否存在，若不存在
        if (promoteVideosDao.selectVideoIsPromoted(promoteVideosBean) == null) {
            //向promote_videos添加一条信息并更改user_video表中的视频状态(promoteType与videoState相差一，因为videoState的1需要表示正常状态)
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
        List<PromoteVideosBean> res = InitPromoteVideos.initPromoteVideos4Carousel(promoteVideosDao.selectPromotedVideos4Carousel());
        List<VideoInfo> resultList = new ArrayList<>();
        for (PromoteVideosBean re : res) {
            VideoInfo videoInfo = userDao.queryVideosByVideoFileName(re.getVideoFilename());
            resultList.add(videoInfo);
        }
        return resultList;
    }

    @Override
    public List<VideoInfo> getPromoteVideos4List() {
        List<PromoteVideosBean> res = InitPromoteVideos.initPromoteVideos4List(promoteVideosDao.selectPromotedVideos4List());
        List<VideoInfo> resultList = new ArrayList<>();
        for (PromoteVideosBean re : res) {
            VideoInfo videoInfo = userDao.queryVideosByVideoFileName(re.getVideoFilename());
            resultList.add(videoInfo);
        }
        return resultList;
    }

    @Override
    public boolean setPromoteVideoDuration(String videoFilename, Timestamp timestamp) {
        return promoteVideosDao.setPromoteVideoDuration(videoFilename, timestamp);
    }
}
