package com.yfdyf.recommend.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 * */
public class SystemDateFormat {
    //年月日
    public static String getNowDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    //年月日 时分秒
    public static String getNowDateExact(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
