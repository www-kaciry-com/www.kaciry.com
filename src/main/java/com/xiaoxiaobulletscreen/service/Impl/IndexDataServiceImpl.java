package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.Utils.DataWeightSort;
import com.xiaoxiaobulletscreen.dao.IndexDataDao;
import com.xiaoxiaobulletscreen.entity.VideoInfo;
import com.xiaoxiaobulletscreen.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 初始化首页数据Service实现类
 */
@Service
public class IndexDataServiceImpl implements IndexDataService {
    @Autowired
    private IndexDataDao indexDataDao;

    @Override
    public List<VideoInfo> selectIndexDataByType(String videoType, int length) {
        List<VideoInfo> videoInfo = indexDataDao.selectVideoData(videoType);
        return DataWeightSort.dataWeightSort(videoInfo,length);

    }

    public List<VideoInfo> selectIndexDataByType(String videoType, int length, boolean rank) {
        return indexDataDao.selectVideoDataByType(videoType,length);
    }


}
