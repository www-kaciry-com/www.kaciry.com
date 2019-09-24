package com.xiaoxiaobulletscreen.entity;

public class Ops {
    private String username;
    private String videoFilename;
    private int isStar;
    private int isCoin;
    private int isCollect;


    public Ops() {
    }

    public Ops(String username, String videoFilename) {
        this.username = username;
        this.videoFilename = videoFilename;
    }
    //点赞

    public Ops(String username, String videoFilename, int isStar) {
        this.username = username;
        this.videoFilename = videoFilename;
        this.isStar = isStar;
    }

    public Ops(String username, String videoFilename, int isStar, int isCoin, int isCollect) {
        this.username = username;
        this.videoFilename = videoFilename;
        this.isStar = isStar;
        this.isCoin = isCoin;
        this.isCollect = isCollect;
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
