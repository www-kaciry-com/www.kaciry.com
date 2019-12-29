package com.kaciry.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaciry.entity.*;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.service.Impl.VideoServiceImpl;
import com.kaciry.utils.AutoGetBarrages;
import com.kaciry.utils.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/10/25 17:48
 * @description 有关用户对视频的Controller
 */
@Controller
public class VideoController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private VideoServiceImpl videoService;
    /**
     * @description 设置日期格式
     * @date 2019/11/3 23:15
     **/
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @param videoAddress 视频文件名
     * @author kaciry
     * @description 访问视频页面时增加播放量
     * @date 2019/11/4 15:00
     **/
    @PostMapping(value = "/addVideoPlayNum")
    @ResponseBody
    public void addVideoPlayNum(String videoAddress) {
        videoService.addVideoPlayNumByVideoFilename(videoAddress);
    }

    /**
     * @param videoFile      视频文件
     * @param videoCoverFile 封面图片文件
     * @param videoInfo      视频相关信息
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 用户投稿视频，上传视频和封面文件至服务器
     * @date 2019/11/15 17:26
     **/
    @RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean uploadVideoFiles(@RequestParam("videoFile") MultipartFile videoFile, @RequestParam("videoCoverFile") MultipartFile videoCoverFile,
                                       @RequestParam("videoInfo") String videoInfo) {
        //json字符换转化为实体对象
        VideoInfoDO parseObject = JSONObject.parseObject(videoInfo, VideoInfoDO.class);
        UploadFiles uploadFiles = new UploadFiles();
        if (userService.uploadVideo((VideoInfoDO) uploadFiles.uploadFiles(videoFile, videoCoverFile, parseObject).getData())) {
            return new ResultBean<>("上传成功！");
        } else {
            return new ResultBean<>("上传失败，请检查网络！!");
        }
    }

    /**
     * @param username     用户名
     * @param content      评论内容
     * @param videoAddress 视频文件名
     * @return com.kaciry.entity.Comment
     * @author kaciry
     * @description 用户评论视频
     * @date 2019/10/25 17:52
     **/
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public boolean setComment(String username, String content, String videoAddress) {
        CommentDO commentDO = new CommentDO(videoAddress, username, content, simpleDateFormat.format(new Date()), 0);
        return videoService.addComment(commentDO);
    }

    /**
     * @param videoAddress 视频文件名
     * @param pageNum      分页，当前页码
     * @param pageSize     分页，每一页的大小
     * @return com.github.pagehelper.PageInfo<com.kaciry.entity.Comment>
     * @author kaciry
     * @description 获取视频评论信息
     * @date 2019/10/25 17:53
     **/
    @PostMapping(value = "/selectVideoComment")
    @ResponseBody
    public PageInfo<CommentDO> selectVideoComment(String videoAddress, Integer pageNum, Integer pageSize) {
        //pageNum当前页面,  pageSize页面需要显示的数据条数
        PageHelper.startPage(pageNum, pageSize);
        List<CommentDO> commentDOList = videoService.selectVideoCommentsByVideoFilename(videoAddress);
        return new PageInfo<>(commentDOList);

    }

    /**
     * @param username     用户名
     * @param videoAddress 视频文件名
     * @return com.kaciry.entity.VideoPage
     * @author kaciry
     * @description 初始化视频
     * @date 2019/10/25 17:54
     **/
    @PostMapping(value = "/initVideo")
    @ResponseBody
    public VideoInfoDTO initVideoData(String username, String videoAddress) {
        int barrages = AutoGetBarrages.getBarrages(videoAddress);
        VideoInfoDTO videoInfoDTO;
        if ((username == null) || ("".equals(username))) {
            videoInfoDTO = videoService.initVideoInfo(videoAddress);
            videoInfoDTO.setVideoBarrages(barrages);
            videoService.addVideoBarrages(videoAddress, barrages);
            return videoInfoDTO;
        } else {
            videoInfoDTO = videoService.initVideoInfo(videoAddress, username);
            videoInfoDTO.setVideoBarrages(barrages);
            videoService.addVideoBarrages(videoAddress, barrages);
            return videoInfoDTO;
        }
    }

    /**
     * @param username      用户名
     * @param videoFilename 视频文件名
     * @param option        用户进行的操作
     * @return boolean
     * @author kaciry
     * @description 投币点赞收藏Controller
     * @date 2019/10/25 17:54
     **/
    @PostMapping(value = "/opsStar")
    @ResponseBody
    public boolean videoOperations(String username, String videoFilename, String option) {
        boolean res = false;
        OperationsDO operationsDO = new OperationsDO(username, videoFilename);
        //判断用户点击的是哪一个操作
        switch (option) {
            //点赞
            case "star": {
                res = videoService.operationOfStar(operationsDO);
                break;
            }
            //收藏
            case "collect": {
                res = videoService.operationOfCollect(operationsDO);
                break;
            }
            case "share": {
                res = videoService.operationOfShare(operationsDO);
                break;
            }
            default:
        }
        videoService.deleteOpsData(operationsDO);
        return res;
    }

    /**
     * @param videoAddress 视频文件名
     * @param username     用户名
     * @return com.kaciry.entity.VideoFollowPage
     * @author kaciry
     * @description 获取视频作者信息
     * @date 2019/10/25 17:56
     **/
    @PostMapping(value = "/getVideoUser")
    @ResponseBody
    public VideoFollowPage getVideoUser(String videoAddress, String username) {
        String hisUsername = userService.selectVideoInfoByVideoFilename(videoAddress).getUsername();
        User user = userService.selectUserInfoByUsername(hisUsername);
        FansDO fansDO = userService.selectFollowsState(username, hisUsername);
        if (fansDO != null) {
            return new VideoFollowPage(
                    fansDO.getUserIdentityDocument(), fansDO.getFollowedUser(), fansDO.getFollowedDate(), user.getUsername(), user.getUserHeadIcon(), user.getUserSignature(), user.getUserNickName()
            );
        } else {
            return new VideoFollowPage("null", "null", "null", user.getUsername(), user.getUserHeadIcon(), user.getUserSignature(), user.getUserNickName());
        }
    }

    /**
     * @param reportVideoDO 举报信息实体，详情见实体类
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 用户举报视频
     * @date 2019/10/25 17:57
     **/
    @PostMapping(value = "/reportVideo")
    @ResponseBody
    public ResultBean reportVideo(@RequestBody ReportVideoDO reportVideoDO) {
        reportVideoDO.setReportedTime(simpleDateFormat.format(new Date()));
        return videoService.addReportVideoData(reportVideoDO);
    }

    /**
     * @param videoFilename 视频文件名
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 删除我的投稿
     * @date 2019/11/13 13:00
     **/
    @PostMapping(value = "/removeVideo")
    @ResponseBody
    public ResultBean deleteVideo(String videoFilename) {
        if (videoService.removeVideoByVideoFilename(videoFilename)) {
            return new ResultBean<>("删除成功！");
        } else {
            return new ResultBean<>("删除失败！");
        }
    }
}
