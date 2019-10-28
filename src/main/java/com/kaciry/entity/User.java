package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class User {
    //用户名
    private String username;
    //密码
    private String userPassword;
    //头像
    private String userHeadIcon;
    //个性签名
    private String userSignature;
    //昵称
    private String userNickName;
    //是否为会员
    private String isVip;
    //等级
    private String userLevel;
    //真实姓名
    private String userRealName;
    //邮箱
    private String userEmail;
    //电话
    private String userPhoneNumber;
    //性别
    private String userSex;
    //币的个数
    private int userCoins;

    public User() {
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
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

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
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

    public int getUserCoins() {
        return userCoins;
    }

    public void setUserCoins(int userCoins) {
        this.userCoins = userCoins;
    }

    public User(String username, String userPassword, String userHeadIcon, String userNickName, String isVip, String userLevel, String userRealName, String userEmail, String userPhoneNumber, String userSex, int userCoins) {
        this.username = username;
        this.userPassword = userPassword;
        this.userHeadIcon = userHeadIcon;
        this.userNickName = userNickName;
        this.isVip = isVip;
        this.userLevel = userLevel;
        this.userRealName = userRealName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userSex = userSex;
        this.userCoins = userCoins;
    }

    public User(String username, String userPassword, String userNickName, String userLevel, String userRealName, String userEmail, String userPhoneNumber, String userSex, int userCoins) {
        this.username = username;
        this.userPassword = userPassword;
        this.userNickName = userNickName;
        this.userLevel = userLevel;
        this.userRealName = userRealName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userSex = userSex;
        this.userCoins = userCoins;
    }

    public User(String username, String userPassword, String userHeadIcon, String userSignature, String userNickName, String isVip, String userLevel, String userRealName, String userEmail, String userPhoneNumber, String userSex, int userCoins) {
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
    }

    public User(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }
}
