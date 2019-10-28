package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class ReportVideoBean {
    //自增ID
    private int reportVideoIdentityDocument;
    //视频文件名
    private String videoFileName;
    //举报类型
    private String reportedType;
    //被举报者ID
    private String beReportedUser;
    //举报人ID
    private String reportedUser;
    //举报时间
    private String reportedTime;
    //举报原因
    private String reportedReason;
    //封禁原因
    private String bannedReason;
    //处理时间
    private int handleState;

    public ReportVideoBean() {
    }

    public ReportVideoBean(String videoFileName, String reportedType, String beReportedUser, String reportedUser, String reportedTime, String reportedReason) {
        this.videoFileName = videoFileName;
        this.reportedType = reportedType;
        this.beReportedUser = beReportedUser;
        this.reportedUser = reportedUser;
        this.reportedTime = reportedTime;
        this.reportedReason = reportedReason;
    }

    public ReportVideoBean(String videoFileName, String reportedType, String beReportedUser, String reportedUser, String reportedTime, String reportedReason, String bannedReason, int handleState) {
        this.videoFileName = videoFileName;
        this.reportedType = reportedType;
        this.beReportedUser = beReportedUser;
        this.reportedUser = reportedUser;
        this.reportedTime = reportedTime;
        this.reportedReason = reportedReason;
        this.bannedReason = bannedReason;
        this.handleState = handleState;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public String getReportedType() {
        return reportedType;
    }

    public void setReportedType(String reportedType) {
        this.reportedType = reportedType;
    }

    public String getBeReportedUser() {
        return beReportedUser;
    }

    public void setBeReportedUser(String beReportedUser) {
        this.beReportedUser = beReportedUser;
    }

    public String getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(String reportedUser) {
        this.reportedUser = reportedUser;
    }

    public String getReportedTime() {
        return reportedTime;
    }

    public void setReportedTime(String reportedTime) {
        this.reportedTime = reportedTime;
    }

    public String getReportedReason() {
        return reportedReason;
    }

    public void setReportedReason(String reportedReason) {
        this.reportedReason = reportedReason;
    }

    public String getBannedReason() {
        return bannedReason;
    }

    public void setBannedReason(String bannedReason) {
        this.bannedReason = bannedReason;
    }

    public int getHandleState() {
        return handleState;
    }

    public void setHandleState(int handleState) {
        this.handleState = handleState;
    }
}
