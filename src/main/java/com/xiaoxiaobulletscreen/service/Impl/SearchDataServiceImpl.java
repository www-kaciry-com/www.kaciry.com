package com.xiaoxiaobulletscreen.service.Impl;

import com.xiaoxiaobulletscreen.dao.IndexDataDao;
import com.xiaoxiaobulletscreen.entity.VideoInfo;
import com.xiaoxiaobulletscreen.service.SearchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchDataServiceImpl implements SearchDataService {
    @Autowired
    private IndexDataDao indexDataDao;

    @Override
    public List<VideoInfo> SearchKeyword(String keyword) {
        List<VideoInfo> res = indexDataDao.FuzzySearch(keyword);

            return res;
    }
}
