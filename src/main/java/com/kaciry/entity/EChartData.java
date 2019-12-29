package com.kaciry.entity;

import java.math.BigInteger;

/**
 * @author FLLH
 * @date 2019/11/11 15:43
 * @description
 */
public class EChartData {

    private String name;
    private BigInteger value;
   // private Map<String, List<String>> valueList;

   /* public Map<String,List<String>> getValueList() {
        return valueList;
    }

    public void setValueList(Map<String,List<String>> valueList) {
        this.valueList = valueList;
    }

    public EChartData(String name, Map<String, List<String>> valueList) {
        this.name = name;
        this.valueList = valueList;
    }*/

    public EChartData(String name, BigInteger value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
