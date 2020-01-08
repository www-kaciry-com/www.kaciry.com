package com.kaciry.controller;


import com.kaciry.entity.ColumnShow;
import com.kaciry.entity.ResultBean;
import com.kaciry.service.Impl.ColumnServiceImpl;
import com.kaciry.utils.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author FLLH
 * @date 2019/12/23 13:01
 * @description
 */
@Controller
public class ColumnController {
    @Autowired
    private ColumnServiceImpl columnService;

    @GetMapping(value = {"/column"})
    public String column() {
        return "column/column";
    }

    /**
     * @param columnImg 富文本框中的图片
     * @return java.lang.String
     * @author FLLH
     * @description 富文本框上传图片到服务器  指定文件夹
     * @date 2019/12/23 12:27
     **/
    @RequestMapping(value = "/uploadColumn", method = RequestMethod.POST)
    @ResponseBody
    public String uploadColumn(@RequestParam("files") MultipartFile columnImg) {
        //Music parseObject = JSONObject.parseObject(musicInfo, Music.class);
        UploadFiles uploadFiles = new UploadFiles();
        String columnImgSrc = uploadFiles.uploadColumnFiles(columnImg);
        return columnImgSrc;
    }

    /**
     * @param columnInfo 上传专栏
     * @return com.kaciry.entity.ResultBean
     * @author FLLH
     * @description
     * @date 2019/12/26 11:36
     **/
    @RequestMapping(value = "/submitColumn", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean submitColumn(@RequestParam("columnInfo") String columnInfo) {
        return columnService.setColumn(columnInfo);
    }

    /**
     * @param
     * @return java.util.List<com.kaciry.entity.ColumnShow>
     * @author FLLH
     * @description 查看专栏
     * @date 2020/1/7 11:20
     **/
    @RequestMapping(value = "/selectColumn", method = RequestMethod.POST)
    @ResponseBody
    public List<ColumnShow> queryColumn() {
        return columnService.findColumn();
    }

    @RequestMapping(value = "/selectTodayColumn", method = RequestMethod.POST)
    @ResponseBody
    public List<ColumnShow> queryTodayColumn() {
        return columnService.findTodayColumn();
    }

    @RequestMapping(value = "/selectThreeDaysColumn", method = RequestMethod.POST)
    @ResponseBody
    public List<ColumnShow> queryThreeDaysColumn() {
        return columnService.findThreeDaysColumn();
    }
}
