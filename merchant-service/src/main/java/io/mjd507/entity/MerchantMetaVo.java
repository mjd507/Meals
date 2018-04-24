package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 商家元数据
 *
 * Created by mjd on 2018/4/16 20:36
 */
@Setter
@Getter
public class MerchantMetaVo {

  private String merchantId;
  private String name;
  private String logo;
  private String desc;
  private String location;
  private String isActive;
}
