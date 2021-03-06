package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class FansDO {
    //表索引
    private long followIdentityDocument;
    //主用户名
    private String userIdentityDocument;
    //被关注的用户名
    private String followedUser;
    //关注日期
    private String followedDate;

    public FansDO() {
    }

    public FansDO(String userIdentityDocument, String followedUser, String followedDate) {
        this.userIdentityDocument = userIdentityDocument;
        this.followedUser = followedUser;
        this.followedDate = followedDate;
    }

    public FansDO(long followIdentityDocument, String userIdentityDocument, String followedUser, String followedDate) {
        this.followIdentityDocument = followIdentityDocument;
        this.userIdentityDocument = userIdentityDocument;
        this.followedUser = followedUser;
        this.followedDate = followedDate;
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
