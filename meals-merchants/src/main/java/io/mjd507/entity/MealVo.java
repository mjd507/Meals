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
 * 菜单
 *
 * @author mjd
 * @date 2018/4/16 20:57
 */
@Getter
@Setter
public class MealVo {

  private int merchantId;
  private int mealId;
  private String mealName;
  private String mealPicUrl;
  private String mealDesc;
  private String mealPrice;

}
