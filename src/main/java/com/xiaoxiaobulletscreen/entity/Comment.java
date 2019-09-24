package com.xiaoxiaobulletscreen.entity;

public class Comment {
    private String videoFilename;
    private String username;
    private String commentID;
    private String content;
    private String sendDate;
    private String userHeadIcon;
    private String userNickName;
    private int commentStars;

    public Comment() {
    }

    public Comment(String videoFilename, String username, String content, String sendDate, String userHeadIcon, String userNickName, int commentStars) {
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

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
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
                ", commentID='" + commentID + '\'' +
                ", content='" + content + '\'' +
                ", sendDate='" + sendDate + '\'' +
                ", userHeadIcon='" + userHeadIcon + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", commentStars=" + commentStars +
                '}';
    }
}
