package io.mjd507;

import lombok.Data;

/**
 * Created by mjd on 2018/5/27 10:35
 */
@Data
public class MchMetaDto {

  private String mchUserId;

  private String mchId;

  private String mchName;

  private String mchLogo;

  private String mchDesc;

  private String mchLocation;

  private Short isActive;

}
