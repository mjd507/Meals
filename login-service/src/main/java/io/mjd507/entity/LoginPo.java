package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/5/20 09:20
 */
@Getter
@Setter
public class LoginPo {

  private long id;
  private String userId;
  private String token;
  private String phone;
  private String openId;
  private String expireAt;
  private String createAt;
  private String updateAt;

}
