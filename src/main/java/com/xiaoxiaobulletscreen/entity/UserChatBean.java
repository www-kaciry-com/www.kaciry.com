package com.xiaoxiaobulletscreen.entity;

public class UserChatBean {
    private int userChatID;
    private String senderID;
    private String receiverID;
    private String content;
    private String date;

    public UserChatBean() {
    }

    public UserChatBean(String senderID, String receiverID, String content, String date) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.content = content;
        this.date = date;
    }

    public int getUserChatID() {
        return userChatID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserChatBean{" +
                "userChatID=" + userChatID +
                ", senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
