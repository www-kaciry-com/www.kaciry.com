package com.kaciry.Utils;
/**
 * @author kaciry
 * @date 2019/10/26 13:57
 * @description 视频文件名工具类
 */
public class FormatVideoName {
    /**
     * @author kaciry
     * @description
     * @date  2019/10/26 14:08
     * @param str 视频文件名
     * @return java.lang.String
    **/
    public static String formatVideoName(String str){
        String res = str.replaceAll("[a-zA-Z]","");
        res = res.replace("-","");
        return res;
    }
}
