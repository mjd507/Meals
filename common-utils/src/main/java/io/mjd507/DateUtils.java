package io.mjd507;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mjd on 2018/4/23 20:39
 */
public class DateUtils {

  private static final String FORMAT_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";

  /**
   * 查看是否过期
   */
  public static boolean isExpired(Date date, int minutes) {
    long beforeTime = date.getTime();
    long bufferTime = minutes * 60 * 1000;
    long currTime = new Date().getTime();
    return currTime - beforeTime > bufferTime;
  }

  public static boolean isExpiredNow(Date date) {
    return isExpired(date, 0);
  }

  public static String getFormatStr(long millis) {
    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_PATTERN_1);
    return sdf.format(millis);
  }

  /**
   * 获取与当前时间相距 n 天的时间 day > 0 当前时间之后的 n 天 day < 0 当前时间之前的 n 天
   */
  public static Date getDayFromNow(int day) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.DATE, date.get(Calendar.DATE) + day);
    return new Date(date.getTimeInMillis());
  }

  public static Date getMinFromNow(int minute) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.MINUTE, date.get(Calendar.MINUTE) + minute);
    return new Date(date.getTimeInMillis());
  }

  public static boolean isOver3Clock() {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    return hour >= 15 && minute >= 1;
  }
}
