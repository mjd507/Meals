package io.mjd507.module.user;

import java.util.Date;
import lombok.Data;

/**
 * Created by mjd on 2018/5/25 15:19
 */
@Data
public class UserDo {

  private Long id;

  private String userId;

  private String userName;

  private String nickName;

  private String phone;

  private String avatar;

  private String department;

  private String userGroup;

  private Short userRole;

  private Date createdAt;

  private Date updatedAt;

}
