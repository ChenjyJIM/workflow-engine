package com.graduate.engine.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    private static final String[] DATE_FORMAT_ARR = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd", "yyyy-MM", "yyyy-MM-dd HH:mm:ss.S", "yyyy年MM月dd日", "yyyy年MM月dd日 HH:mm", "yyyyMMdd",
            "yyyy年MM月dd日 HH:mm:ss", "MM.dd"};

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 获取字符串日期
     *
     * @return
     */
    public static String getDateString() {
        return DATE_FORMAT.format(new Date());
    }

    public static Long getCurrentSecondTimestamp() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    public static Long getCurrentMillSecondsTimestamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static String getDateStrByTimestamp(long timestamp) {
        return getDateStrByTimestamp(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateStrByTimestamp(long timestamp, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(timestamp * 1000));
    }

    /**
     * 返回秒级的时间戳
     * @param dateStr
     * @return
     */
    public static Long getTimestampByDateStr(String dateStr) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, DATE_FORMAT_ARR).getTime() / 1000;
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean validateQueryTime(Long startTime, Long endTime) {

        if (startTime != null && endTime != null && endTime > startTime) {
            return true;
        }

        return false;
    }

    public static String format(Date date, String tpl) {
        SimpleDateFormat format = new SimpleDateFormat(tpl);
        return format.format(date);
    }


    public static boolean isOverAWeek(Long timeRange) {
        Long aWeek = 60 * 60 * 24 * 7L;

        if (timeRange > aWeek) {
            return true;
        }

        return false;
    }

    /**
     * 获取到本日结束时间（秒）
     * @return
     */
    public static Long getSecondTimeStampBetweenCurrentAndEndOfDay(){
        Long currentMillSecond = getCurrentMillSecondsTimestamp();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Long endOfTodayMillSecond = calendar.getTime().getTime();

        return (endOfTodayMillSecond-currentMillSecond) /1000;

    }

    public static Long parseDateYMDhmsToSecondsTimestamp(String dateStr){
        try {
            Date data = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").parse(dateStr);
            return (data.getTime()/1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTimeByTimeStamp(long timestamp) {
        return getDateStrByTimestamp(timestamp, "HH:mm:ss");
    }

    /**
     * 仅获取日期
     * @param timestamp
     * @return
     */
    public static String getDate(long timestamp) {
        return getDateStrByTimestamp(timestamp, "yyyy-MM-dd");
    }

    public static void main(String[] args) {
        Long time = getCurrentMillSecondsTimestamp();
        System.out.println(getDate(time/1000));
    }
}
