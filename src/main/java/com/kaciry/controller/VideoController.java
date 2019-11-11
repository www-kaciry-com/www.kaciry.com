package com.kaciry.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaciry.entity.*;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.service.Impl.VideoServiceImpl;
import com.kaciry.utils.AutoGetBarrages;
import com.kaciry.utils.GetAuthorization;
import com.kaciry.utils.GetCookiesValueByKey;
import com.kaciry.utils.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * @param file    用户上传的文件，视频封面与视频
     * @param request request请求
     * @return java.lang.String
     * @author kaciry
     * @description 用户投稿视频
     * @date 2019/10/25 17:48
     **/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean uploadFile(MultipartFile[] file, HttpServletRequest request) {
        String username = GetCookiesValueByKey.getValue(request, "username");
        if (GetAuthorization.isAuthorization(username, GetCookiesValueByKey.getValue(request, "Token"))) {
            UploadFiles uploadFiles = new UploadFiles();
            if (userService.uploadVideo((VideoInfo) uploadFiles.uploadFiles(file, request).getData())) {
                return new ResultBean<>("上传成功！success");
            } else {
                return new ResultBean<>("上传失败，请检查网络！error!");
            }
        } else {
            return new ResultBean<>("请勿非法操作！");
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
    public CommentBean setComment(String token, String username, String content, String videoAddress) {
        if (GetAuthorization.isAuthorization(username, token)) {
            CommentBean commentBean = new CommentBean(videoAddress, username, content, simpleDateFormat.format(new Date()), 0);
            return videoService.addComment(commentBean);
        } else {
            return null;
        }

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
    public PageInfo<CommentBean> selectVideoComment(String videoAddress, Integer pageNum, Integer pageSize) {
        //pageNum当前页面,  pageSize页面需要显示的数据条数
        PageHelper.startPage(pageNum, pageSize);
        List<CommentBean> commentBeanList = videoService.selectVideoCommentsByVideoFilename(videoAddress);
        return new PageInfo<>(commentBeanList);

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
    public VideoPage selectVideoComment(String token, String username, String videoAddress) {
        int barrages = AutoGetBarrages.getBarrages(videoAddress);
        VideoPage videoPage;
        if ((username == null) || ("".equals(username))) {
            videoPage = videoService.initVideoInfo(videoAddress);
            videoPage.setVideoBarrages(barrages);
            videoService.addVideoBarrages(videoAddress, barrages);
            return videoPage;
        } else {
            if (GetAuthorization.isAuthorization(username, token)) {
                videoPage = videoService.initVideoInfo(videoAddress, username);
                videoPage.setVideoBarrages(barrages);
                videoService.addVideoBarrages(videoAddress, barrages);
                return videoPage;
            } else {
                videoPage = videoService.initVideoInfo(videoAddress);
                videoPage.setVideoBarrages(barrages);
                videoService.addVideoBarrages(videoAddress, barrages);
                return videoPage;
            }
        }
    }

    /**
     * @param username      用户名
     * @param videoFileName 视频文件名
     * @param option        用户进行的操作
     * @return boolean
     * @author kaciry
     * @description 投币点赞收藏Controller
     * @date 2019/10/25 17:54
     **/
    @PostMapping(value = "/opsStar")
    @ResponseBody
    public boolean opsStar(String token, String username, String videoFileName, String option) {
        if (GetAuthorization.isAuthorization(username, token)) {
            boolean res = false;
            Ops ops = new Ops(username, videoFileName);
            //判断用户点击的是哪一个操作
            switch (option) {
                //点赞
                case "star": {
                    res = videoService.opsStar(ops);
                    break;
                }
                //收藏
                case "collect": {
                    res = videoService.opsCollect(ops);
                    break;
                }
                case "share": {
                    res = videoService.opsShare(ops);
                    break;
                }
                default:
            }
            videoService.deleteOpsData(ops);
            return res;
        } else {
            return false;
        }

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
    public VideoFollowPage getVideoUser(String token, String videoAddress, String username) {
        if (GetAuthorization.isAuthorization(username, token)) {
            String hisUsername = userService.selectVideoInfoByVideoFilename(videoAddress).getUsername();
            User user = userService.selectUserInfoByUsername(hisUsername);
            FansBean fansBean = userService.selectFollowsState(username, hisUsername);
            if (fansBean != null) {
                return new VideoFollowPage(
                        fansBean.getUserIdentityDocument(), fansBean.getFollowedUser(), fansBean.getFollowedDate(), user.getUsername(), user.getUserHeadIcon(), user.getUserSignature(), user.getUserNickName()
                );
            } else {
                return new VideoFollowPage("null", "null", "null", user.getUsername(), user.getUserHeadIcon(), user.getUserSignature(), user.getUserNickName());
            }
        } else {
            return null;
        }

    }

    /**
     * @param reportVideoBean 举报信息实体，详情见实体类
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 用户举报视频
     * @date 2019/10/25 17:57
     **/
    @PostMapping(value = "/reportVideo")
    @ResponseBody
    public ResultBean reportVideo(@RequestBody ReportVideoBean reportVideoBean, HttpServletRequest request) {
        if (GetAuthorization.isAuthorization(GetCookiesValueByKey.getValue(request, "username"), GetCookiesValueByKey.getValue(request, "Token"))) {
            reportVideoBean.setReportedTime(simpleDateFormat.format(new Date()));
            return videoService.addOneReportVideoData(reportVideoBean);
        } else {
            return new ResultBean<>("请勿非法操作！");
        }

    }

}
