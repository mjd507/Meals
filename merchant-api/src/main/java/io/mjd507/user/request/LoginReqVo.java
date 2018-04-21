package io.mjd507.user.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/4/17 19:33
 */
@Setter
@Getter
public class LoginReqVo {

  private String code; // 微信 code
  private String phone;
  private String verifyCode;
}
