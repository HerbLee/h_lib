package com.herb2sy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_FORMAT, Locale.getDefault());

    public static Date str2Date(String str){
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date strYYMMDD2Date(String str){
        if (str == null){
            return null;
        }
        try {
            SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return d1.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date strYYMMDDHHMMSS2Date(String str){
        if (str == null){
            return null;
        }
        try {
            SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            return d1.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date addDays(Date orDate, int no){
        if (orDate == null){
            return null;
        }

        Calendar instance = Calendar.getInstance();
        instance.setTime(orDate);
        instance.add(Calendar.DAY_OF_MONTH,no);
        return instance.getTime();
    }


    public static String data2Str(Date date){
        try {
            return simpleDateFormat.format(date);
        }catch (Exception e){
            return null;
        }
    }

    public static Date long2Date(Long time){
        try {
            return new Date(time);
        }catch (Exception e){
            return null;
        }
    }

    public static Long date2Long(Date date){
        try {

            return Long.valueOf(date.getTime());
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取 20190909101011 年月日是分秒的格式
     * @param temp 当前时间延后多久
     * @return
     */
    public static String getNow(int temp){
        StringBuilder sb = new StringBuilder();
        Calendar c = Calendar.getInstance();
        if (temp != 0) c.add(Calendar.MINUTE, temp);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        sb.append(year);
        sb.append(month<10?"0"+month:month);
        sb.append(date<10?"0"+date:date);
        sb.append(hour<10?"0"+hour:hour);
        sb.append(minute<10?"0"+minute:minute);
        sb.append(second<10?"0"+second:second);

        return sb.toString();
    }



    public static String getNowYEAR() {
        StringBuilder sb = new StringBuilder();
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);

        sb.append(year);
        sb.append(month < 10 ? "0" + month : month);
        sb.append(date < 10 ? "0" + date : date);
        return sb.toString();
    }





}