package io.mjd507;

import java.util.Date;
import lombok.Data;

/**
 * Created by mjd on 2018/4/26 20:44
 */
@Data
public class OrderVo {

  private Long id;

  private String mchId;

  private String mchName;

  private String mealName;

  private Date createdAt;

}
