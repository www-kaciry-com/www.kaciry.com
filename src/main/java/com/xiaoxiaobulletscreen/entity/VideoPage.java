package com.xiaoxiaobulletscreen.entity;

public class VideoPage {
    private int videoID;
    private String username;
    private String videoTitle;
    private String videoType;
    private int videoState;
    private String videoFilename;
    private String videoDescription;
    private String videoName;
    private int videoStars;
    private int videoCoins;
    private int videoConnections;
    private int videoShares;
    private int videoPlayNum;
    private int videoBarrages;
    private int isStar;
    private int isConnection;
    private int isCoin;

    public VideoPage() {
    }

    public VideoPage(int videoID, String username, String videoTitle, String videoType, int videoState, String videoFilename, String videoDescription, String videoName,
                     int videoStars, int videoCoins, int videoConnections, int videoShares, int videoPlayNum, int videoBarrages, int isStar, int isConnection, int isCoin) {
        this.videoID = videoID;
        this.username = username;
        this.videoTitle = videoTitle;
        this.videoType = videoType;
        this.videoState = videoState;
        this.videoFilename = videoFilename;
        this.videoDescription = videoDescription;
        this.videoName = videoName;
        this.videoStars = videoStars;
        this.videoCoins = videoCoins;
        this.videoConnections = videoConnections;
        this.videoShares = videoShares;
        this.videoPlayNum = videoPlayNum;
        this.videoBarrages = videoBarrages;
        this.isStar = isStar;
        this.isConnection = isConnection;
        this.isCoin = isCoin;
    }

    public VideoPage(int videoID, String username, String videoTitle, String videoType, int videoState, String videoFilename, String videoDescription, String videoName,
                     int videoStars, int videoCoins, int videoConnections, int videoShares, int videoPlayNum, int videoBarrages) {
        this.videoID = videoID;
        this.username = username;
        this.videoTitle = videoTitle;
        this.videoType = videoType;
        this.videoState = videoState;
        this.videoFilename = videoFilename;
        this.videoDescription = videoDescription;
        this.videoName = videoName;
        this.videoStars = videoStars;
        this.videoCoins = videoCoins;
        this.videoConnections = videoConnections;
        this.videoShares = videoShares;
        this.videoPlayNum = videoPlayNum;
        this.videoBarrages = videoBarrages;
    }

    public int getVideoID() {
        return videoID;
    }

    public void setVideoID(int videoID) {
        this.videoID = videoID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getIsStar() {
        return isStar;
    }

    public void setIsStar(int isStar) {
        this.isStar = isStar;
    }

    public int getIsConnection() {
        return isConnection;
    }

    public void setIsConnection(int isConnection) {
        this.isConnection = isConnection;
    }

    public int getIsCoin() {
        return isCoin;
    }

    public void setIsCoin(int isCoin) {
        this.isCoin = isCoin;
    }

    @Override
    public String toString() {
        return "VideoPage{" +
                "videoID=" + videoID +
                ", username='" + username + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoType='" + videoType + '\'' +
                ", videoState=" + videoState +
                ", videoFilename='" + videoFilename + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                ", videoName='" + videoName + '\'' +
                ", videoStars=" + videoStars +
                ", videoCoins=" + videoCoins +
                ", videoConnections=" + videoConnections +
                ", videoShares=" + videoShares +
                ", videoPlayNum=" + videoPlayNum +
                ", videoBarrages=" + videoBarrages +
                ", isStar=" + isStar +
                ", isConnection=" + isConnection +
                ", isCoin=" + isCoin +
                '}';
    }
}
