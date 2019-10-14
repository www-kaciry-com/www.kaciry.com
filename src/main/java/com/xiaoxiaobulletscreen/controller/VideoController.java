package com.xiaoxiaobulletscreen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiaobulletscreen.Utils.FormatVideoName;
import com.xiaoxiaobulletscreen.entity.*;
import com.xiaoxiaobulletscreen.service.Impl.UserServiceImpl;
import com.xiaoxiaobulletscreen.service.Impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    //投稿视频Controller
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartFile[] file, HttpSession session, HttpServletRequest req) {
        String username = session.getAttribute("username").toString();
        String video_title = req.getParameter("videoTitle");
        String video_type = req.getParameter("videoType");
        String video_name = req.getParameter("videoName");
        String video_description = req.getParameter("videoDescription");

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
            // System.out.println(fileSuffix);
            //转化文件名
            String fileName = FormatVideoName.formatVideoName(UUID.randomUUID().toString());
            //创建文件对象
            File filesVideo = new File(filePathVideo + "av" + fileName + fileSuffixVideo);
            File filesCover = new File(filePathCover + "av" + fileName + fileSuffixCover);
            //上传文件
            file[0].transferTo(filesVideo);
            file[1].transferTo(filesCover);
            //保存用户上传的信息
//            flag = userService.uploadVideo(username,"AV" + fileName + fileSuffix);
            //视频文件名
            String videoName = "av" + fileName + fileSuffixVideo;
            //视频封面
            String videoCover = "av" + fileName + fileSuffixCover;
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            VideoInfo videoInfo = new VideoInfo(username, video_title, video_type, 0, videoName,
                    video_description, video_name, videoCover, df.format(new Date()), 0, 0, 0, 0, 0, 0);
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

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Comment setComment(String username, String content, String videoAddress) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        User userInfo = userService.selectInfo(username);
        Comment comment = new Comment(videoAddress, username, content, df.format(new Date()), userInfo.getUserHeadIcon(), userInfo.getUserNickName(), 0);
        Comment result = videoService.addComment(comment);
        return result;
    }

    @PostMapping(value = "/selectVideoComment")
    @ResponseBody
    public PageInfo<Comment> selectVideoComment(String videoAddress, Integer pageNum, Integer pageSize) {
        //pageNum当前页面,  pageSize页面需要显示的数据条数
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = videoService.selectVideoComment(videoAddress);
        return new PageInfo<>(commentList);

    }

    @PostMapping(value = "/initVideo")
    @ResponseBody
    public VideoPage selectVideoComment(String username, String videoAddress) {
        if ((username == null) || (username.equals(""))) {
            return videoService.initVideoInfo(videoAddress);
        } else {
            return videoService.initVideoInfo(videoAddress, username);
        }
    }

    //投币点赞收藏Controller
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
            }
        }
        videoService.deleteOpsData(ops);
        return res;

    }

    //获取视频作者信息
    @PostMapping(value = "/getVideoUser")
    @ResponseBody
    public VideoFollowPage getVideoUser(String videoAddress,String username) {
        String hisUsername = userService.queryVideoInfo(videoAddress).getUsername();
        User user =  userService.selectInfo(hisUsername);
        FansBean fansBean = userService.queryFollowsState(username,hisUsername);
        if (fansBean != null){
            return new VideoFollowPage(
                    fansBean.getUserID(),fansBean.getFollowedUser(),fansBean.getFollowedDate(),user.getUsername(),user.getUserHeadIcon(),user.getUserSignature(),user.getUserNickName()
            );
        }else {
            return new VideoFollowPage("null","null","null",user.getUsername(),user.getUserHeadIcon(),user.getUserSignature(),user.getUserNickName());
        }

    }



}
