package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginVo {

  private String userId;
  private String openId;
  private String phone;
  private String expireAt;

}
