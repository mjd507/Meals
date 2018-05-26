package io.mjd507.module.sms;

/**
 * Created by mjd on 2018/5/26 16:57
 */
public enum SendStatus {
  SUCCESS("发送成功"),
  FAILURE("发送失败"),
  STILL_VALID("验证码仍有效，请勿重复发送");
  private String desc;

  SendStatus(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}
