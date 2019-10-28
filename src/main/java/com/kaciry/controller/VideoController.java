package com.kaciry.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaciry.Utils.FormatVideoName;
import com.kaciry.entity.*;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.service.Impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    @RequestMapping(value = {"/video"})
    public String videoPlay() {
        return "video";
    }

    @RequestMapping(value = {"/contribute"})
    public String contribute() {
        return "upload";
    }

    /**
     * @param file    用户上传的文件，视频封面与视频
     * @param session session
     * @param req     request请求
     * @return java.lang.String
     * @author kaciry
     * @description 用户投稿视频
     * @date 2019/10/25 17:48
     **/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartFile[] file, HttpSession session, HttpServletRequest req) {
        String username = session.getAttribute("username").toString();
        String videoTitle = req.getParameter("videoTitle");
        String videoType = req.getParameter("videoType");
        String videoName1 = req.getParameter("videoName");
        String videoDescription = req.getParameter("videoDescription");

        boolean flag;
        try {
            //获取文件后缀，包括点“.”
            String fileSuffixVideo = Objects.requireNonNull(file[0].getOriginalFilename()).substring(file[0].getOriginalFilename().lastIndexOf("."));
            String fileSuffixCover = Objects.requireNonNull(file[1].getOriginalFilename()).substring(file[1].getOriginalFilename().lastIndexOf("."));
            //Linux下上传目录
//          String filePathVideo = "/www/wwwroot/www.kaciry.com/upload/video/";
//          String filePathCover = "/www/wwwroot/www.kaciry.com/upload/videoCover/";
            //Windows下上传目录
            String filePathVideo = "F:/upload/video/";
            String filePathCover = "F:/upload/videoCover/";
            //转化文件名
            String fileName = FormatVideoName.formatVideoName(UUID.randomUUID().toString());
            //创建文件对象
            File filesVideo = new File(filePathVideo + "av" + fileName + fileSuffixVideo);
            File filesCover = new File(filePathCover + "av" + fileName + fileSuffixCover);
            //上传文件
            file[0].transferTo(filesVideo);
            file[1].transferTo(filesCover);
            //保存用户上传的信息
//          flag = userService.uploadVideo(username,"AV" + fileName + fileSuffix);
            //视频文件名
            String videoName = "av" + fileName + fileSuffixVideo;
            //视频封面
            String videoCover = "av" + fileName + fileSuffixCover;
            //设置日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            VideoInfo videoInfo = new VideoInfo(username, videoTitle, videoType, 0, videoName,
                    videoDescription, videoName1, videoCover, simpleDateFormat.format(new Date()), 0, 0, 0, 0, 0, 0);
            flag = userService.uploadVideo(videoInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return "服务器开小差了！server error!";
        }
        if (flag) {
            return "上传成功！success";
        } else {
            return "上传失败，请检查网络！error!";
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
    public CommentBean setComment(String username, String content, String videoAddress) {
        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CommentBean commentBean = new CommentBean(videoAddress, username, content, simpleDateFormat.format(new Date()), 0);
        return videoService.addComment(commentBean);
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
    public VideoPage selectVideoComment(String username, String videoAddress) {
        if ((username == null) || ("".equals(username))) {
            return videoService.initVideoInfo(videoAddress);
        } else {
            return videoService.initVideoInfo(videoAddress, username);
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
    public boolean opsStar(String username, String videoFileName, String option) {
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
        FansBean fansBean = userService.selectFollowsState(username, hisUsername);
        if (fansBean != null) {
            return new VideoFollowPage(
                    fansBean.getUserIdentityDocument(), fansBean.getFollowedUser(), fansBean.getFollowedDate(), user.getUsername(), user.getUserHeadIcon(), user.getUserSignature(), user.getUserNickName()
            );
        } else {
            return new VideoFollowPage("null", "null", "null", user.getUsername(), user.getUserHeadIcon(), user.getUserSignature(), user.getUserNickName());
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
    public ResultBean reportVideo(@RequestBody ReportVideoBean reportVideoBean) {
        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        reportVideoBean.setReportedTime(simpleDateFormat.format(new Date()));
        return videoService.addOneReportVideoData(reportVideoBean);
    }

}
