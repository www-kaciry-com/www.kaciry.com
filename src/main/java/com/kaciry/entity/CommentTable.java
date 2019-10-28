package com.kaciry.entity;

/**
 * @author kaciry
 * @date 2019/10/28 16:23
 * @description 数据库表Comment
 */
public class CommentTable {
    //视频文件名
    private String videoFilename;
    //用户名
    private String username;
    //评论ID
    private String commentIdentityDocument;
    //评论内容
    private String content;
    //评论时间
    private String sendDate;
    //评论点赞数
    private int commentStars;

    public CommentTable() {
    }

    public CommentTable(String videoFilename, String username, String commentIdentityDocument, String content, String sendDate, int commentStars) {
        this.videoFilename = videoFilename;
        this.username = username;
        this.commentIdentityDocument = commentIdentityDocument;
        this.content = content;
        this.sendDate = sendDate;
        this.commentStars = commentStars;
    }

    public String getVideoFilename() {
        return videoFilename;
    }

    public void setVideoFilename(String videoFilename) {
        this.videoFilename = videoFilename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentIdentityDocument() {
        return commentIdentityDocument;
    }

    public void setCommentIdentityDocument(String commentIdentityDocument) {
        this.commentIdentityDocument = commentIdentityDocument;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public int getCommentStars() {
        return commentStars;
    }

    public void setCommentStars(int commentStars) {
        this.commentStars = commentStars;
    }
}
