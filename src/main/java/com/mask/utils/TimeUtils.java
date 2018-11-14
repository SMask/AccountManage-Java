package com.mask.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * TimeUtils
 */
public class TimeUtils {

    /**
     * 获取日期 yyyy-MM-dd
     *
     * @param timeMillis timeMillis
     * @return String
     */
    public static String getDate(long timeMillis) {
        return format("yyyy-MM-dd", timeMillis);
    }

    /**
     * 获取日期时间 yyyy-MM-dd HH:mm:ss
     *
     * @param timeMillis timeMillis
     * @return String
     */
    public static String getDateTime(long timeMillis) {
        return format("yyyy-MM-dd HH:mm:ss", timeMillis);
    }

    /**
     * 格式化
     *
     * @param pattern    pattern
     * @param timeMillis timeMillis
     * @return String
     */
    public static String format(String pattern, long timeMillis) {
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return dateFormat.format(new Date(timeMillis));
    }

}
