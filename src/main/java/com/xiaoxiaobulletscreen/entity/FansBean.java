package com.xiaoxiaobulletscreen.entity;

public class FansBean {
    private int followID;
    private String userID;
    private String followedUser;
    private String followedDate;

    public FansBean() {
    }

    public FansBean(String userID, String followedUser, String followedDate) {
        this.userID = userID;
        this.followedUser = followedUser;
        this.followedDate = followedDate;
    }

    public FansBean(int followID, String userID, String followedUser, String followedDate) {
        this.followID = followID;
        this.userID = userID;
        this.followedUser = followedUser;
        this.followedDate = followedDate;
    }

    public int getFollowID() {
        return followID;
    }

    public void setFollowID(int followID) {
        this.followID = followID;
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

    @Override
    public String toString() {
        return "FansBean{" +
                "followID=" + followID +
                ", userID='" + userID + '\'' +
                ", followedUser='" + followedUser + '\'' +
                ", followedDate='" + followedDate + '\'' +
                '}';
    }
}
