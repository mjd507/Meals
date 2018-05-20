package io.mjd507.utils;

import io.mjd507.MD5Utils;
import java.util.UUID;

/**
 * Created by mjd on 2018/4/1 18:37
 */
public final class TokenUtils {

  private TokenUtils() {
  }

  public static String buildUserIdByPhone(String phone) {
    String s = new StringBuilder().append(phone).reverse().toString();
    return MD5Utils.encode(s);
  }

  public static String buildUserIdByOpenId(String openId) {
    String s = new StringBuilder().append(openId).reverse().toString();
    return MD5Utils.encode(s);
  }

  public static String buildToken() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static void main(String args[]) {
    String s = buildUserIdByPhone("13862124216");
    System.out.println(s);
  }
}
