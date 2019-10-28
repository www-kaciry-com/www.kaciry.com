package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class UserChatBean {
    //用户聊天索引
    private int userChatIdentityDocument;
    //发送方用户名
    private String senderIdentityDocument;
    //接收方用户名
    private String receiverIdentityDocument;
    //内容
    private String content;
    //日期
    private String date;

    public UserChatBean() {
    }

    public UserChatBean(String senderIdentityDocument, String receiverIdentityDocument, String content, String date) {
        this.senderIdentityDocument = senderIdentityDocument;
        this.receiverIdentityDocument = receiverIdentityDocument;
        this.content = content;
        this.date = date;
    }

    public int getUserChatIdentityDocument() {
        return userChatIdentityDocument;
    }

    public String getSenderIdentityDocument() {
        return senderIdentityDocument;
    }

    public void setSenderIdentityDocument(String senderIdentityDocument) {
        this.senderIdentityDocument = senderIdentityDocument;
    }

    public String getReceiverIdentityDocument() {
        return receiverIdentityDocument;
    }

    public void setReceiverIdentityDocument(String receiverIdentityDocument) {
        this.receiverIdentityDocument = receiverIdentityDocument;
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
                "userChatIdentityDocument=" + userChatIdentityDocument +
                ", senderIdentityDocument='" + senderIdentityDocument + '\'' +
                ", receiverIdentityDocument='" + receiverIdentityDocument + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
