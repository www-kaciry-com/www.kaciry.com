package com.kaciry.Utils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import java.util.Date;

/**
 * @author kaciry
 * @date 2019/11/2 23:58
 * @description demo
 */
public class demo {
    public static void main(String[] args) {
        //将指定日期加上固定时间，DateUtils还有其它添加分钟、小时、月份之类的方法api
        //使用到的是commons-lang包下面的DateUtils类
        Date date = DateUtils.addDays(new Date(), -10);   //
        System.out.println("当前时间为:" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        String format = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间加上10天后:" + format);
    }
}
