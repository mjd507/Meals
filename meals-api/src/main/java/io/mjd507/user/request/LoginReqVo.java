/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.user.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mjd
 * @date 2018/4/17 19:33
 */
@Setter
@Getter
public class LoginReqVo {

  private String code; // 微信 code
  private String phone;
  private String verifyCode;
}
