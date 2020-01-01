package com.kaciry.entity;

/**
 * @author FLLH
 * @date 2019/11/16 14:31
 * @description 音乐实体
 */
public class Music {
    private int musicIdentityDocument;
    private String username;
    private String musicType;
    private String musicTittle;
    private String musicName;
    private String musicFilename;
    private String musicAuthor;
    private String date;
    private String musicCover;
    private String musicLrc;
    private int musicPlayNum;
    private int musicState;

    public Music(int musicIdentityDocument, String username, String musicType, String musicTittle, String musicName, String musicFilename, String date, String musicCover, String musicLrc, int musicPlayNum, int musicState) {
        this.musicIdentityDocument = musicIdentityDocument;
        this.username = username;
        this.musicType = musicType;
        this.musicTittle = musicTittle;
        this.musicName = musicName;
        this.musicFilename = musicFilename;
        this.date = date;
        this.musicCover = musicCover;
        this.musicLrc = musicLrc;
        this.musicPlayNum = musicPlayNum;
        this.musicState = musicState;
    }

    public Music() {

    }

    @Override
    public String toString() {
        return "Music{" +
                "username='" + username + '\'' +
                ", musicType='" + musicType + '\'' +
                ", musicTittle='" + musicTittle + '\'' +
                ", musicName='" + musicName + '\'' +
                ", musicFilename='" + musicFilename + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                ", date='" + date + '\'' +
                ", musicCover='" + musicCover + '\'' +
                ", musicLrc='" + musicLrc + '\'' +
                ", musicPlayNum=" + musicPlayNum +
                ", musicState=" + musicState +
                '}';
    }

    public int getMusicIdentityDocument() {
        return musicIdentityDocument;
    }

    public void setMusicIdentityDocument(int musicIdentityDocument) {
        this.musicIdentityDocument = musicIdentityDocument;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMusicCover() {
        return musicCover;
    }

    public void setMusicCover(String musicCover) {
        this.musicCover = musicCover;
    }

    public String getMusicLrc() {
        return musicLrc;
    }

    public void setMusicLrc(String musicLrc) {
        this.musicLrc = musicLrc;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getMusicTittle() {
        return musicTittle;
    }

    public void setMusicTittle(String musicTittle) {
        this.musicTittle = musicTittle;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicFilename() {
        return musicFilename;
    }

    public void setMusicFilename(String musicFilename) {
        this.musicFilename = musicFilename;
    }

    public int getMusicPlayNum() {
        return musicPlayNum;
    }

    public void setMusicPlayNum(int musicPlayNum) {
        this.musicPlayNum = musicPlayNum;
    }

    public int getMusicState() {
        return musicState;
    }

    public void setMusicState(int musicState) {
        this.musicState = musicState;
    }
}
