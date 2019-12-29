package com.kaciry.entity;

/**
 * @author FLLH
 * @date 2019/12/16 21:52
 * @description 用户IP 绘制地图数据
 */
public class UserIp {
    private int num;
    private String userAddress;

    @Override
    public String toString() {
        return "UserIp{" +
                "num=" + num +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
