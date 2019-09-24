package com.xiaoxiaobulletscreen.service;

import com.xiaoxiaobulletscreen.entity.VideoInfo;

import java.util.List;

public interface IndexDataService {

    List<VideoInfo> SelectIndexDataByType(String VideoType, int length);



}
