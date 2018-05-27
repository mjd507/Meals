package io.mjd507;

import java.util.Date;
import lombok.Data;

/**
 * Created by mjd on 2018/5/27 13:41
 */
@Data
public class MealDo {

  private Long id;

  private String mchId;

  private String mealName;

  private String mealPic;

  private String mealDesc;

  private String mealPrice;

  private Date createdAt;

  private Date updatedAt;
}
