package com.xiaoxiaobulletscreen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiaobulletscreen.entity.VideoInfo;
import com.xiaoxiaobulletscreen.service.Impl.SearchDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    SearchDataServiceImpl searchDataService;

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @PostMapping(value = "/searchMsg")
    @ResponseBody
    public PageInfo<VideoInfo> searchMsg(Integer pageNum,Integer pageSize,String keyword,String type){
        List<VideoInfo> videoInfoList = null;

        PageHelper.startPage(pageNum, pageSize);
        if (type.equals("s")){
            videoInfoList = searchDataService.SearchKeyword(keyword);
        }else if (type.equals("n")){
            videoInfoList = searchDataService.SearchByType(keyword);
        }else {
            System.out.println("Search error!");
        }
        return new PageInfo<>(videoInfoList);
    }

//    @PostMapping(value = "searchByType")
//    @ResponseBody
//    public PageInfo<VideoInfo> searchByType(Integer pageNum,Integer pageSize,String keyword ){
//
//        List<VideoInfo> videoInfoList;
//
//        PageHelper.startPage(pageNum, pageSize);
//
//        videoInfoList = searchDataService.SearchByType(keyword);
//
//        return new PageInfo<>(videoInfoList);
//    }
}
