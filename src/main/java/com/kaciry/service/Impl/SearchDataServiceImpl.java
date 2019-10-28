package com.kaciry.service.Impl;

import com.kaciry.dao.IndexDataDao;
import com.kaciry.entity.VideoInfo;
import com.kaciry.service.SearchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 搜索数据Service实现类
 */
@Service
public class SearchDataServiceImpl implements SearchDataService {
    @Autowired
    private IndexDataDao indexDataDao;

    @Override
    public List<VideoInfo> searchKeyword(String keyword) {
            return indexDataDao.fuzzySearch(keyword);
    }

    @Override
    public List<VideoInfo> searchByType(String keyword) {
        return indexDataDao.selectVideoData(keyword);
    }
}
