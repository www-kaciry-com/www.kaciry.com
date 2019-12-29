package com.kaciry.service.Impl;

import com.kaciry.entity.VideoInfoDO;
import com.kaciry.utils.DataWeightSort;
import com.kaciry.dao.IndexDataDao;
import com.kaciry.service.IndexDataService;
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
    public List<VideoInfoDO> selectIndexDataByType(String videoType, int length) {
        List<VideoInfoDO> videoInfoDO = indexDataDao.selectVideoData(videoType);
        if (videoInfoDO.size() < length) {
            return videoInfoDO;
        }else {
            return DataWeightSort.dataWeightSort(videoInfoDO, length);
        }
    }

    public List<VideoInfoDO> selectIndexDataByType(String videoType, int length, boolean rank) {
        return indexDataDao.selectVideoDataByType(videoType, length);
    }

    @Override
    public List<VideoInfoDO> countVideoType() {
        return indexDataDao.selectVideoNum();
    }
}
