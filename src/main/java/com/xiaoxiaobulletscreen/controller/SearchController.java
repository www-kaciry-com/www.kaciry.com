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

/**
 * @author kaciry
 * @date 2019/10/25 17:26
 * @description 有关用户搜索项的Controller
 */
@Controller
public class SearchController {

    @Autowired
    private SearchDataServiceImpl searchDataService;

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    /**
     * @param pageNum  分页，当前页码
     * @param pageSize 分页，每一页的大小
     * @param keyword  搜索的关键词
     * @param type     搜索的类型
     * @return com.github.pagehelper.PageInfo<com.xiaoxiaobulletscreen.entity.VideoInfo>
     * @author kaciry
     * @description 主页次导航
     * @date 2019/10/25 17:26
     **/
    @PostMapping(value = "/searchMsg")
    @ResponseBody
    public PageInfo<VideoInfo> searchMsg(Integer pageNum, Integer pageSize, String keyword, String type) {
        List<VideoInfo> videoInfoList = null;

        PageHelper.startPage(pageNum, pageSize);
        if ("s".equals(type)) {
            videoInfoList = searchDataService.searchKeyword(keyword);
        } else if ("n".equals(type)) {
            videoInfoList = searchDataService.searchByType(keyword);
        } else {
            System.out.println("Search error!");
        }
        return new PageInfo<>(videoInfoList);
    }

}
