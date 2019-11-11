package com.kaciry.entity;

/**
 * @author kaciry
 * @date 2019/11/11 15:46
 * @description
 */
public class Danmu {
    private float time;
    private int place;
    private int color;
    private String user;
    private String content;

    public Danmu(float time, int place, int color, String user, String content) {
        this.time = time;
        this.place = place;
        this.color = color;
        this.user = user;
        this.content = content;
    }

    public double getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
