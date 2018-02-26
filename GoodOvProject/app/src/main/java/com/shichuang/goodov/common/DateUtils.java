package com.shichuang.goodov.common;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 获取当前时间,返回date类型
     */

    @SuppressLint("SimpleDateFormat")
    public static Date getCurrentSystemTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String steDate = format.format(date);
        Date currentTime = null;
        try {
            currentTime = format.parse(steDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentTime;
    }

    /**
     * 获取当前时间，返回String类型
     */

    @SuppressLint("SimpleDateFormat")
    public static String getStrCurrentSystemTime(String dataType) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dataType);
        String steDate = format.format(date);
        return steDate;
    }

    /**
     * 获取日期的毫秒数，传入2017-05-26 11:36:00类型（字符串日期型），返货毫秒数getTime;
     */

    @SuppressLint("SimpleDateFormat")
    public static long getTimeByDate(String strDate, String dataType) {
        SimpleDateFormat format1 = new SimpleDateFormat(dataType);
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 根据毫秒，返回 分和秒 48:56
     */
    public static String getMMSSByMS(Long ms) {
        long mmm = ms / 1000; // 总秒数
        long mm = mmm / 60; // 分钟
        long ss = mmm % 60; // 秒

        String strMM = mm < 10 ? "0" + mm : mm + "";
        String strSS = ss < 10 ? "0" + ss : ss + "";

        return strMM + ":" + strSS;
    }

    /**
     * 计算两个日期之间差值，传入传入2017-05-26 11:36:00类型（字符串日期型），返回差值 分秒
     */

    public static String getMMSSByDateDifferenceValues(String startDate, String endDate, String dataType) {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return "";
        }
        long startDateMS = 0;
        long endDateMS = 0;
        long differenceValues = 0;

        startDateMS = getTimeByDate(startDate, dataType);
        endDateMS = getTimeByDate(endDate, dataType);
        differenceValues = Math.abs(endDateMS - startDateMS);
        return getMMSSByMS(differenceValues);
    }

    /**
     * 计算两个日期之间的差值，传入传入2017-05-26 11:36:00类型（字符串日期型），返回（long）毫秒值
     */

    public static long getTimeByDateDifferenceValues(String startDate, String endDate, String dataType) {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return 0;
        }
        long startDateMS = 0;
        long endDateMS = 0;
        long differenceValues = 0;

        startDateMS = getTimeByDate(startDate, dataType);
        endDateMS = getTimeByDate(endDate, dataType);
        differenceValues = endDateMS - startDateMS;
//        differenceValues = Math.abs(endDateMS - startDateMS);
        Log.v("时间差", differenceValues + "");
        return differenceValues;
    }

    /**
     * 计算两个日期之间的差值，传入传入2017-05-26 11:36:00类型（字符串日期型），时间差。
     */

    public static String getDistanceTimeByDateDifferenceValues(String startDate, String endDate, String dataType) {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return "";
        }
        long startDateMS = 0;
        long endDateMS = 0;
        long differenceValues = 0;

        startDateMS = getTimeByDate(startDate, dataType);
        endDateMS = getTimeByDate(endDate, dataType);
        if (startDateMS - endDateMS < 0) {
            differenceValues = -Math.abs(endDateMS - startDateMS);
        } else {
            differenceValues = Math.abs(endDateMS - startDateMS);
        }
        return getDistanceDay(differenceValues);
    }

    /**
     * 根据差值返回天数
     */
    public static String getDistanceDay(long diff) {
        long day = 0;
        day = diff / (24 * 60 * 60 * 1000);
        return day + "";
    }

    /**
     * 根据差值返回多长之间前或多长时间后
     */
    public static String getDistanceTime(long diff) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0) {
            if (day > 7) {
                transferLongToDate("MM-dd", diff);
            } else {
                return day + "天前";
            }
        }
        if (hour != 0)
            return hour + "小时前";
        if (min != 0)
            return min + "分钟前";
        return "刚刚";
    }

    /**
     * long转string
     */
    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 时间格式转换 Data转换成string
     */
    public static String getTimeString(String dateFormat, Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    /**
     * 传入String格式化
     */
    @SuppressLint("SimpleDateFormat")
    public static String getStringToFormatDate(String dateFormat, String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat(dateFormat);
        return format2.format(date);
    }

    /**
     * long转换成时分秒
     */
    public static String secToTime(long time) {
        String timeStr;
        long _hour = 1000 * 60 * 60;
        long _minute = 1000 * 60;
        long _second = 1000;

        int hour = (int) (time / _hour);
        int minute = (int) (time % _hour / _minute);
        int second = (int) (time % _hour % _minute / _second);

        String hourString = String.format("%02d", hour);
        String minuteString = String.format("%02d", minute);
        String secondString = String.format("%02d", second);
        timeStr = hourString + "：" + minuteString + "：" + secondString;
        return timeStr;
    }

    /**
     * 传入String格式化
     */
    @SuppressLint("SimpleDateFormat")
    public static String getStringToFormatDate2(String dateFormat, String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat(dateFormat);
        return format2.format(date);
    }
}
