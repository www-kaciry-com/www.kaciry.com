package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.Utils.DataWeightSort;
import com.xiaoxiaobulletscreen.dao.IndexDataDao;
import com.xiaoxiaobulletscreen.entity.VideoInfo;
import com.xiaoxiaobulletscreen.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexDataServiceImpl implements IndexDataService {
    @Autowired
    private IndexDataDao indexDataDao;

    @Override
    public List<VideoInfo> SelectIndexDataByType(String VideoType,int length) {
        List<VideoInfo> videoInfo = indexDataDao.SelectVideoData(VideoType);

        return DataWeightSort.dataWeightSort(videoInfo,length);

    }

    public List<VideoInfo> SelectIndexDataByType(String VideoType,int length,boolean rank) {
        return indexDataDao.SelectVideoDataByType(VideoType,length);
    }


}
