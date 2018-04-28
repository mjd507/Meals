package io.mjd507.utils;

import io.mjd507.MD5Utils;

/**
 * Created by mjd on 2018/4/1 18:37
 */
public final class TokenUtils {

  private TokenUtils() {
  }


  public static String buildUserId(String token) {
    String s = new StringBuilder().append(token).reverse().toString();
    return MD5Utils.encode(s);
  }

  public static void main(String args[]) {
    String s = buildUserId("13862124216");
    System.out.println(s);
  }
}
