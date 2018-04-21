package io.mjd507;

import java.security.MessageDigest;

/**
 * Created by mjd on 2018/4/2 19:58
 */
public class MD5Utils {

  private MD5Utils() {
  }

  //十六进制下数字到字符的映射数组
  private final static String[] hexDigits = {"0", "1", "2", "3", "4",
      "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


  public static String encode(String originStr) {
    if (originStr != null) {
      try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(originStr.getBytes("UTF-8"));
        //将得到的字节数组变成字符串返回
        String resultString = byteArrayToHexString(bytes);
        return resultString.toUpperCase();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private static String byteArrayToHexString(byte[] bytes) {
    StringBuilder builder = new StringBuilder();
    for (byte b : bytes) {
      builder.append(byteToHexString(b));
    }
    return builder.toString();
  }

  /**
   * 将一个字节转化成十六进制形式的字符串
   */
  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0) {
      n = 256 + n;
    }
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  public static void main(String args[]) {
    String str = "abc";
    String encodeStr = encode(str);
    System.out.println(encodeStr);
  }

}
