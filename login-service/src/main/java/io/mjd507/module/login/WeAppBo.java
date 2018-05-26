package io.mjd507.module.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by mjd on 2018/3/24 13:37
 */
@Getter
@Setter
@ToString
public class WeAppBo {

  private String openid; //用户唯一标识
  private String session_key;
  private String errcode;
  private String errmsg;
}
