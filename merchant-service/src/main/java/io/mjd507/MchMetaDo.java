package io.mjd507;

import java.util.Date;
import lombok.Data;

/**
 * Created by mjd on 2018/5/27 09:34
 */
@Data
public class MchMetaDo {

  private Long id;

  private String mchUserId;

  private String mchId;

  private String mchName;

  private String mchLogo;

  private String mchDesc;

  private String mchLocation;

  private Short isActive;

  private Date createdAt;

  private Date updatedAt;

}
