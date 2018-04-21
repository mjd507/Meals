package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by mjd on 2018/3/24 13:37
 */
@Getter
@Setter
@ToString
public class UserVo {

  private String userId;
  private String userName;
  private String nickName;
  private String phone;
  private String avatar;
  private String department; // 研发，产品
  private String group; // Java, Js
  private String userType; // 用户类型：普通用户，商家，审核者，小组长
  private String createdAt;
  private String updatedAt;
}
