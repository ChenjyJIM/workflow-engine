package com.graduate.engine.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     *
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
     *
     * @return
     */
    public static Long getSecondTimeStampBetweenCurrentAndEndOfDay() {
        Long currentMillSecond = getCurrentMillSecondsTimestamp();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Long endOfTodayMillSecond = calendar.getTime().getTime();

        return (endOfTodayMillSecond - currentMillSecond) / 1000;

    }

    public static Long parseDateYMDhmsToSecondsTimestamp(String dateStr) {
        try {
            Date data = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").parse(dateStr);
            return (data.getTime() / 1000);
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
     *
     * @param timestamp
     * @return
     */
    public static String getDate(long timestamp) {
        return getDateStrByTimestamp(timestamp, "yyyy-MM-dd");
    }

    public static void main(String[] args) {
        String dateString = "2018-08-05T16:00:00.000Z";
        try {
            Long result = DateUtils.getTimeStampByUTC(dateString);
            System.out.println(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String preCallbackTime = "";
//        try {
//            String callbackTimeStart = format.parse(TimeStart).toString();
//            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
//            TimeZone tz = TimeZone.getTimeZone("GMT+8");
//            sdf.setTimeZone(tz);
//            Date parse = sdf.parse(callbackTimeStart);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            preCallbackTime = simpleDateFormat.format(parse);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String TimeStart = time.replace("Z", " UTC");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//        Date callbackTimeStart = format.parse(TimeStart);    //Fri Dec 28 00:00:00 GMT+08:00 2018
    }

    /**
     * 将iview组件日期格式转换成unix时间戳
     * 2018-08-05T16:00:00.000Z --> 1533484800
     * 用法：Long result = DateUtils.getTimeStampByUTC(dateString);
     *
     * @param create_time
     * @return
     * @throws ParseException
     */
    public static Long getTimeStampByUTC(String create_time) throws ParseException {
        String format = "";
        if (create_time != null && create_time != "NULL" && create_time != "") {
            if (isDate(create_time)) {
                format = create_time;
            } else {
                //转换日期格式(将Mon Jun 18 2018 00:00:00 GMT+0800 (中国标准时间) 转换成yyyy-MM-dd)
                create_time = create_time.replace("Z", " UTC");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
                //Mon Mar 06 00:00:00 CST 2017
                Date d = sdf1.parse(create_time);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                format = sdf.format(d);
            }
        }
        return DateUtils.getTimestampByDateStr(format);
    }

    private static boolean isDate(String date) {
        /**
         * 判断日期格式和范围
         */
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(date);
        boolean dateType = mat.matches();
        return dateType;
    }
}
