package io.mjd507.merchant.entity;

import lombok.Data;

/**
 * Created by mjd on 2018/4/27 09:26
 */
@Data
public class MealsVo {

  private Long id;

  private String mealName;

  private String mealPic;

  private String mealDesc;

  private String mealPrice;
}
