package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserVo {

  private String userId;
  private String userName;
  private String nickName;
  private String phone;
  private String userAvatar;
  private String department; // 研发，产品
  private String group; // Java, Js
  private String isGroupLeader; // 小组长，有权限统计该组人数
  private String openId; // 微信保留字段
  private String createdAt;
  private String updatedAt;
}
