package com.kaciry.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author kaciry
 * @date 2019/11/11 15:36
 * @description
 */
public class ResultDanmu<T> implements Serializable {

    private int code;
    private List<String> data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
