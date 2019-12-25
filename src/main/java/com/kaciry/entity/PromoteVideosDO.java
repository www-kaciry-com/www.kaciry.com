package com.kaciry.entity;

import java.sql.Timestamp;

/**
 * @author kaciry
 * @date 2019/11/1 13:58
 * @description 推广视频表实体
 */
public class PromoteVideosDO {
    //视频文件名
    private String videoFilename;
    //到期时间
    private Timestamp surplusDuration;
    //推广类型(0：到期，1：轮播图，2：推广栏目专区)
    private int promoteType;

    public PromoteVideosDO() {
    }

    public PromoteVideosDO(String videoFilename, Timestamp surplusDuration, int promoteType) {
        this.videoFilename = videoFilename;
        this.surplusDuration = surplusDuration;
        this.promoteType = promoteType;
    }

    public String getVideoFilename() {
        return videoFilename;
    }

    public void setVideoFilename(String videoFilename) {
        this.videoFilename = videoFilename;
    }

    public Timestamp getSurplusDuration() {
        return surplusDuration;
    }

    public void setSurplusDuration(Timestamp surplusDuration) {
        this.surplusDuration = surplusDuration;
    }

    public int getPromoteType() {
        return promoteType;
    }

    public void setPromoteType(int promoteType) {
        this.promoteType = promoteType;
    }

    @Override
    public String toString() {
        return "PromoteVideosBean{" +
                "videoFilename='" + videoFilename + '\'' +
                ", surplusDuration=" + surplusDuration +
                ", promoteType=" + promoteType +
                '}';
    }
}
