package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class CommentBean {
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
    //用户头像路径
    private String userHeadIcon;
    //用户昵称
    private String userNickName;
    //评论点赞数
    private int commentStars;

    public CommentBean() {
    }

    public CommentBean(String videoFilename, String username, String content, String sendDate, int commentStars) {
        this.videoFilename = videoFilename;
        this.username = username;
        this.content = content;
        this.sendDate = sendDate;
        this.commentStars = commentStars;
    }

    public CommentBean(String videoFilename, String username, String content, String sendDate, String userHeadIcon, String userNickName, int commentStars) {
        this.videoFilename = videoFilename;
        this.username = username;
        this.content = content;
        this.sendDate = sendDate;
        this.userHeadIcon = userHeadIcon;
        this.userNickName = userNickName;
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

    public String getUserHeadIcon() {
        return userHeadIcon;
    }

    public void setUserHeadIcon(String userHeadIcon) {
        this.userHeadIcon = userHeadIcon;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public int getCommentStars() {
        return commentStars;
    }

    public void setCommentStars(int commentStars) {
        this.commentStars = commentStars;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "videoFilename='" + videoFilename + '\'' +
                ", username='" + username + '\'' +
                ", commentIdentityDocument='" + commentIdentityDocument + '\'' +
                ", content='" + content + '\'' +
                ", sendDate='" + sendDate + '\'' +
                ", userHeadIcon='" + userHeadIcon + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", commentStars=" + commentStars +
                '}';
    }
}
