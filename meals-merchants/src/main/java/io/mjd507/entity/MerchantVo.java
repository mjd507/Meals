/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 商家信息
 *
 * @author mjd
 * @date 2018/4/16 20:36
 */
@Setter
@Getter
public class MerchantVo {

  private int id;
  private String name;
  private String logoUrl;
  private String desc;
  private String location;

}
