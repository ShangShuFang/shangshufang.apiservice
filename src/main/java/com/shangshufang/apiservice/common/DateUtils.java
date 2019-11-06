package com.shangshufang.apiservice.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    final static String DEFAULT_PATTERN = "MM-dd-yyyy HH:mm:ss";

    public static String getCurrentDateTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Date convert(String value) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(value);
    }

    public static String getRecentMonthDateTime(int recentMonth){
        GregorianCalendar gc=new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        gc.add(2,-recentMonth);

        return simpleDateFormat.format(gc.getTime());
    }

    /**
     * 对比date1与date2两个时间之间的分钟差
     * @param fromDate 开始时间
     * @param toDate 戒指时间
     * @return 返回两个时间的分钟差值
     */
    public static DataDifference difference(String fromDate, String toDate) throws ParseException {
        DataDifference dataDifference = new DataDifference();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date data1 = format.parse(fromDate);
        Date date2 = format.parse(toDate);
        long between = date2.getTime() - data1.getTime();

        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long seconds = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        dataDifference.setDay(day);
        dataDifference.setHour(hour);
        dataDifference.setMin(min);
        dataDifference.setSeconds(seconds);

        return dataDifference;
    }

    /**
     * 将累计的天、小时、分钟、秒转为标准的时间
     * @param totalDay 天
     * @param totalHour 小时
     * @param totalMinutes 分钟
     * @param totalSeconds 分钟
     * @return 标准时间
     */
    public static DataDifference convertToStandard(long totalDay, long totalHour, long totalMinutes, long totalSeconds){
        DataDifference standardTime = new DataDifference();
        long tempDays;
        long tempHours;
        long tempMinutes;

        long standardSeconds;
        long standardMinutes;
        long standardHours;


        //根据总秒数计算总分钟和标准秒数
        tempMinutes = totalMinutes + (long)Math.floor(totalSeconds / 60);
        standardSeconds = totalSeconds % 60;

        //根据总分钟计算总小时标准分钟
        tempHours = totalHour + (long)Math.floor(tempMinutes / 60);
        standardMinutes = tempMinutes % 60;

        //根据总小时计算总天数和标准小时
        tempDays = totalDay + (long)Math.floor(tempHours / 24);
        standardHours = tempHours % 24;

        standardTime.setDay(tempDays);
        standardTime.setHour(standardHours);
        standardTime.setMin(standardMinutes);
        standardTime.setSeconds(standardSeconds);

        return standardTime;
    }
}
