package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/4/16 20:57
 */
@Getter
@Setter
public class MealVo {

  private int mealId;
  private int merchantId;
  private String name;
  private String picUrl;
  private String desc;
  private String price;

}
