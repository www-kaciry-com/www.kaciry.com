package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class ReportCommentDO {
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



    public ReportCommentDO() {
    }

    public ReportCommentDO(int commentIdentityDocument, String reportedType, String beReportedUser, String reportedUser, String reportedTime, String reportedReason, String commentContent) {
        this.commentIdentityDocument = commentIdentityDocument;
        this.reportedType = reportedType;
        this.beReportedUser = beReportedUser;
        this.reportedUser = reportedUser;
        this.reportedTime = reportedTime;
        this.reportedReason = reportedReason;
        this.commentContent = commentContent;
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
}