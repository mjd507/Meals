package io.mjd507;

import java.util.Date;
import lombok.Data;

/**
 * Created by mjd on 2018/5/27 15:04
 */
@Data
public class OrderDo {

  private Long id;

  private String userId;

  private String mchId;

  private String mchName;

  private String mealName;

  private Date createdAt;

  private Date updatedAt;

}
