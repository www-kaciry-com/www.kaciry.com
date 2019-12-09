package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class OpsDO {
    //用户名
    private String username;
    //视频文件名
    private String videoFilename;
    //是否点过赞
    private int isStar;
    //是否投币
    private int isCoin;
    //是否收藏
    private int isCollect;
    //是否分享
    private int isShare;

    public OpsDO() {
    }

    public OpsDO(String username, String videoFilename) {
        this.username = username;
        this.videoFilename = videoFilename;
    }
    //点赞

    public OpsDO(String username, String videoFilename, int isStar) {
        this.username = username;
        this.videoFilename = videoFilename;
        this.isStar = isStar;
    }

    public OpsDO(String username, String videoFilename, int isStar, int isCoin, int isCollect) {
        this.username = username;
        this.videoFilename = videoFilename;
        this.isStar = isStar;
        this.isCoin = isCoin;
        this.isCollect = isCollect;
    }

    public OpsDO(String username, String videoFilename, int isStar, int isCoin, int isCollect, int isShare) {
        this.username = username;
        this.videoFilename = videoFilename;
        this.isStar = isStar;
        this.isCoin = isCoin;
        this.isCollect = isCollect;
        this.isShare = isShare;
    }

    public int getIsShare() {
        return isShare;
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVideoFilename() {
        return videoFilename;
    }

    public void setVideoFilename(String videoFilename) {
        this.videoFilename = videoFilename;
    }

    public int getIsStar() {
        return isStar;
    }

    public void setIsStar(int isStar) {
        this.isStar = isStar;
    }

    public int getIsCoin() {
        return isCoin;
    }

    public void setIsCoin(int isCoin) {
        this.isCoin = isCoin;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    @Override
    public String toString() {
        return "Ops{" +
                "username='" + username + '\'' +
                ", videoFilename='" + videoFilename + '\'' +
                ", isStar=" + isStar +
                ", isCoin=" + isCoin +
                ", isCollect=" + isCollect +
                '}';
    }
}
