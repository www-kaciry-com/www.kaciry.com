package com.kaciry.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaciry.entity.*;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.utils.GetAuthorization;
import com.kaciry.utils.GetCookiesValueByKey;
import com.kaciry.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    private final String PREFIX = "User/";

    @Autowired
    private UserServiceImpl userService;

    /**
     * @description 设置日期格式
     * @date 2019/11/3 23:15
     **/
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value = {"/homePage"})
    public String userInfo() {
        return PREFIX + "homePage";
    }

    @RequestMapping(value = {"/reply"})
    public String userReply() {
        return "reply";
    }

    @PostMapping(value = "/changeInfo")
    @ResponseBody
    public User changeInfo(User user) {
        return userService.changeInfo(user);
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
    public List<VideoInfo> selectUserVideos(String username) {
        if (GetAuthorization.isAuthorization(username)) {
            username = TokenUtils.checkToken(username);
            return userService.selectVideosByUsername(username);
        } else {
            return null;
        }
    }

    /**
     * @param file    用户上传的文件，头像
     * @param request request
     * @return boolean
     * @author kaciry
     * @description 用户修改个人头像
     * @date 2019/10/25 17:44
     **/
    @PostMapping(value = "/modifyHeadIcon")
    @ResponseBody
    public boolean modifyHeadIcon(MultipartFile file, HttpServletRequest request) {
        String username = GetCookiesValueByKey.getValue(request);
        if (GetAuthorization.isAuthorization(TokenUtils.getToken(username))) {
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
        } else {
            return false;
        }

    }

    @PostMapping("/postUpdatePwd")
    @ResponseBody
    public boolean updatePassword(String email, String password, HttpServletRequest request) {
        String username = GetCookiesValueByKey.getValue(request);
        if (GetAuthorization.isAuthorization(TokenUtils.getToken(username))) {
            return userService.updatePassword(email, password);
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/selectInfo", method = {RequestMethod.POST})
    @ResponseBody
    public User selectInfo(String username) {
        if (GetAuthorization.isAuthorization(username)) {
            username = TokenUtils.checkToken(username);
            return userService.selectUserInfoByUsername(username);
        } else {
            return null;
        }
    }

    /**
     * @param request  HttpServletRequest
     * @param pageNum  分页，当前页码
     * @param pageSize 分页，每一页的大小
     * @return com.github.pagehelper.PageInfo<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 查看我的投稿视频
     * @date 2019/10/25 17:37
     **/
    @PostMapping(value = "/selectMyVideo")
    @ResponseBody
    public PageInfo<VideoInfo> selectMyVideo(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        String token = GetCookiesValueByKey.getToken(request);
        if (GetAuthorization.isAuthorization(token)) {
            List<VideoInfo> videoInfoList;
            if (pageNum == null || pageSize == null) {
                PageHelper.startPage(1, 20);
            } else {
                PageHelper.startPage(pageNum, pageSize);
            }
            String username = TokenUtils.checkToken(token);
            videoInfoList = userService.selectVideosByUsername(username);
            return new PageInfo<>(videoInfoList);
        } else {
            return null;
        }
    }

    /**
     * @param request  HttpServletRequest
     * @param pageNum  分页，当前页码
     * @param pageSize 分页，每一页的大小
     * @return com.github.pagehelper.PageInfo<com.kaciry.entity.VideoInfo>
     * @author kaciry
     * @description 查询我收藏的视频
     * @date 2019/10/25 17:41
     **/
    @PostMapping("/postQueryCollect")
    @ResponseBody
    public PageInfo<VideoInfo> queryCollect(Integer pageNum, Integer pageSize, HttpServletRequest request) {
        String username = GetCookiesValueByKey.getValue(request);
        if (GetAuthorization.isAuthorization(TokenUtils.getToken(username))) {
            List<VideoInfo> videoInfoList;
            if (pageNum == null || pageSize == null) {
                PageHelper.startPage(1, 20);
            } else {
                PageHelper.startPage(pageNum, pageSize);
            }
            videoInfoList = userService.selectCollectionsByUsername(username);
            return new PageInfo<>(videoInfoList);
        } else {
            return null;
        }

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
        if (GetAuthorization.isAuthorization(username)) {
            username = TokenUtils.checkToken(username);
            PageHelper.startPage(pageNum, pageSize);
            List<UnionFansBean> list = userService.queryFollows1(username);
            return new PageInfo<>(list);
        } else {
            return null;
        }

    }

    @PostMapping(value = "/followHim")
    @ResponseBody
    public ResultBean followOthers(String username, String hisUsername) {
        if (GetAuthorization.isAuthorization(username)) {
            username = TokenUtils.checkToken(username);
            return userService.followOthers(username, hisUsername);
        } else {
            return null;
        }

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
    public ResultBean reportComments(@RequestBody ReportCommentBean reportCommentBean, HttpServletRequest request) {
        if (GetAuthorization.isAuthorization(TokenUtils.getToken(GetCookiesValueByKey.getValue(request)))) {
            reportCommentBean.setReportedTime(simpleDateFormat.format(new Date()));
            CommentBean commentBean = userService.queryCommentByIdentityDocument(reportCommentBean.getCommentIdentityDocument());
            reportCommentBean.setBeReportedUser(commentBean.getUsername());
            reportCommentBean.setCommentContent(commentBean.getContent());
            return userService.reportComment(reportCommentBean);
        } else {
            return null;
        }

    }

    @PostMapping(value = "/changePassword")
    @ResponseBody
    public ResultBean changePassword(String username, String originPassword, String password) {
        if (GetAuthorization.isAuthorization(username)) {
            return userService.updateUserPassword(TokenUtils.checkToken(username), originPassword, password);
        } else {
            return null;
        }

    }

}
