package io.mjd507.module.user;

import lombok.Data;

/**
 * Created by mjd on 2018/5/25 16:58
 */
@Data
public class UserDto {

  private String userId;

  private String userName;

  private String nickName;

  private String phone;

  private String avatar;

  private String department;

  private String userGroup;

  private Short userRole;

}
