package io.mjd507.sms;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/4/23 20:11
 */
@Getter
@Setter
public class PhoneCodeVo {

  private String phone;
  private String verifyCode;
  private String status;
  private String expireAt;
}
