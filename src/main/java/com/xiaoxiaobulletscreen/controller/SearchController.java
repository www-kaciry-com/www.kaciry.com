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
    public PageInfo<VideoInfo> searchMsg(Integer pageNum,Integer pageSize,String keyword){
        List<VideoInfo> videoInfoList;

        PageHelper.startPage(pageNum, pageSize);

        videoInfoList = searchDataService.SearchKeyword(keyword);

        return new PageInfo<>(videoInfoList);
    }
}
