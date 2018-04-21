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
public class MerchantUserVo {

  private String userId;
  private String userName;
  private String nickName;
  private String phone;
  private String avatar;
  private String createdAt;
  private String updatedAt;

}
