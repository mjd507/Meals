package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 手机号-验证码 映射表实体
 * Created by mjd on 2018/4/23 20:11
 */
@Getter
@Setter
public class PhoneCodeDo {

  private String phone;
  private String verifyCode;
  private String status;
  private String expireAt;
}
