package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class ReportVideoDO {
    //自增ID
    private int reportVideoIdentityDocument;
    //视频文件名
    private String videoFilename;
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





    public ReportVideoDO() {
    }

    public ReportVideoDO(int reportVideoIdentityDocument, String videoFilename, String reportedType, String beReportedUser, String reportedUser, String reportedTime, String reportedReason) {
        this.reportVideoIdentityDocument = reportVideoIdentityDocument;
        this.videoFilename = videoFilename;
        this.reportedType = reportedType;
        this.beReportedUser = beReportedUser;
        this.reportedUser = reportedUser;
        this.reportedTime = reportedTime;
        this.reportedReason = reportedReason;
    }

    public int getReportVideoIdentityDocument() {
        return reportVideoIdentityDocument;
    }

    public void setReportVideoIdentityDocument(int reportVideoIdentityDocument) {
        this.reportVideoIdentityDocument = reportVideoIdentityDocument;
    }

    public String getVideoFilename() {
        return videoFilename;
    }

    public void setVideoFilename(String videoFilename) {
        this.videoFilename = videoFilename;
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



}
