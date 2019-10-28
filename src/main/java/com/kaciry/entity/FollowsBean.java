package com.kaciry.entity;

import java.util.List;

/**
 * @author Kaciry
 */
public class FollowsBean {
    private List<FansBean> fansBeans;
    private List<User> users;

    public FollowsBean(List<FansBean> fansBeans, List<User> users) {
        this.fansBeans = fansBeans;
        this.users = users;
    }

    public List<FansBean> getFansBeans() {
        return fansBeans;
    }

    public void setFansBeans(List<FansBean> fansBeans) {
        this.fansBeans = fansBeans;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
