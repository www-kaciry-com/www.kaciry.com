package com.kaciry.service.Impl;

import com.kaciry.mapper.VideoDao;
import com.kaciry.entity.*;
import com.kaciry.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author kaciry
 * @date 2019/10/26 13:05
 * @description 初始化首页数据Service
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public void addVideoPlayNumByVideoFilename(String videoFilename) {
        int res = videoDao.addVideoPlayNumByVideoFilename(videoFilename);
        if (res!=1) {
            System.out.println("addVideoPlayNumByVideoFilename : " + res);
            // TODO: 2019/11/4 出错打野日志
        }

    }

    @Override
    public CommentBean addComment(CommentBean commentBean) {
        if (!videoDao.addNewComment(commentBean)) {
            return null;
        } else {
            return commentBean;
        }
    }

    @Override
    public List<CommentBean> selectVideoCommentsByVideoFilename(String videoFileName) {
        return videoDao.selectVideoComment(videoFileName);
    }

    //登陆的用户查询对该视频是否进行过操作
    @Override
    public VideoPage initVideoInfo(String videoAddress, String username) {
        //1.查询该视频的所有信息
        VideoInfo resultOfVideoInfo = videoDao.initVideoInfo(videoAddress);
        Ops ops = new Ops(username, videoAddress);
        //2.查询username用户是否对该视频操作过
        Ops resultOfOps = videoDao.queryOpsState(ops);
        VideoPage videoPage;
        //3.1 若未进行任何操作，videoPage不包含Ops的信息,用于视频播放页面初始化时下方的按钮是否为激活状态
        if (resultOfOps == null) {
            videoPage = new VideoPage(resultOfVideoInfo.getVideoIdentityDocument(), resultOfVideoInfo.getUsername(), resultOfVideoInfo.getVideoTitle(),
                    resultOfVideoInfo.getVideoType(), resultOfVideoInfo.getVideoState(), resultOfVideoInfo.getVideoFilename(),
                    resultOfVideoInfo.getVideoDescription(), resultOfVideoInfo.getVideoName(),resultOfVideoInfo.getVideoCover(),resultOfVideoInfo.getVideoData(), resultOfVideoInfo.getVideoStars(),
                    resultOfVideoInfo.getVideoCoins(), resultOfVideoInfo.getVideoConnections(), resultOfVideoInfo.getVideoShares(), resultOfVideoInfo.getVideoPlayNum(),
                    resultOfVideoInfo.getVideoBarrages());
        }
        //3.2 若有进行点赞等操作，videoPage包含Ops实体信息,用于视频播放页面初始化时下方的按钮是否为激活状态
        else {
            videoPage = new VideoPage(resultOfVideoInfo.getVideoIdentityDocument(), resultOfVideoInfo.getUsername(), resultOfVideoInfo.getVideoTitle(),
                    resultOfVideoInfo.getVideoType(), resultOfVideoInfo.getVideoState(), resultOfVideoInfo.getVideoFilename(),
                    resultOfVideoInfo.getVideoDescription(), resultOfVideoInfo.getVideoName(),resultOfVideoInfo.getVideoCover(),resultOfVideoInfo.getVideoData(), resultOfVideoInfo.getVideoStars(),
                    resultOfVideoInfo.getVideoCoins(), resultOfVideoInfo.getVideoConnections(), resultOfVideoInfo.getVideoShares(), resultOfVideoInfo.getVideoPlayNum(),
                    resultOfVideoInfo.getVideoBarrages(), resultOfOps.getIsStar(), resultOfOps.getIsCollect(), resultOfOps.getIsCoin());
        }

        return videoPage;
    }

    //游客，查询该视频自有的属性
    public VideoPage initVideoInfo(String videoAddress) {
        //1.查询该视频的所有信息
        VideoInfo resultOfVideoInfo = videoDao.initVideoInfo(videoAddress);
        //2.将videoInfo的信息赋给VideoPage（多态，处理方便）
        VideoPage videoPage;
        videoPage = new VideoPage(resultOfVideoInfo.getVideoIdentityDocument(), resultOfVideoInfo.getUsername(), resultOfVideoInfo.getVideoTitle(),
                resultOfVideoInfo.getVideoType(), resultOfVideoInfo.getVideoState(), resultOfVideoInfo.getVideoFilename(),
                resultOfVideoInfo.getVideoDescription(), resultOfVideoInfo.getVideoName(),resultOfVideoInfo.getVideoCover(),resultOfVideoInfo.getVideoData(),  resultOfVideoInfo.getVideoStars(),
                resultOfVideoInfo.getVideoCoins(), resultOfVideoInfo.getVideoConnections(), resultOfVideoInfo.getVideoShares(), resultOfVideoInfo.getVideoPlayNum(),
                resultOfVideoInfo.getVideoBarrages());
        return videoPage;
    }

    @Override
    public boolean opsStar(Ops ops) {
        //1.1查询是否存在该用户对视频的操作，不存在时返回值为false
        if (videoDao.queryOpsData(ops) != null) {
            //1.2 查询该条数据的star为1还是0
            Ops opsState = videoDao.queryOpsState(ops);
            //1.3 star为0，表示没点过攒，这次操作为点赞
            if (opsState.getIsStar() == 0) {
                //1.3.1 改变star的数值为1
                ops.setIsStar(1);
                videoDao.changeStarState(ops);
                //1.3.2 更新视频的自有信息，点赞数量加一，其余不变
                videoDao.updateVideoStarAdd(ops.getVideoFilename());
                //1.3.3 返回true 表示点赞成功
                return true;
            }
            //1.4 star为1，表示点过攒，这次操作为取消点赞
            else {
                //1.4.1 改变star的数值为0
                ops.setIsStar(0);
                //1.4.2 执行更改
                videoDao.changeStarState(ops);
                //1.4.3 更新视频的自有信息，点赞数量减一，其余不变
                videoDao.updateVideoStarSub(ops.getVideoFilename());
                //1.4.4 返回false，表示取消点赞成功
                return false;
            }

        }
        //2.1 为null时 ， 表示表中不存在该条数据
        else {
            //2.2 初始化ops中的star项的数值为1
            ops.setIsStar(1);
            //2.3 向ops表中添加一条数据
            videoDao.addOpsData(ops);
            videoDao.updateVideoStarAdd(ops.getVideoFilename());
            //2.4 返回值为true，表示点赞成功
            return true;
        }

    }

    @Override
    public boolean opsCollect(Ops ops) {
        //1.1查询是否存在该用户对视频的操作，不存在时返回值为false
        if (videoDao.queryOpsData(ops) != null) {
            //1.2 查询该条数据的collect为1还是0
            Ops opsState = videoDao.queryOpsState(ops);
            //1.3 collect为0，表示没收藏过，这次操作为点赞
            if (opsState.getIsCollect() == 0) {
                //1.3.1 改变collect的数值为1
                ops.setIsCollect(1);
                videoDao.changeCollectState(ops);
                //1.3.2 更新视频的自有信息，收藏数量加一，其余不变
                videoDao.updateVideoCollectAdd(ops.getVideoFilename());
                //1.3.3 返回true 表示收藏成功
                return true;
            }
            //1.4 collect为1，表示已收藏，这次操作为取消收藏
            else {
                //1.4.1 改变star的数值为0
                ops.setIsCollect(0);
                //1.4.2 执行更改
                videoDao.changeCollectState(ops);
                //1.4.3 更新视频的自有信息，收藏数量减一，其余不变
                videoDao.updateVideoCollectSub(ops.getVideoFilename());
                //1.4.4 返回false，表示取消收藏成功
                return false;
            }

        }
        //2.1 为null时 ， 表示表中不存在该条数据
        else {
            //2.2 初始化ops中的star项的数值为1
            ops.setIsCollect(1);
            //2.3 向ops表中添加一条数据
            videoDao.addOpsData(ops);
            videoDao.updateVideoCollectAdd(ops.getVideoFilename());
            //2.4 返回值为true，表示点赞成功
            return true;
        }
    }

    //分享视频
    @Override
    public boolean opsShare(Ops ops) {
        if (videoDao.queryOpsData(ops) != null){
            ops.setIsShare(1);
            videoDao.changeShareData(ops);
        }else {
            ops.setIsShare(1);
            videoDao.addOpsData(ops);
        }
        // TODO: 2019/10/14 未解决恶意分享影响视频热度BUG
        videoDao.updateVideoShareAdd(ops.getVideoFilename());
        return true;
    }

    //检查数据项是否为空
    @Override
    public void deleteOpsData(Ops ops) {
        //定义布尔类型的变量来判断是否删除成功，用于打印日志以便日后调试
        boolean flag = false;
        //1.查询ops中对应的数据，username 和 videoFileName
        Ops opsState = videoDao.queryOpsState(ops);
        //1.1 若全部都为空
        if ((opsState.getIsStar() == 0) && (opsState.getIsCoin() == 0) && (opsState.getIsCollect() == 0)&& (opsState.getIsShare() == 0)) {
            //1.1.1 删除该条数据
            flag = videoDao.deleteOpsData(ops);
        }

        // TODO: 2019/9/11 打印日志，记录错误信息
//        if (!flag) {
//
//        }
    }

    @Override
    public ResultBean addOneReportVideoData(ReportVideoBean reportVideoBean) {
        if (videoDao.queryReportData(reportVideoBean)== null){
            if (videoDao.addReportVideoData(reportVideoBean)){
                return new ResultBean<>("举报成功，感谢您的支持！");
            }else return new ResultBean<>("举报失败，请稍后重试！");

        }else return new ResultBean<>("您已经举报过该视频，请勿重复操作！");

    }
}
