package com.sdf.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.sql.Timestamp;

/**
 * @Description: 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @Date: 2017/10/11 16:58
 * @Author: SDF
 * @Version: 1.0
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public final static String DATEPATTERN_YYYY = "yyyy";
    public final static String DATEPATTERN_MM = "MM";
    public final static String DATEPATTERN_M = "M";
    public final static String DATEPATTERN_DD = "dd";
    public final static String DATEPATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String DATEPATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String DATEPATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public final static String DATEPATTERN_YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public final static String DATEPATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String DATEPATTERN_YYYYMMDDHHMM = "yyyyMMdd HH:mm";
    public final static String DATEPATTERN_YYYYMMDDHH = "yyyyMMdd HH";
    public final static String DATEPATTERN_YYYYMMDD = "yyyyMMdd";
    public final static String DATEPATTERN_HHMM = "HH:mm";
    public final static String DATEPATTERN_MM_DD = "MM-dd";
    public final static String DATEPATTERN_HHMMSS = "HH:mm:ss";

    public final static String DATEPATTERN_YYYY_MM_DD_LABEL = "yyyy年-MM月-dd日";
    public final static String DATEPATTERN_MM_DD_LABEL = "MM月-dd日";
    public final static String DATEPATTERN_MM_LABEL = "MM月";
    public final static String DATEPATTERN_DD_LABEL = "dd日";
    public final static String DATEPATTERN_YYYYMMDD_LABEL = "yyyy年MM月dd日";
    public final static String DATEPATTERN_MMDD_LABEL = "MM月dd日";
    public final static String DATEPATTERN_MMDD_HH_MM_LABEL = "MM月dd日 HH:mm";

    public final static String[] MONTH = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
    public final static String[] DAY = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getNowDate(String... patterns) {
        String pattern = patterns.length == 0 ? "yyyy-MM-dd" : patterns[0];
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd）
     */
    public static String formatDate(Date date) {
        return formatDate(date, new Object[]{});
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    public static String cstToStringDate(String cst) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        //java.util.Date对象
        Date date = null;
        String formatStr = null;
        try {
            date = (Date) sdf.parse(cst);
            formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return formatStr;
    }

    /**
     * @return 返回当前日期
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * @return 返回当前日期（yyyy-MM-dd）
     */
    public static String getCurrentDateY_M_D() {
        SimpleDateFormat format = new SimpleDateFormat(DATEPATTERN_YYYY_MM_DD);
        return format.format(getCurrentDate());
    }

    /**
     * @return 返回当前日期（yyyy-MM-dd HH）
     */
    public static String getCurrentDateY_M_D_H() {
        SimpleDateFormat format = new SimpleDateFormat(DATEPATTERN_YYYY_MM_DD_HH);
        return format.format(getCurrentDate());
    }

    /**
     * @return 返回当前日期（yyyy-MM-dd HH:mm）
     */
    public static String getCurrentDateY_M_D_H_M() {
        SimpleDateFormat format = new SimpleDateFormat(DATEPATTERN_YYYY_MM_DD_HH_MM);
        return format.format(getCurrentDate());
    }


    /**
     * @return 返回当前日期（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrentDateY_M_D_H_M_S() {
        SimpleDateFormat format = new SimpleDateFormat(DATEPATTERN_YYYY_MM_DD_HH_MM_SS);
        return format.format(getCurrentDate());
    }

    /**
     * @return 返回当前日期（yyyyMMddHHmmss）
     */
    public static String getCurrentDateYMDHMS() {
        SimpleDateFormat format = new SimpleDateFormat(DATEPATTERN_YYYYMMDDHHMMSS);
        return format.format(getCurrentDate());
    }

    /**
     * @return 返回当前时间（HH:mm）
     */
    public static String getCurrentDateH_M() {
        SimpleDateFormat format = new SimpleDateFormat(DATEPATTERN_HHMM);
        return format.format(getCurrentDate());
    }

    /**
     * @return 返回当前时间（HH:mm:ss）
     */
    public static String getCurrentDateH_M_S() {
        SimpleDateFormat format = new SimpleDateFormat(DATEPATTERN_HHMMSS);
        return format.format(getCurrentDate());
    }

    /**
     * 在日期上增加数个整日
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, n);
        return cal.getTime();
    }

    /**
     * 当前日期时间
     *
     * @return Timestamp
     */
    public static Timestamp getCurrrentDate() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(getNowDate());

    }

}
