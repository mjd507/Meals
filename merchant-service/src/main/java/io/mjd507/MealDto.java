package io.mjd507;

import lombok.Data;

/**
 * Created by mjd on 2018/5/27 13:55
 */
@Data
public class MealDto {

  private Long id;

  private String mchId;

  private String mealName;

  private String mealPic;

  private String mealDesc;

  private String mealPrice;

}
