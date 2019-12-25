package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class UnionFansDO {
    //用户名
    private String username;
    //密码
    private String userPassword;
    //头像路径
    private String userHeadIcon;
    //个性签名
    private String userSignature;
    //昵称
    private String userNickName;
    //是否为VIP
    private String isVip;
    //等级
    private Integer userLevel;
    //真实姓名
    private String userRealName;
    //邮箱
    private String userEmail;
    //电话
    private String userPhoneNumber;
    //性别
    private String userSex;
    //币的个数
    private Integer userCoins;
    //关注表索引
    private long followIdentityDocument;
    //主用户名
    private String userIdentityDocument;
    //被关注的用户名
    private String followedUser;
    //关注日期
    private String followedDate;

    public UnionFansDO() {
    }

    public UnionFansDO(String username, String userPassword, String userHeadIcon, String userSignature, String userNickName, String isVip,
                       Integer userLevel, String userRealName, String userEmail, String userPhoneNumber, String userSex, Integer userCoins, long followIdentityDocument, String userIdentityDocument, String followedUser, String followedDate) {
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
        this.followIdentityDocument = followIdentityDocument;
        this.userIdentityDocument = userIdentityDocument;
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

    public long getFollowIdentityDocument() {
        return followIdentityDocument;
    }

    public void setFollowIdentityDocument(long followIdentityDocument) {
        this.followIdentityDocument = followIdentityDocument;
    }

    public String getUserIdentityDocument() {
        return userIdentityDocument;
    }

    public void setUserIdentityDocument(String userIdentityDocument) {
        this.userIdentityDocument = userIdentityDocument;
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
