package com.kaciry.entity;

import java.math.BigInteger;

/**
 * @author Kaciry
 */
public class VideoInfo {
    //索引
    private BigInteger videoIdentityDocument;
    //用户名
    private String username;
    //昵称
    private String userNickName;
    //视频标题
    private String videoTitle;
    //视频类型
    private String videoType;
    //视频审核状态
    private int videoState;
    //视频文件名
    private String videoFilename;
    //视频描述
    private String videoDescription;
    //视频名
    private String videoName;
    //视频封面路径
    private String videoCover;
    //上传日期
    private String videoData;
    //视频点赞数
    private int videoStars;
    //视频获得的币的个数
    private int videoCoins;
    //视频收藏数
    private int videoConnections;
    //视频分享数
    private int videoShares;
    //视频播放数
    private int videoPlayNum;
    //视频弹幕数
    private int videoBarrages;

    public VideoInfo() {
    }

    public VideoInfo(String username, String videoTitle, String videoType, int videoState, String videoFilename, String videoDescription,
                     String videoName, String videoCover, String videoData, int videoStars, int videoCoins, int videoConnections, int videoShares,
                     int videoPlayNum, int videoBarrages) {
        this.username = username;
        this.videoTitle = videoTitle;
        this.videoType = videoType;
        this.videoState = videoState;
        this.videoFilename = videoFilename;
        this.videoDescription = videoDescription;
        this.videoName = videoName;
        this.videoCover = videoCover;
        this.videoData = videoData;
        this.videoStars = videoStars;
        this.videoCoins = videoCoins;
        this.videoConnections = videoConnections;
        this.videoShares = videoShares;
        this.videoPlayNum = videoPlayNum;
        this.videoBarrages = videoBarrages;
    }

    public VideoInfo(String username, String userNickName, String videoTitle, String videoType, int videoState, String videoFilename, String videoDescription, String videoName, String videoCover, String videoData, int videoStars, int videoCoins, int videoConnections, int videoShares, int videoPlayNum, int videoBarrages) {
        this.username = username;
        this.userNickName = userNickName;
        this.videoTitle = videoTitle;
        this.videoType = videoType;
        this.videoState = videoState;
        this.videoFilename = videoFilename;
        this.videoDescription = videoDescription;
        this.videoName = videoName;
        this.videoCover = videoCover;
        this.videoData = videoData;
        this.videoStars = videoStars;
        this.videoCoins = videoCoins;
        this.videoConnections = videoConnections;
        this.videoShares = videoShares;
        this.videoPlayNum = videoPlayNum;
        this.videoBarrages = videoBarrages;
    }

    public BigInteger getVideoIdentityDocument() {
        return videoIdentityDocument;
    }

    public void setVideoIdentityDocument(BigInteger videoIdentityDocument) {
        this.videoIdentityDocument = videoIdentityDocument;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public int getVideoState() {
        return videoState;
    }

    public void setVideoState(int videoState) {
        this.videoState = videoState;
    }

    public String getVideoFilename() {
        return videoFilename;
    }

    public void setVideoFilename(String videoFilename) {
        this.videoFilename = videoFilename;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoData() {
        return videoData;
    }

    public void setVideoData(String videoData) {
        this.videoData = videoData;
    }

    public int getVideoStars() {
        return videoStars;
    }

    public void setVideoStars(int videoStars) {
        this.videoStars = videoStars;
    }

    public int getVideoCoins() {
        return videoCoins;
    }

    public void setVideoCoins(int videoCoins) {
        this.videoCoins = videoCoins;
    }

    public int getVideoConnections() {
        return videoConnections;
    }

    public void setVideoConnections(int videoConnections) {
        this.videoConnections = videoConnections;
    }

    public int getVideoShares() {
        return videoShares;
    }

    public void setVideoShares(int videoShares) {
        this.videoShares = videoShares;
    }

    public int getVideoPlayNum() {
        return videoPlayNum;
    }

    public void setVideoPlayNum(int videoPlayNum) {
        this.videoPlayNum = videoPlayNum;
    }

    public int getVideoBarrages() {
        return videoBarrages;
    }

    public void setVideoBarrages(int videoBarrages) {
        this.videoBarrages = videoBarrages;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "videoIdentityDocument=" + videoIdentityDocument +
                ", username='" + username + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoType='" + videoType + '\'' +
                ", videoState=" + videoState +
                ", videoFilename='" + videoFilename + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                ", videoName='" + videoName + '\'' +
                ", videoCover='" + videoCover + '\'' +
                ", videoData='" + videoData + '\'' +
                ", videoStars=" + videoStars +
                ", videoCoins=" + videoCoins +
                ", videoConnections=" + videoConnections +
                ", videoShares=" + videoShares +
                ", videoPlayNum=" + videoPlayNum +
                ", videoBarrages=" + videoBarrages +
                '}';
    }
}
