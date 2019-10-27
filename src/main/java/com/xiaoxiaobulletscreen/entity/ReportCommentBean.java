package com.xiaoxiaobulletscreen.entity;

/**
 * @author Kaciry
 */
public class ReportCommentBean {
    //评论的ID
    private int commentIdentityDocument;
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
    //评论内容
    private String commentContent;

/*  ----------------------------------  */
    //封禁原因
    private String bannedReason;
    //处理时间
    private int handleState;

    public ReportCommentBean() {
    }

    public ReportCommentBean(int commentIdentityDocument, String reportedType, String beReportedUser, String reportedUser, String reportedTime, String reportedReason, String commentContent) {
        this.commentIdentityDocument = commentIdentityDocument;
        this.reportedType = reportedType;
        this.beReportedUser = beReportedUser;
        this.reportedUser = reportedUser;
        this.reportedTime = reportedTime;
        this.reportedReason = reportedReason;
        this.commentContent = commentContent;
    }

    public ReportCommentBean(int commentIdentityDocument, String reportedType, String beReportedUser, String reportedUser, String reportedTime, String reportedReason, String commentContent, String bannedReason, int handleState) {
        this.commentIdentityDocument = commentIdentityDocument;
        this.reportedType = reportedType;
        this.beReportedUser = beReportedUser;
        this.reportedUser = reportedUser;
        this.reportedTime = reportedTime;
        this.reportedReason = reportedReason;
        this.commentContent = commentContent;
        this.bannedReason = bannedReason;
        this.handleState = handleState;
    }

    public int getCommentIdentityDocument() {
        return commentIdentityDocument;
    }

    public void setCommentIdentityDocument(int commentIdentityDocument) {
        this.commentIdentityDocument = commentIdentityDocument;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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