package com.kaciry.utils;

import com.kaciry.entity.PromoteVideosDO;

import java.util.List;

/**
 * @author kaciry
 * @date 2019/11/2 14:52
 * @description 初始化首页推荐视频数据
 */
public class InitPromoteVideos {
    private static final int PROMOTE_CAROUSEL_SIZE = 3;
    private static final int PROMOTE_LIST_SIZE = 6;

    public static List<PromoteVideosDO> initPromoteVideos4Carousel(List<PromoteVideosDO> list) {
        List<PromoteVideosDO> res;
        if (list.size() >= PROMOTE_CAROUSEL_SIZE) {
            res = list.subList(0, PROMOTE_CAROUSEL_SIZE);
            return res;
        }
        return list;
    }

    public static List<PromoteVideosDO> initPromoteVideos4List(List<PromoteVideosDO> list) {
        List<PromoteVideosDO> res;
        if (list.size() >= PROMOTE_LIST_SIZE) {
            res = list.subList(0, PROMOTE_LIST_SIZE);
            return res;
        }
        return list;
    }
}
