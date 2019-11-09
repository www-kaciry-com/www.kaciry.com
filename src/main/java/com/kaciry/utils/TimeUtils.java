package com.kaciry.utils;

import org.apache.commons.lang.time.DateUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author kaciry
 * @date 2019/11/1 14:52
 * @description 计算时间工具类
 */
public class TimeUtils {

    static String getTimeStampNumberFormat(Timestamp formatTime) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", new Locale("zh", "cn"));
        return mFormat.format(formatTime);
    }

    public static String getTimeDifference(Timestamp formatTime1, Timestamp formatTime2) {
        long t1 = formatTime1.getTime();
        long t2 = formatTime2.getTime();

        int hours = (int) ((t1 - t2) / (1000 * 60 * 60));
        if (hours < 0) {
            return "false";
        } else {
            int minutes = (int) (((t1 - t2) / 1000 - hours * (60 * 60)) / 60);
            int second = (int) ((t1 - t2) / 1000 - hours * (60 * 60) - minutes * 60);
            return "" + hours + "小时" + minutes + "分" + second + "秒";
        }
    }

    public static Timestamp analysisTime(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        date = DateUtils.addDays(date, 3);
        //中西方相差8小时，配置serverTimezone=GMT%2B8
        return new Timestamp(date.getTime());
    }
}
