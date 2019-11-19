package com.kaciry.utils;

import com.kaciry.entity.ResultBean;
import com.kaciry.entity.VideoInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author kaciry
 * @date 2019/11/10 14:02
 * @description 上传文件工具类
 */
public class UploadFiles {
    //    public ResultBean uploadFiles(MultipartFile[] file, HttpServletRequest request) {
//        String username = GetCookiesValueByKey.getValue(request, "username");
//        VideoInfo videoInfo;
//        String videoTitle = request.getParameter("videoTitle");
//        String videoType = request.getParameter("videoType");
//        String videoName = request.getParameter("videoName");
//        String videoDescription = request.getParameter("videoDescription");
//        try {
//            //获取文件后缀，包括点“.”
//            String fileSuffixVideo = Objects.requireNonNull(file[0].getOriginalFilename()).substring(file[0].getOriginalFilename().lastIndexOf("."));
//            String fileSuffixCover = Objects.requireNonNull(file[1].getOriginalFilename()).substring(file[1].getOriginalFilename().lastIndexOf("."));
//            //Linux下上传目录
//            //String filePathVideo = "/www/wwwroot/www.kaciry.com/upload/video/";
//            //String filePathCover = "/www/wwwroot/www.kaciry.com/upload/videoCover/";
//            //Windows下上传目录
//            String filePathVideo = "F:/upload/video/";
//            String filePathCover = "F:/upload/temp/";
//            //转化文件名
//            String fileName = FormatVideoName.getTargetFileName();
//            //创建文件对象
//            File filesVideo = new File(filePathVideo + "av" + fileName + fileSuffixVideo);
//            File filesCover = new File(filePathCover + "av" + fileName + fileSuffixCover);
//            //文件全名
//            String fileFullName = "F:\\upload\\temp\\" + "av" + fileName + fileSuffixCover;
//            //上传文件
//            file[0].transferTo(filesVideo);
//            file[1].transferTo(filesCover);
//            //压缩图片并将图片转移到videoCover目录下
//            ManageFiles manageFiles = new ManageFiles();
//            manageFiles.compressWithDimension(fileFullName, "F:\\upload\\videoCover\\" + "av" + fileName + fileSuffixCover);
//            //删除源文件
//            if (!manageFiles.deleteOriginFile(fileFullName)) {
//                return new ResultBean<>("服务器开小差了！");
//            }
//            //视频文件名
//            String videoFilename = "av" + fileName + fileSuffixVideo;
//            //视频封面
//            String videoCover = "av" + fileName + fileSuffixCover;
//            //设置日期格式
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            //生成实体
//            videoInfo = new VideoInfo(username, videoTitle, videoType, 0, videoFilename,
//                    videoDescription, videoName, videoCover, simpleDateFormat.format(new Date()),
//                    0, 0, 0, 0, 0, 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResultBean<>("服务器开小差了！");
//        }
//        return new ResultBean<>(videoInfo);
//    }
    public ResultBean uploadFiles(MultipartFile videoFile, MultipartFile videoCoverFile, VideoInfo videoInfo) {
        try {
            //获取文件后缀，包括点“.”
            String fileSuffixVideo = Objects.requireNonNull(videoFile.getOriginalFilename()).substring(videoFile.getOriginalFilename().lastIndexOf("."));
            String fileSuffixCover = Objects.requireNonNull(videoCoverFile.getOriginalFilename()).substring(videoCoverFile.getOriginalFilename().lastIndexOf("."));
            //Linux下上传目录
//            String filePathVideo = "/www/wwwroot/www.kaciry.com/upload/video/";
//            String filePathCover = "/www/wwwroot/www.kaciry.com/upload/temp/";
            //Windows下上传目录
            String filePathVideo = "F:/upload/video/";
            String filePathCover = "F:/upload/temp/";
            //转化文件名
            String fileName = FormatVideoName.getTargetFileName();
            //创建文件对象
            File filesVideo = new File(filePathVideo + "av" + fileName + fileSuffixVideo);
            File filesCover = new File(filePathCover + "av" + fileName + fileSuffixCover);
            //文件全名
//            String fileFullName = "/www/wwwroot/www.kaciry.com/upload/temp/" + "av" + fileName + fileSuffixCover;
            String fileFullName = "F:\\upload\\temp\\" + "av" + fileName + fileSuffixCover;
            //上传文件
            videoFile.transferTo(filesVideo);
            videoCoverFile.transferTo(filesCover);
            //压缩图片并将图片转移到videoCover目录下
            ManageFiles manageFiles = new ManageFiles();
            //Linux环境下文件目录
//            manageFiles.compressWithDimension(fileFullName, "/www/wwwroot/www.kaciry.com/upload/videoCover/" + "av" + fileName + fileSuffixCover);
            //Windows环境下目录
            manageFiles.compressWithDimension(fileFullName, "F:\\upload\\videoCover\\" + "av" + fileName + fileSuffixCover);
            //删除源文件
            if (!manageFiles.deleteOriginFile(fileFullName)) {
                return new ResultBean<>("服务器开小差了！");
            }
            //视频文件名
            String videoFilename = "av" + fileName + fileSuffixVideo;
            //视频封面
            String videoCover = "av" + fileName + fileSuffixCover;
            //设置日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //生成实体
            videoInfo.setVideoData(simpleDateFormat.format(new Date()));
            videoInfo.setVideoCover(videoCover);
            videoInfo.setVideoFilename(videoFilename);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean<>("服务器开小差了！");
        }
        return new ResultBean<>(videoInfo);
    }
}
