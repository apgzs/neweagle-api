package com.neweagle.api.comm.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author zhangxd
 */
public class DateHelper extends DateUtils {

    /**
     * 日期格式
     */
    private static final String[] PARSE_PATTERNS = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"
    };

    /**
     * 日期格式
     */
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期时间格式
     */
    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     *
     * @return the date
     */
    public static String getDate() {
        return getDate(DEFAULT_DATE_FORMAT);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @param pattern the pattern
     * @return the date
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @param date    the date
     * @param pattern the pattern
     * @return the string
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, DEFAULT_DATE_FORMAT);
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     *
     * @param date the date
     * @return the string
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     *
     * @return the time
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     *
     * @return the date time
     */
    public static String getDateTime() {
        return formatDate(new Date(), DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     *
     * @return the year
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     *
     * @return the month
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     *
     * @return the day
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     *
     * @return the week
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     *
     * @param str the str
     * @return the date
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), PARSE_PATTERNS);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 描述：字符串转时间戳
     * @param s
     * @return
     * @throws Exception
     */
    public static Long getTimeBtString(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sdf.parse(s).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取过去的天数
     *
     * @param date 对比日期
     * @return long long
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date 对比日期
     * @return long long
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date 对比日期
     * @return long long
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis 毫秒数
     * @return 天, 时:分:秒.毫秒
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = timeMillis / (60 * 60 * 1000) - day * 24;
        long min = timeMillis / (60 * 1000) - day * 24 * 60 - hour * 60;
        long s = timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        long sss = timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000;
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before 开始日期
     * @param after  结束日期
     * @return 天数 distance of two date
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (double) (1000 * 60 * 60 * 24);
    }

    /**
     * 获取东八区当前时间
     *
     * @return Date est 8 date
     */
    public static Date getEst8Date() {
        TimeZone tz = TimeZone.getTimeZone("GMT+8:00");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        dateFormat.setTimeZone(tz);
        return parseDate(dateFormat.format(date));
    }

    /**
     * 描述：获得秒级时间戳
     *
     * @return
     */
    public static Long getCurrentTimeMillis() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 描述：时间间隔工具类
     *
     * @param d
     * @return
     */
    public static String getPastTime(long d) {
        DateFormat ddtf = DateFormat.getDateTimeInstance();
        long delta = (new Date().getTime() - d) / 1000;
        if (delta < 0) return ddtf.format(d);
        if (delta / (60 * 60 * 24 * 365) > 0) return delta / (60 * 60 * 24 * 365) + "年前";
        if (delta / (60 * 60 * 24 * 30) > 0) return delta / (60 * 60 * 24 * 30) + "个月前";
        if (delta / (60 * 60 * 24 * 7) > 0) return delta / (60 * 60 * 24 * 7) + "周前";
        if (delta / (60 * 60 * 24) > 0) return delta / (60 * 60 * 24) + "天前";
        if (delta / (60 * 60) > 0) return delta / (60 * 60) + "小时前";
        if (delta / (60) > 0) return delta / (60) + "分钟前";
        return "刚刚";
    }

    /**
     * 描述：时间戳转Date
     *
     * @param stamp
     * @return
     */
    public static Date stampToDate(long stamp) {
        Date date = new Date(stamp);
        return date;
    }

    /**
     * 描述：指定日期获得所在周的开始时间
     * @param stamp
     * @return
     * @throws Exception
     */
    public static Date getBeginWeek(long stamp) throws Exception {
        Calendar cal = Calendar.getInstance();
        Date date = new Date(stamp);
        cal.setTime(date);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        return cal.getTime();
    }

    /**
     * 描述：指定当前日期加减
     * @param stamp
     * @param day
     * @return
     */
    public static Date dateAdd(long stamp,int day){
        Date date = new Date(stamp);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";

    /***
     *
     * @param date 时间
     * @return  cron类型的日期
     */
    public static String dateToCron(final Date  date){
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        String formatTimeStr = "";
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     *
     * @param cron Quartz cron的类型的日期
     * @return  Date日期
     */

    public static Date cronToDate(final String cron) {


        if(cron == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(cron);
        } catch (ParseException e) {
            return null;// 此处缺少异常处理,自己根据需要添加
        }
        return date;
    }

    //判断选择的日期是否是本周
    public static boolean isThisWeek(long time)
    {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if(paramWeek==currentWeek){
            return true;
        }
        return false;
    }
    //判断选择的日期是否是今天
    public static boolean isToday(long time)
    {
        return isThisTime(time,"yyyy-MM-dd");
    }
    //判断选择的日期是否是本月
    public static boolean isThisMonth(long time)
    {
        return isThisTime(time,"yyyy-MM");
    }
    private static boolean isThisTime(long time,String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if(param.equals(now)){
            return true;
        }
        return false;
    }

    /**
     * 描述:获得本月开始时间秒级时间戳
     * @return
     */
    public static long getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return  cal.getTime().getTime()/1000;
    }


    public static Date getAfterWorkDate(Date currentDate, int days){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(currentDate);
        int i=0;
        while(i<days){
            calendar.add(Calendar.DATE,1);
            i++;
            if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
                i--;
            }
        }
        return calendar.getTime();
    }

    /**
     * 描述：将秒数转化为易读的时分秒
     * @param seconds
     * @return
     */
    public static String formatSecond(long seconds) {
        long day=seconds/(60*60*24);//换成天
        long hour=(seconds-(60*60*24*day))/3600;//总秒数-换算成天的秒数=剩余的秒数    剩余的秒数换算为小时
        long minute=(seconds-60*60*24*day-3600*hour)/60;//总秒数-换算成天的秒数-换算成小时的秒数=剩余的秒数    剩余的秒数换算为分
        //long second=seconds-60*60*24*day-3600*hour-60*minute;//总秒数-换算成天的秒数-换算成小时的秒数-换算为分的秒数=剩余的秒数
        String result=day+"天"+hour+"小时"+minute+"分钟";
        if (day==0){
            result = result.replace("0天","");
        }
        if (hour==0){
            result = result.replace("0小时","");
        }
        if (minute==0){
            result = result.replace("0分钟","");
        }
        return result;
    }

    /**
     * 描述：获得几个月后的秒级时间戳
     * @param monehCount
     * @return
     */
    public static Long afterMonthTime(int monehCount){
        Calendar c=Calendar.getInstance();
        Date date=new Date();
        c.setTime(date);
        c.add(Calendar.MONTH,monehCount); //将当前日期加一个月
        return c.getTime().getTime()/1000;
    }

    /**
     * 描述：获得今天开始时间秒级时间戳
     * @return
     */
    public static Long getTodayBeginTime(){
            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime().getTime()/1000;
    }
}
