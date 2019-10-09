package com.seeyu.lang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author seeyu
 */
public class DateUtils {


    /**
     * 使用默认的pattern(yyyy-MM-dd)对日期进行转换
     * @param date
     * @return
     */
    public static Date trunc(Date date){
        return trunc(date, "yyyy-MM-dd");
    }

    /**
     * 使用pattern对日期进行转换
     * @param date
     * @param pattern
     * @return
     */
    public static Date trunc(Date date, String pattern){
        String s = datePrint(date, pattern);
        return dateFormat(s, pattern);
    }


    /**
     * 日期加减
     * @param date
     * @param field 如: Calendar.MONTH
     * @param amount
     * @return
     */
    public static Date datePlus(Date date, int field, int amount){
        if(date == null){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 将Date类型转化为字符串
     *
     * @param date
     *            日期
     * @param pattern
     *            格式
     * @return 字符串
     */
    public static String datePrint(Date date, String pattern) {
        if(date == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }


    /**
     * 将字符串转换为Date类型
     *
     * @param date
     *            字符串类型
     * @param patterns
     *            格式
     * @return 日期类型
     */
    public static Date dateFormat(String date, String... patterns) {
        if(date == null){
            return null;
        }

        Exception exception = null;
        for(String p : patterns){
            try{
                return dateFormat(date, p);
            }catch (Exception e){
                exception = e;
            }
        }
        throw new RuntimeException(exception);
    }

    /**
     * 将字符串转换为Date类型
     *
     * @param date
     *            字符串类型
     * @param pattern
     *            格式
     * @return 日期类型
     */
    public static Date dateFormat(String date, String pattern) {
        if(date == null){
            return null;
        }
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 将字符串转换为Date类型(yyyy-MM-dd)格式
     *
     * @param date
     * @return
     */
    public static Date dateShortFormat(String date) {
        return dateFormat(date, "yyyy-MM-dd");
    }

    /**
     * 将Date类型转化为字符串(yyyy-MM-dd)格式
     *
     * @param date
     * @return
     */
    public static String dateShortPrint(Date date) {
        return datePrint(date, "yyyy-MM-dd");
    }

    /**
     * 将Date类型转化为字符串(yyyy-MM-dd HH:mm:ss)格式
     *
     * @param date
     * @return
     */
    public static String dateTimePrint(Date date) {
        return datePrint(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将字符串转换为Date类型(yyyy-MM-dd HH:mm:ss)格式
     *
     * @param date
     * @return
     */
    public static Date dateTimeFormat(String date) {
        return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
    }
}
