/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.merchants.request;

import io.mjd507.entity.MealVo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mjd
 * @date 2018/4/18 20:35
 */
@Getter
@Setter
public class MealReqVo {

  private int merchantId;
  private MealVo mealVo;

}
