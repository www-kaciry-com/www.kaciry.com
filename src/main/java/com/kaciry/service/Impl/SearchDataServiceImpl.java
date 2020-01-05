package com.kaciry.service.Impl;

import com.kaciry.dao.IndexDataDao;
import com.kaciry.entity.User;
import com.kaciry.entity.VideoInfoDO;
import com.kaciry.service.SearchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<VideoInfoDO> searchKeyword(String keyword) {
        return indexDataDao.selectFuzzySearch(keyword);
    }

    @Override
    public List<VideoInfoDO> searchByType(String keyword) {
        return indexDataDao.selectVideoData(keyword);
    }

    @Override
    public List<User> searchUserByUsername(String keyword) {
        List<User> userList1 = indexDataDao.selectUserByUsername(keyword);
        List<User> userList2 = indexDataDao.selectUserByUserNickname(keyword);
        userList1.addAll(userList2);
        //去除重复用户名,重写bean对象的equals和hashCode方法，然后通过放入Set集合来自动去重，
        Set<User> userSet = new HashSet<>(userList1);
        return new ArrayList<>(userSet);
    }
}
