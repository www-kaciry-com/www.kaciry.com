package com.kaciry.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaciry.entity.VideoInfoDO;
import com.kaciry.service.Impl.SearchDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * @param pageNum  分页，当前页码
     * @param pageSize 分页，每一页的大小
     * @param keyword  搜索的关键词
     * @param type     搜索的类型
     * @return com.github.pagehelper.PageInfo<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 主页次导航
     * @date 2019/10/25 17:26
     **/
    @PostMapping(value = "/searchMsg")
    @ResponseBody
    public PageInfo<VideoInfoDO> searchMessage(Integer pageNum, Integer pageSize, String keyword, String type) {
        List<VideoInfoDO> videoInfoDOList = null;
        PageHelper.startPage(pageNum, pageSize);
        if ("s".equals(type)) {
            videoInfoDOList = searchDataService.searchKeyword(keyword);
        } else if ("n".equals(type)) {
            videoInfoDOList = searchDataService.searchByType(keyword);
        } else {
            System.out.println("Search error!");
        }
        return new PageInfo<>(videoInfoDOList);
    }

}
