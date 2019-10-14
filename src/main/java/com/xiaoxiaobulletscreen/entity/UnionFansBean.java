package com.xiaoxiaobulletscreen.entity;

public class UnionFansBean {
    private String username;
    private String userPassword;
    private String userHeadIcon;
    private String userSignature;
    private String userNickName;
    private String isVip;
    private Integer userLevel;
    private String userRealName;
    private String userEmail;
    private String userPhoneNumber;
    private String userSex;
    private Integer userCoins;
    private Integer followID;
    private String userID;
    private String followedUser;
    private String followedDate;

    public UnionFansBean() {
    }

    public UnionFansBean(String username, String userPassword, String userHeadIcon, String userSignature, String userNickName, String isVip, Integer userLevel, String userRealName, String userEmail, String userPhoneNumber, String userSex, Integer userCoins, Integer followID, String userID, String followedUser, String followedDate) {
        this.username = username;
        this.userPassword = userPassword;
        this.userHeadIcon = userHeadIcon;
        this.userSignature = userSignature;
        this.userNickName = userNickName;
        this.isVip = isVip;
        this.userLevel = userLevel;
        this.userRealName = userRealName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userSex = userSex;
        this.userCoins = userCoins;
        this.followID = followID;
        this.userID = userID;
        this.followedUser = followedUser;
        this.followedDate = followedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserCoins() {
        return userCoins;
    }

    public void setUserCoins(Integer userCoins) {
        this.userCoins = userCoins;
    }

    public Integer getFollowID() {
        return followID;
    }

    public void setFollowID(Integer followID) {
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
}
