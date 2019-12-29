package com.kaciry.entity;

/**
 * @author FLLH
 * @date 2019/12/23 12:05
 * @description 专题 富文本框
 */
public class ColumnInfo {
    private int columnIdentityDocument;
    private String username;
    private String columnContent;
    private int state;
    private String uploadTime;

    public ColumnInfo(int columnIdentityDocument, String username, String columnContent, int state, String uploadTime) {
        this.columnIdentityDocument = columnIdentityDocument;
        this.username = username;
        this.columnContent = columnContent;
        this.state = state;
        this.uploadTime = uploadTime;
    }

    public ColumnInfo(String username, String columnContent, String uploadTime) {
        this.username = username;
        this.columnContent = columnContent;
        this.uploadTime = uploadTime;
    }

    public ColumnInfo() {

    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getColumnIdentityDocument() {
        return columnIdentityDocument;
    }

    public void setColumnIdentityDocument(int columnIdentityDocument) {
        this.columnIdentityDocument = columnIdentityDocument;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "columnIdentityDocument=" + columnIdentityDocument +
                ", username='" + username + '\'' +
                ", columnContent='" + columnContent + '\'' +
                ", state=" + state +
                '}';
    }
}
