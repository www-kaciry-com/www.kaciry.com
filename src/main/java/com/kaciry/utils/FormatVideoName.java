package com.kaciry.utils;

import java.sql.Timestamp;

/**
 * @author kaciry
 * @date 2019/10/26 13:57
 * @description 视频文件名工具类
 */
public class FormatVideoName {
    /**
     * @author kaciry
     * @description  生成时间戳类型的文件名
     * @date  2019/11/2 12:50
     * @return java.lang.String
    **/
    public static String getTargetFileName(){
        String res = new Timestamp(System.currentTimeMillis()).toString();
        res = res.replace("-","");
        res = res.replace(":","");
        res = res.replace(".","");
        res = res.replace(" ","");
        return res;
    }
}
