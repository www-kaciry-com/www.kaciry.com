package com.xiaoxiaobulletscreen.service;

import com.xiaoxiaobulletscreen.entity.VideoInfo;

import java.util.List;

public interface SearchDataService {

    List<VideoInfo> SearchKeyword(String keyword);

    List<VideoInfo> SearchByType(String keyword);

}
