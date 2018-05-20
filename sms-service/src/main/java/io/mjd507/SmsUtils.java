package io.mjd507;

import java.util.Random;

/**
 * Created by mjd on 2018/4/23 19:58
 */
public class SmsUtils {

  public static String getRandomSixNumber() {
    int flag = new Random().nextInt(999999);
    if (flag < 100000) {
      flag += 100000;
    }
    return flag + "";
  }
}
