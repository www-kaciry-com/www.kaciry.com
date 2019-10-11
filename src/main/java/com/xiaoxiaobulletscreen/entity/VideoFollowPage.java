package com.xiaoxiaobulletscreen.entity;

public class VideoFollowPage {

    private String userID;
    private String followedUser;
    private String followedDate;

    private String username;
    private String userHeadIcon;
    private String userSignature;
    private String userNickName;


    public VideoFollowPage(String userID, String followedUser, String followedDate, String username, String userHeadIcon, String userSignature, String userNickName) {
        this.userID = userID;
        this.followedUser = followedUser;
        this.followedDate = followedDate;
        this.username = username;
        this.userHeadIcon = userHeadIcon;
        this.userSignature = userSignature;
        this.userNickName = userNickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(String followedUser) {
        this.followedUser = followedUser;
    }

    public String getFollowedDate() {
        return followedDate;
    }

    public void setFollowedDate(String followedDate) {
        this.followedDate = followedDate;
    }

    public String getUserHeadIcon() {
        return userHeadIcon;
    }

    public void setUserHeadIcon(String userHeadIcon) {
        this.userHeadIcon = userHeadIcon;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}
