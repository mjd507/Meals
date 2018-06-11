package io.mjd507;

import java.security.GeneralSecurityException;

/**
 * Created by mjd on 2018/6/9 22:02
 */
public class SimpleEmail {

  public static void main(String[] args) throws GeneralSecurityException {

    String fromEmail = "mjd507@qq.com";
    String mailAuth = "ahjjadyzdorhbcdg";
    String toEmail = "836240219@qq.com";

    EmailUtil.sendEmail(fromEmail, mailAuth, toEmail, "今日订餐统计",
        "100荤100素");
  }
}
