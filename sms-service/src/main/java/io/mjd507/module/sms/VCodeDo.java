package io.mjd507.module.sms;

import java.util.Date;
import lombok.Data;

/**
 * 手机号-验证码 映射表实体
 * Created by mjd on 2018/4/23 20:11
 */
@Data
public class VCodeDo {

  private Long id;

  private String phone;

  private String vCode;

  private Date expiredAt;

  private Date createdAt;

  private Date updatedAt;
}
