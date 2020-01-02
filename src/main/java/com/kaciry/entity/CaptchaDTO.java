package com.kaciry.entity;

/**
 * @author Kaciry
 */
public class CaptchaDTO {
    private String code = Long.toString(System.currentTimeMillis());
    private String res = code.substring(code.length() - 6);

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
