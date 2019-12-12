package com.kaciry.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaciry.entity.*;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.utils.GetCookiesValueByKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author kaciry
 * @date 2019/10/25 17:37
 * @description 有关用户信息的Controller
 */
@Controller
public class UserInfoController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/changeInfo")
    @ResponseBody
    public User changeInfo(User user) {
        return userService.changeUserInfo(user);
    }

    /**
     * @param username 用户名
     * @return java.util.List<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 根据用户名查找所有用户投稿
     * @date 2019/10/31 22:16
     **/
    @PostMapping(value = "/selectUserVideos")
    @ResponseBody
    public List<VideoInfo> selectUserVideos(String token, String username) {
        return userService.selectVideosByUsername(username);
    }

    /**
     * @param file    用户上传的文件，头像
     * @param request HttpServletRequest,包含token和用户信息
     * @return boolean
     * @author kaciry
     * @description 用户修改个人头像
     * @date 2019/10/25 17:44
     **/
    @PostMapping(value = "modifyHeadIcon")
    @ResponseBody
    public boolean modifyHeadIcon(MultipartFile file, HttpServletRequest request) {
        String username = GetCookiesValueByKey.getValue(request, "username");
        try {
            //获取文件名
            String fileSuffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
            //Linux下上传目录
            //String filePathCover = "/www/wwwroot/www.kaciry.com/upload/HeadIcon/";
            //Windows下上传目录
            String filePath = "F:/upload/HeadIcon/";
            //创建文件
            File files = new File(filePath + username + fileSuffix);
            //上传文件
            file.transferTo(files);
            //创建文件路径
            String userHeadIconPathName = "/files/HeadIcon/" + username + fileSuffix;
            //修改数据库中头像路径
            return userService.updateUserHeadIcon(userHeadIconPathName, username);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @param email    用户邮箱
     * @param password 用户新密码
     * @return boolean
     * @author kaciry
     * @description 重置密码
     * @date 2019/12/9 16:41
     **/
    @PostMapping("/postUpdatePwd")
    @ResponseBody
    public boolean resetPassword(String email, String password) {
        return userService.updatePassword(email, password);
    }

    /**
     * @param username 用户名
     * @return com.kaciry.entity.user
     * @author kaciry
     * @description 查询个人信息
     * @date 2019/11/9 18:29
     **/
    @PostMapping(value = "/selectInfo")
    @ResponseBody
    public User selectInfo(String username) {
        return userService.selectUserInfoByUsername(username);
    }

    /**
     * @param username 用户名
     * @param pageNum  分页，当前页码
     * @param pageSize 分页，每一页的大小
     * @return com.github.pagehelper.PageInfo<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 查看我的投稿视频
     * @date 2019/11/9 18:12
     **/
    @PostMapping(value = "/selectMyVideo")
    @ResponseBody
    public PageInfo<VideoInfo> selectMyVideo(String username, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            PageHelper.startPage(1, 16);
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        return new PageInfo<>(userService.selectVideosByUsername(username));
    }

    /**
     * @param username 用户名
     * @param pageNum  分页，当前页码
     * @param pageSize 分页，每一页的大小
     * @return com.github.pagehelper.PageInfo<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 查询我收藏的视频
     * @date 2019/10/25 17:41
     **/
    @PostMapping("/postQueryCollect")
    @ResponseBody
    public PageInfo<VideoInfo> queryCollect(String username, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            PageHelper.startPage(1, 20);
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        return new PageInfo<>(userService.selectCollectionsByUsername(username));
    }

    /**
     * @param username 用户名
     * @param pageNum  分页，当前页码
     * @param pageSize 分页，每一页的大小
     * @return com.github.pagehelper.PageInfo<com.kaciry.entity.UnionFansBean>
     * @author kaciry
     * @description 查询我的关注
     * @date 2019/10/25 17:42
     **/
    @PostMapping(value = "/postQueryFollows")
    @ResponseBody
    public PageInfo<UnionFansBean> queryFollows(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UnionFansBean> list = userService.queryFollows1(username);
        return new PageInfo<>(list);
    }

    @PostMapping(value = "/followHim")
    @ResponseBody
    public ResultBean followOthers(String username, String hisUsername) {
        return userService.followOthers(username, hisUsername);
    }

    /**
     * @param reportCommentBean 举报评论的实体，包含信息见实体对象
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 举报视频的评论
     * @date 2019/10/25 17:43
     **/
    @PostMapping(value = "/reportComment")
    @ResponseBody
    public ResultBean reportComments(@RequestBody ReportCommentBean reportCommentBean) {
        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        reportCommentBean.setReportedTime(simpleDateFormat.format(new Date()));
        CommentBean commentBean = userService.queryCommentByIdentityDocument(reportCommentBean.getCommentIdentityDocument());
        reportCommentBean.setBeReportedUser(commentBean.getUsername());
        reportCommentBean.setCommentContent(commentBean.getContent());

        return userService.reportComment(reportCommentBean);
    }

    /**
     * @param username       用户名
     * @param originPassword 原始密码
     * @param password       新密码
     * @return com.kaciry.entity.ResultBean
     * @author kaciry
     * @description 修改密码
     * @date 2019/11/9 18:28
     **/
    @PostMapping(value = "/changePassword")
    @ResponseBody
    public ResultBean changePassword(String username, String originPassword, String password) {
        return userService.updateUserPassword(username, originPassword, password);
    }

}
