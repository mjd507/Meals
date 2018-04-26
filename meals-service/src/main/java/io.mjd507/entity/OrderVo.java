package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/4/26 20:44
 */
@Getter
@Setter
public class OrderVo {

  private String id;
  private String userId;
  private String merchantId;
  private String merchantName;
  private String mealsIds;
  private String mealsNames;
  private String createdAt;

}
