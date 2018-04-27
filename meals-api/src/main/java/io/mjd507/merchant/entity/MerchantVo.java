package io.mjd507.merchant.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/4/24 21:59
 */
@Setter
@Getter
public class MerchantVo {

  private String merchantId;
  private String name;
  private String logo;
  private String desc;
  private String location;

}
