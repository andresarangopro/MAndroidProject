package android.extend.util.date;

import android.extend.data.sqlite.util.DateFormat;
import android.extend.data.sqlite.util.ValidateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanqimin on 2016/11/19.
 */

public class DateTimeUtil {
    private static Map<String, SimpleDateFormat> cache = new HashMap();

    public DateTimeUtil() {
    }

    public static String convertToString(Date date) {
        return convertToString(date, DateFormat.DATE_TIME);
    }

    public static String convertToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat;
        if (cache.containsKey(format)) {
            simpleDateFormat = cache.get(format);
        } else {
            simpleDateFormat = new SimpleDateFormat(format);
            cache.put(format, simpleDateFormat);
        }

        return simpleDateFormat.format(date);
    }

    public static Date convertToDate(String dateStr) throws ParseException {
        return convertToDate(dateStr, DateFormat.DATE_TIME);
    }

    public static Date convertToDate(String dateStr, String format) throws ParseException {
        if (ValidateUtil.isBlank(dateStr)) {
            return null;
        } else {
            SimpleDateFormat simpleDateFormat;
            if (cache.containsKey(format)) {
                simpleDateFormat = cache.get(format);
            } else {
                simpleDateFormat = new SimpleDateFormat(format);
                cache.put(format, simpleDateFormat);
            }

            return simpleDateFormat.parse(dateStr);
        }
    }

    /**
     * 获取几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfterDay(
            Date d,
            int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
}