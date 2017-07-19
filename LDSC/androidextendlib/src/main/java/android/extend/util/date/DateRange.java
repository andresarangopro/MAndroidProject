package android.extend.util.date;

import android.extend.data.sqlite.util.DateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期范围，用于查询条件
 * 开始日期，如果传入2000-01-01 13:14:15，则会格式化为：2000-01-01 00:00:00
 * 结束日期，如果传入2000-01-01 13:14:15，则会格式化为：2000-01-02 00:00:00
 * Created by tanqimin on 2016/11/19.
 */

public class DateRange {
    private Date start;
    private Date end;

    private DateRange() {
    }

    /**
     * 创建DateRange对象
     *
     * @param start 开始时间
     * @param end   结束时间（必须大于开始时间）
     */
    public DateRange(
            Date start,
            Date end) {
        if (end.compareTo(start) < 0) throw new RuntimeException("起始时间不能大于结束时间！");

        String startDateStr = DateTimeUtil.convertToString(start, DateFormat.DATE);
        String endDateStr   = DateTimeUtil.convertToString(end, DateFormat.DATE);
        try {
            this.start = DateTimeUtil.convertToDate(startDateStr + " 00:00:00.000", DateFormat.DATE_TIME);
            this.end = DateTimeUtil.getDateAfterDay(DateTimeUtil.convertToDate(endDateStr + " 00:00:00.000", DateFormat.DATE_TIME), 1);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取开始时间
     *
     * @return
     */
    public Date getStart() {
        return start;
    }

    /**
     * 获取结束时间
     *
     * @return
     */
    public Date getEnd() {
        return end;
    }

    /**
     * 判断日期是否在声明的时间范围内
     *
     * @param date 日期
     * @return
     */
    public boolean inRange(Date date) {
        if (start.compareTo(date) > 0) return false;
        if (end.compareTo(date) < 0) return false;
        return true;
    }
}
