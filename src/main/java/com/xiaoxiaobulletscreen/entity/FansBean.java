package com.xiaoxiaobulletscreen.entity;

/**
 * @author Kaciry
 */
public class FansBean {
    //表索引
    private int followIdentityDocument;
    //主用户名
    private String userIdentityDocument;
    //被关注的用户名
    private String followedUser;
    //关注日期
    private String followedDate;

    public FansBean() {
    }

    public FansBean(String userIdentityDocument, String followedUser, String followedDate) {
        this.userIdentityDocument = userIdentityDocument;
        this.followedUser = followedUser;
        this.followedDate = followedDate;
    }

    public FansBean(int followIdentityDocument, String userIdentityDocument, String followedUser, String followedDate) {
        this.followIdentityDocument = followIdentityDocument;
        this.userIdentityDocument = userIdentityDocument;
        this.followedUser = followedUser;
        this.followedDate = followedDate;
    }

    public int getFollowIdentityDocument() {
        return followIdentityDocument;
    }

    public void setFollowIdentityDocument(int followIdentityDocument) {
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

    @Override
    public String toString() {
        return "FansBean{" +
                "followIdentityDocument=" + followIdentityDocument +
                ", userIdentityDocument='" + userIdentityDocument + '\'' +
                ", followedUser='" + followedUser + '\'' +
                ", followedDate='" + followedDate + '\'' +
                '}';
    }
}
