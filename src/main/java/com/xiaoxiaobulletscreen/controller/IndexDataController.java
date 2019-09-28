package com.xiaoxiaobulletscreen.controller;

import com.xiaoxiaobulletscreen.entity.VideoInfo;
import com.xiaoxiaobulletscreen.service.Impl.IndexDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexDataController {
    @Autowired
    private IndexDataServiceImpl indexDataService;

    @PostMapping(value = "/indexDataInit")
    @ResponseBody
    public List<VideoInfo> indexDataInit(String videoType , int length){
//       int  Length = Integer.parseInt(length);
        List<VideoInfo> result = indexDataService.SelectIndexDataByType(videoType,length);
//        System.out.println(result.toString());
        return result;
    }

    //首页播放排行榜Controller
    @PostMapping(value = "/playRank")
    @ResponseBody
    public List<VideoInfo> indexPlayRankInit(String videoType,int length){

        return indexDataService.SelectIndexDataByType(videoType,length,true);
    }

}
