package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 微信小程序 session 实体
 * @author mjd
 * @date 2018/3/25 16:05
 */
@Getter
@Setter
@ToString
public class WeAppSession {

  private String openid; //用户唯一标识
  private String session_key;
  private String errcode;
  private String errmsg;
}
