package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/3/24 13:37
 */
@Setter
@Getter
public class LoginVo {

  private String userId;
  private String openId;
  private String phone;
  private String expireAt;

}
