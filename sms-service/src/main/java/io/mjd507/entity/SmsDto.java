package io.mjd507.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 聚合数据返回的短信内容实体
 * Created by mjd on 2018/4/23 19:38
 */
@Getter
@Setter
public final class SmsDto {

  /**
   * reason : 短信发送成功
   * result : {"count":1,"fee":1,"sid":"23d6bc4913614919a823271d820662af"}
   * error_code : 0
   */

  private String reason;
  private ResultBean result;
  private int error_code;

  @Getter
  @Setter
  private static final class ResultBean {

    /**
     * count : 1
     * fee : 1
     * sid : 23d6bc4913614919a823271d820662af
     */

    private int count;
    private int fee;
    private String sid;
  }
}
