package com.kaciry.service.Impl;

import com.kaciry.dao.PromoteVideosDao;
import com.kaciry.dao.VideoDao;
import com.kaciry.entity.*;
import com.kaciry.service.VideoService;
import com.kaciry.utils.ManageFilesUtil;
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

    @Autowired
    private PromoteVideosDao promoteVideosDao;

    @Override
    public void addVideoPlayNumByVideoFilename(String videoFilename) {
        int res = videoDao.addVideoPlayNumByVideoFilename(videoFilename);
        if (res != 1) {
            System.out.println("addVideoPlayNumByVideoFilename : " + res);
            // TODO: 2019/11/4 出错打野日志
        }

    }

    @Override
    public boolean addComment(CommentDO commentDO) {
        return videoDao.insertComment(commentDO);
    }

    @Override
    public List<CommentDO> selectVideoCommentsByVideoFilename(String videoFileName) {
        return videoDao.selectVideoComment(videoFileName);
    }

    //登陆的用户查询对该视频是否进行过操作
    @Override
    public VideoInfoDTO initVideoInfo(String videoAddress, String username) {
        //1.查询该视频的所有信息
        VideoInfoDO resultOfVideoInfoDO = videoDao.selectVideoInfo(videoAddress);
        OperationsDO operationsDO = new OperationsDO(username, videoAddress);
        //2.查询username用户是否对该视频操作过
        OperationsDO resultOfOperationsDO = videoDao.selectOperationsState(operationsDO);
        VideoInfoDTO videoInfoDTO;
        //3.1 若未进行任何操作，videoPage不包含Ops的信息,用于视频播放页面初始化时下方的按钮是否为激活状态
        if (resultOfOperationsDO == null) {
            videoInfoDTO = new VideoInfoDTO(resultOfVideoInfoDO.getVideoIdentityDocument(), resultOfVideoInfoDO.getUsername(), resultOfVideoInfoDO.getVideoTitle(),
                    resultOfVideoInfoDO.getVideoType(), resultOfVideoInfoDO.getVideoState(), resultOfVideoInfoDO.getVideoFilename(),
                    resultOfVideoInfoDO.getVideoDescription(), resultOfVideoInfoDO.getVideoName(), resultOfVideoInfoDO.getVideoCover(), resultOfVideoInfoDO.getVideoData(), resultOfVideoInfoDO.getVideoStars(),
                    resultOfVideoInfoDO.getVideoCoins(), resultOfVideoInfoDO.getVideoConnections(), resultOfVideoInfoDO.getVideoShares(), resultOfVideoInfoDO.getVideoPlayNum(),
                    resultOfVideoInfoDO.getVideoBarrages());
        }
        //3.2 若有进行点赞等操作，videoPage包含Ops实体信息,用于视频播放页面初始化时下方的按钮是否为激活状态
        else {
            videoInfoDTO = new VideoInfoDTO(resultOfVideoInfoDO.getVideoIdentityDocument(), resultOfVideoInfoDO.getUsername(), resultOfVideoInfoDO.getVideoTitle(),
                    resultOfVideoInfoDO.getVideoType(), resultOfVideoInfoDO.getVideoState(), resultOfVideoInfoDO.getVideoFilename(),
                    resultOfVideoInfoDO.getVideoDescription(), resultOfVideoInfoDO.getVideoName(), resultOfVideoInfoDO.getVideoCover(), resultOfVideoInfoDO.getVideoData(), resultOfVideoInfoDO.getVideoStars(),
                    resultOfVideoInfoDO.getVideoCoins(), resultOfVideoInfoDO.getVideoConnections(), resultOfVideoInfoDO.getVideoShares(), resultOfVideoInfoDO.getVideoPlayNum(),
                    resultOfVideoInfoDO.getVideoBarrages(), resultOfOperationsDO.getIsStar(), resultOfOperationsDO.getIsCollect(), resultOfOperationsDO.getIsCoin());
        }

        return videoInfoDTO;
    }

    //游客，查询该视频自有的属性
    public VideoInfoDTO initVideoInfo(String videoAddress) {
        //1.查询该视频的所有信息
        VideoInfoDO resultOfVideoInfoDO = videoDao.selectVideoInfo(videoAddress);
        //2.将videoInfo的信息赋给VideoPage（多态，处理方便）
        VideoInfoDTO videoInfoDTO;
        videoInfoDTO = new VideoInfoDTO(resultOfVideoInfoDO.getVideoIdentityDocument(), resultOfVideoInfoDO.getUsername(), resultOfVideoInfoDO.getVideoTitle(),
                resultOfVideoInfoDO.getVideoType(), resultOfVideoInfoDO.getVideoState(), resultOfVideoInfoDO.getVideoFilename(),
                resultOfVideoInfoDO.getVideoDescription(), resultOfVideoInfoDO.getVideoName(), resultOfVideoInfoDO.getVideoCover(), resultOfVideoInfoDO.getVideoData(), resultOfVideoInfoDO.getVideoStars(),
                resultOfVideoInfoDO.getVideoCoins(), resultOfVideoInfoDO.getVideoConnections(), resultOfVideoInfoDO.getVideoShares(), resultOfVideoInfoDO.getVideoPlayNum(),
                resultOfVideoInfoDO.getVideoBarrages());
        return videoInfoDTO;
    }

    @Override
    public boolean operationOfStar(OperationsDO operationsDO) {
        //1.1查询是否存在该用户对视频的操作，不存在时返回值为false
        if (videoDao.selectOperationsData(operationsDO) != null) {
            //1.2 查询该条数据的star为1还是0
            OperationsDO operationsDOState = videoDao.selectOperationsState(operationsDO);
            //1.3 star为0，表示没点过攒，这次操作为点赞
            if (operationsDOState.getIsStar() == 0) {
                //1.3.1 改变star的数值为1
                operationsDO.setIsStar(1);
                videoDao.updateStarState(operationsDO);
                //1.3.2 更新视频的自有信息，点赞数量加一，其余不变
                videoDao.updateVideoStarAdd(operationsDO.getVideoFilename());
                //1.3.3 返回true 表示点赞成功
                return true;
            }
            //1.4 star为1，表示点过攒，这次操作为取消点赞
            else {
                //1.4.1 改变star的数值为0
                operationsDO.setIsStar(0);
                //1.4.2 执行更改
                videoDao.updateStarState(operationsDO);
                //1.4.3 更新视频的自有信息，点赞数量减一，其余不变
                videoDao.updateVideoStarSub(operationsDO.getVideoFilename());
                //1.4.4 返回false，表示取消点赞成功
                return false;
            }

        }
        //2.1 为null时 ， 表示表中不存在该条数据
        else {
            //2.2 初始化ops中的star项的数值为1
            operationsDO.setIsStar(1);
            //2.3 向ops表中添加一条数据
            videoDao.insertOperationsData(operationsDO);
            videoDao.updateVideoStarAdd(operationsDO.getVideoFilename());
            //2.4 返回值为true，表示点赞成功
            return true;
        }

    }

    @Override
    public boolean operationOfCollect(OperationsDO operationsDO) {
        //1.1查询是否存在该用户对视频的操作，不存在时返回值为false
        if (videoDao.selectOperationsState(operationsDO) != null) {
            //1.2 查询该条数据的collect为1还是0
            OperationsDO operationsDOState = videoDao.selectOperationsState(operationsDO);
            //1.3 collect为0，表示没收藏过，这次操作为点赞
            if (operationsDOState.getIsCollect() == 0) {
                //1.3.1 改变collect的数值为1
                operationsDO.setIsCollect(1);
                videoDao.changeCollectState(operationsDO);
                //1.3.2 更新视频的自有信息，收藏数量加一，其余不变
                videoDao.updateVideoCollectAdd(operationsDO.getVideoFilename());
                //1.3.3 返回true 表示收藏成功
                return true;
            }
            //1.4 collect为1，表示已收藏，这次操作为取消收藏
            else {
                //1.4.1 改变star的数值为0
                operationsDO.setIsCollect(0);
                //1.4.2 执行更改
                videoDao.changeCollectState(operationsDO);
                //1.4.3 更新视频的自有信息，收藏数量减一，其余不变
                videoDao.updateVideoCollectSub(operationsDO.getVideoFilename());
                //1.4.4 返回false，表示取消收藏成功
                return false;
            }

        }
        //2.1 为null时 ， 表示表中不存在该条数据
        else {
            //2.2 初始化ops中的star项的数值为1
            operationsDO.setIsCollect(1);
            //2.3 向ops表中添加一条数据
            videoDao.insertOperationsData(operationsDO);
            videoDao.updateVideoCollectAdd(operationsDO.getVideoFilename());
            //2.4 返回值为true，表示点赞成功
            return true;
        }
    }

    //分享视频
    @Override
    public boolean operationOfShare(OperationsDO operationsDO) {
        if (operationsDO.getUsername() == null) {
            return false;
        } else {
            if (videoDao.selectOperationsData(operationsDO) != null) {
                operationsDO.setIsShare(1);
                videoDao.updateShareData(operationsDO);
            } else {
                operationsDO.setIsShare(1);
                videoDao.insertOperationsData(operationsDO);
            }
            videoDao.updateVideoShareAdd(operationsDO.getVideoFilename());
            return true;
        }
    }

    //检查数据项是否为空
    @Override
    public void deleteOpsData(OperationsDO operationsDO) {
        //定义布尔类型的变量来判断是否删除成功，用于打印日志以便日后调试
        boolean flag = false;
        //1.查询ops中对应的数据，username 和 videoFileName
        OperationsDO operationsDOState = videoDao.selectOperationsState(operationsDO);
        //1.1 若全部都为空
        if ((operationsDOState.getIsStar() == 0) && (operationsDOState.getIsCoin() == 0) && (operationsDOState.getIsCollect() == 0) && (operationsDOState.getIsShare() == 0)) {
            //1.1.1 删除该条数据
            flag = videoDao.deleteOpsData(operationsDO);
        }

        // TODO: 2019/9/11 打印日志，记录错误信息
//        if (!flag) {
//
//        }
    }

    @Override
    public ResultBean addReportVideoData(ReportVideoDO reportVideoDO) {
        if (videoDao.selectReportData(reportVideoDO) == null) {
            if (videoDao.insertReportVideoData(reportVideoDO)) {
                return new ResultBean<>("举报成功，感谢您的支持！");
            } else return new ResultBean<>("举报失败，请稍后重试！");

        } else return new ResultBean<>("您已经举报过该视频，请勿重复操作！");

    }

    @Override
    public void addVideoBarrages(String videoFilename, int videoBarrages) {
        videoDao.updateVideoBarragesAdd(videoFilename, videoBarrages);
    }

    @Override
    public boolean removeVideoByVideoFilename(String videoFilename) {
        //删除数据库相关信息和服务器下的文件
        if (videoDao.deleteVideoByVideoFilename(videoFilename) > 0) {
            if (promoteVideosDao.setPromoteVideoTimeOver(videoFilename)) {
                ManageFilesUtil manageFilesUtil = new ManageFilesUtil();
                manageFilesUtil.deleteOriginFile("F:/upload/video/" + videoFilename);
                manageFilesUtil.deleteOriginFile("F:/upload/videoCover/" + videoFilename.replace("mp4", "jpg"));
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
