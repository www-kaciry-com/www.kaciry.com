package com.kaciry.entity;

/**
 * @author kaciry
 */
public class VideoFollowPage {
    //关注表索引
    private String userIdentityDocument;
    //被关注用户名
    private String followedUser;
    //关注日期
    private String followedDate;
    //用户名
    private String username;
    //头像
    private String userHeadIcon;
    //个性签名
    private String userSignature;
    //昵称
    private String userNickName;


    public VideoFollowPage(String userIdentityDocument, String followedUser, String followedDate, String username, String userHeadIcon, String userSignature, String userNickName) {
        this.userIdentityDocument = userIdentityDocument;
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
