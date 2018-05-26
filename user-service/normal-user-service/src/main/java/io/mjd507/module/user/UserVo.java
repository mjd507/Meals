package io.mjd507.module.user;

import lombok.Data;

/**
 * Created by mjd on 2018/3/24 13:37
 */
@Data
public class UserVo {

  private String userName;

  private String nickName;

  private String phone;

  private String avatar;

  private String department;

  private String userGroup;

  private Short userRole;

  private String extra; // 在登录成功后，存放 token
}
