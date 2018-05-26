package io.mjd507.module.login;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/3/24 13:37
 */
@Setter
@Getter
public class LoginDto {

  private String userId;
  private String token;
  private String phone;
  private String openId;
  private Date expiredAt;
}
