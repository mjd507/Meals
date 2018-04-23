package io.mjd507;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mjd on 2018/4/23 20:39
 */
public class DateUtils {

  public static void main(String args[]) {
    System.out.println(isExpireByDateAndMin("2018-04-23 20:30:06", 30));
  }
  /**
   * 数据库日期转毫秒
   *
   * @param date "2018-04-23 20:24:06"
   */
  public static long toMilliseconds(String date) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
  public static boolean isExpireByDateAndMin(String date, int minutes) {
    long sendTime = toMilliseconds(date);
    long expireTime = getMilliByMin(30);
    long currTime = new Date().getTime();
    return sendTime + expireTime < currTime;
  }


}
