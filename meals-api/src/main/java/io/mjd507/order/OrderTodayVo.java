package io.mjd507.order;

import java.util.Date;
import lombok.Data;

/**
 * Created by mjd on 2018/6/6 22:33
 */
@Data
public class OrderTodayVo {

  private Long id;

  private String mchName;

  private String mealName;

  private Date createdAt;

  // user

  private String userName;

  private String nickName;

  private String avatar;

  private String department;

  private String userGroup;

}
