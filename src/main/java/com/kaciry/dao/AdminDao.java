package com.kaciry.dao;

import com.kaciry.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminDao {

    @Select("SELECT count(*) FROM admin where adminAccount = #{adminAccount} and password = #{password}")
    int selectAdminInfo(@Param("adminAccount") String adminAccount, @Param("password") String password);

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 查看用户信息
     * @date 2019/10/31 0:25
     **/
    @Select("SELECT * FROM user LIMIT #{star},#{pageSize}")
    List<User> selectUserInfo(@Param("star") Integer star, @Param("pageSize") Integer pageSize);

    /**
     * @param searchedUsername 用户输入的用户名（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 根据用户输入的用户名查询用户详细信息
     * @date 2019/10/31 0:34
     **/
    @Select("SELECT * FROM user WHERE username = #{searchedUsername}")
    List<User> selectCurrUserByUsername(@Param("searchedUsername") String searchedUsername);

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.BannedVideo>
     * @author FLLH
     * @description 查看视频举报
     * @date 2019/10/30 23:43
     **/
    @Select("SELECT * FROM reportvideo LEFT JOIN user_video ON reportvideo.videoFilename = user_video.videoFilename LIMIT #{star},#{pageSize}")
    List<ReportVideoDO> selectVideoReport(@Param("star") Integer star, @Param("pageSize") Integer pageSize);

    /**
     * @param star     从第几条数据开始查询
     * @param pageSize 页面数据条数
     * @return java.util.List<com.kaciry.entity.BannedUser>
     * @author FLLH
     * @description 查看评论举报
     * @date 2019/10/30 23:49
     **/
    @Select("SELECT * FROM report_comments LEFT JOIN COMMENT ON report_comments.commentIdentityDocument = COMMENT .commentIdentityDocument WHERE COMMENT.state = 3 LIMIT #{star},#{pageSize}")
    List<ReportCommentDO> selectCommentReport(@Param("star") Integer star, @Param("pageSize") Integer pageSize);

    /**
     * @param reportVideoId 用户输入的举报编号（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.BannedVideo>
     * @author FLLH
     * @description 根据用户输入的举报编号查询视频举报详细信息
     * @date 2019/10/30 11:44
     **/
    @Select("SELECT * FROM reportvideo WHERE reportVideoIdentityDocument = #{reportVideoId}")
    List<ReportVideoDO> searchVideoReportDetailsInfoByReportVideoId(@Param("reportVideoId") int reportVideoId);

    /**
     * @param reportCommentId 用户输入的举报编号（搜索框中的value值）
     * @return java.util.List<com.kaciry.entity.BannedUser>
     * @author FLLH
     * @description 根据用户输入的举报编号查询评论举报详细信息
     * @date 2019/10/30 11:58
     **/
    @Select("SELECT * FROM report_comments WHERE commentIdentityDocument = #{reportCommentId}")
    List<ReportCommentDO> searchCommentReportDetailsInfoByReportCommentId(@Param("reportCommentId") int reportCommentId);

    /**
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 根据isVip分组计数绘制饼状图
     * @date 2019/11/11 19:34
     **/
    @Select("SELECT isvip ,count(*) num FROM user GROUP BY isVip;")
    List<User> selectUserInfoForEcharts();

    /**
     * @return java.util.List<com.kaciry.entity.User>
     * @author FLLH
     * @description 各类视频种类数量 柱状图
     * @date 2019/11/11 19:34
     **/
    @Select("SELECT videoType ,count(*) videoIdentityDocument FROM user_video GROUP BY videoType;")
    List<VideoInfoDO> selectVideoInfoForEcharts();

    @Select("SELECT videoType,SUM(videoStars) as videoStars,SUM(videoCoins) as videoCoins,SUM(videoConnections) as videoConnections,SUM(videoShares) as videoShares,SUM(videoPlayNum) as videoPlayNum,SUM(videoBarrages) as videoBarrages FROM user_video GROUP BY videoType;")
    List<VideoInfoDO> selectVideoInfoDataForEcharts();

    @Select("SELECT COUNT(*) AS num,userAddress FROM user_ip GROUP  BY userAddress;")
    List<UserIp> selectUserIpInfoDataForEcharts();

    /**
     * @param videoFilename 视频Id 表（user_video）
     * @return int
     * @author FLLH
     * @description 将选中的视频封禁
     * @date 2019/11/11 19:36
     **/
    @Update("UPDATE user_video SET videoState = 2 WHERE videoFilename = #{videoFilename};")
    int updateVideoState(@Param("videoFilename") String videoFilename);

    @Update("UPDATE comment SET state = 2 WHERE commentIdentityDocument = #{commentIdentityDocument};")
    int updateCommentState(@Param("commentIdentityDocument") int commentIdentityDocument);

    @Update("UPDATE user_video SET videoState = 1 WHERE videoIdentityDocument = #{videoIdentityDocument};")
    int updateVideoStateToPass(@Param("videoIdentityDocument") int videoIdentityDocument);

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 查询举报视频数据信息数量
     * @date 2019/11/18 20:25
     **/
    @Select("SELECT count(*) FROM reportvideo;")
    int selectVideoReportNum();

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 查询举报评论数据信息数量
     * @date 2019/11/18 20:25
     **/
    @Select("SELECT count(*) FROM report_comments;")
    int selectCommentReportNum();

    /**
     * @param
     * @return int
     * @author FLLH
     * @description 查询举报评论数据信息数量
     * @date 2019/11/18 20:25
     **/
    @Select("SELECT count(*) FROM user;")
    int selectUserInfoNum();

    @Select("SELECT * FROM user_video LIMIT #{star},#{pageSize}")
    List<VideoInfoDO> selectVideoInfo(@Param("star") Integer star, @Param("pageSize") Integer pageSize);

    @Select("SELECT count(*) FROM user_video;")
    int selectVideoNum();

    @Select("SELECT * FROM user_video WHERE username = #{username}")
    List<VideoInfoDO> selectUserVideos(@Param("username") String username);

    @Select("SELECT * FROM user_music LIMIT #{star},#{pageSize}")
    List<Music> selectMusicInfo(@Param("star") Integer star, @Param("pageSize") Integer pageSize);

    @Select("SELECT count(*) FROM user_music;")
    int selectMusicNum();

    @Select("SELECT * FROM user_music WHERE username = #{username}")
    List<Music> searchUserMusics(@Param("username") String username);

    @Select("SELECT * FROM user_video WHERE videoState = 0 LIMIT #{star},#{pageSize}")
    List<VideoInfoDO> selectUncheckVideoInfo(@Param("star") Integer star, @Param("pageSize") Integer pageSize);

    @Select("SELECT count(*) FROM user_video WHERE videoState = 0")
    int selectUncheckVideoNum();

    @Select("SELECT COUNT(*) num FROM user")
    int selectUserTotal();

    @Select("SELECT COUNT(*) num FROM user WHERE isVip != '未开通'")
    int selectMemberTotal();

    @Select("SELECT COUNT(*) num FROM user_video")
    int selectVideoTotal();

    @Select("SELECT COUNT(*) num FROM user_music")
    int selectMusicTotal();

}
