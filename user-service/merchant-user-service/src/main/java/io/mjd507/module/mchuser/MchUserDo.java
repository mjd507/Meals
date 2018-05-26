package io.mjd507.module.mchuser;

import java.util.Date;
import lombok.Data;

/**
 * Created by mjd on 2018/3/24 13:37
 */
@Data
public class MchUserDo {

  private Long id;

  private String userId;

  private String userName;

  private String nickName;

  private String phone;

  private String avatar;

  private Date createdAt;

  private Date updatedAt;
}
