package io.mjd507.module.login;

import java.util.Date;
import lombok.Data;

/**
 * Created by mjd on 2018/5/20 09:20
 */
@Data
public class LoginDo {

  private long id;
  private String userId;
  private String token;
  private String phone;
  private String openId;
  private Date expiredAt;
  private Date createdAt;
  private Date updatedAt;

}
