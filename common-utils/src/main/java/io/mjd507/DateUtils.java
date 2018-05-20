package io.mjd507;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mjd on 2018/4/23 20:39
 */
public class DateUtils {

  public static void main(String args[]) {
    // System.out.println(isExpired("2018-04-23 20:30:06", 30));
  }

  private static final String FORMAT_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";

  /**
   * 数据库日期转毫秒
   *
   * @param date "2018-04-23 20:24:06"
   */
  public static long toMilliseconds(String date) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_PATTERN_1);
      Date date1 = sdf.parse(date);
      return date1.getTime();
    } catch (ParseException e) {
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * 获取指定分钟的毫秒值
   */
  public static long getMilliByMin(int minutes) {
    return minutes * 60 * 1000;
  }

  /**
   * 查看数据库中的日期是否过期
   */
  public static boolean isExpired(String date, int minutes) {
    long beforeTime = toMilliseconds(date);
    long bufferTime = getMilliByMin(minutes);
    long currTime = new Date().getTime();
    return currTime - beforeTime > bufferTime;
  }

  public static String getFormatStr(long millis) {
    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_PATTERN_1);
    return sdf.format(millis);
  }

  public static String getFormatStr(long millis, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(millis);
  }

  /**
   * 获取与当前时间相距 n 天的时间
   * day > 0 当前时间之后的 n 天
   * day < 0 当前时间之前的 n 天
   */
  public static long getDayFromNow(int day) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.DATE, date.get(Calendar.DATE) + day);
    return date.getTimeInMillis();
  }

  /**
   * 获取与当前时间相距 n 月的时间
   * month > 0 当前时间之后的 n 月
   * month < 0 当前时间之前的 n 月
   */
  public static long getMonthFromNow(int month) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.MONTH, date.get(Calendar.MONTH) + month);
    return date.getTimeInMillis();
  }

  /**
   * 获取与当前时间相距 n 年的时间
   * year > 0 当前时间之后的 n 年
   * year < 0 当前时间之前的 n 年
   */
  public static long getYearFromNow(int year) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.YEAR, date.get(Calendar.YEAR) + year);
    return date.getTimeInMillis();
  }

}
