package com.xiaoxiaobulletscreen.Utils;

public class FormatVideoName {
    public static String formatVideoName(String str){
        String res = str.replaceAll("[a-zA-Z]","");
        res = res.replace("-","");
        return res;
    }
}
