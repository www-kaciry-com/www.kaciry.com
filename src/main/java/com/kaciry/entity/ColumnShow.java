package com.kaciry.entity;

/**
 * @author FLLH
 * @date 2019/12/26 11:41
 * @description 查看专栏实体
 */
public class ColumnShow {
    private String username;
    private String columnContent;
    private String uploadTime;
    private String userHeadIcon;
    private String userNickName;
    private String isVip;
    private String userSignature;

    public ColumnShow(String username, String columnContent, String uploadTime, String userHeadIcon, String userNickName, String isVip, String userSignature) {
        this.username = username;
        this.columnContent = columnContent;
        this.uploadTime = uploadTime;
        this.userHeadIcon = userHeadIcon;
        this.userNickName = userNickName;
        this.isVip = isVip;
        this.userSignature = userSignature;
    }

    public ColumnShow() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColumnContent() {
        return columnContent;
    }

    public void setColumnContent(String columnContent) {
        this.columnContent = columnContent;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
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

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }
}
