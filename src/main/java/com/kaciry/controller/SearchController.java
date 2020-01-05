package com.kaciry.controller;

import com.github.pagehelper.PageHelper;
import com.kaciry.constant.ConstantClassField;
import com.kaciry.entity.ResultBean;
import com.kaciry.entity.User;
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
    public ResultBean searchMessage(Integer pageNum, Integer pageSize, String keyword, String type) {
        List<VideoInfoDO> videoInfoDOList = null;
        PageHelper.startPage(pageNum, pageSize);
        switch (type) {
            case ConstantClassField.SEARCH_KEYWORD: {
                videoInfoDOList = searchDataService.searchKeyword(keyword);
                break;
            }
            case ConstantClassField.SEARCH_NAVIGATION: {
                videoInfoDOList = searchDataService.searchByType(keyword);
                break;
            }
            case ConstantClassField.SEARCH_USER: {
                List<User> userList = searchDataService.searchUserByUsername(keyword);
                return new ResultBean<>(userList);
            }

        }
        return new ResultBean<>(videoInfoDOList);
    }

}
