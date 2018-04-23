package io.mjd507.user.controller;

import io.mjd507.common.request.DataResponse;
import io.mjd507.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/23 19:50
 */
@RestController
public class SmsController {

  @Autowired
  SmsService smsService;

  @RequestMapping(value = "sendSms")
  public DataResponse<String> sendSms(String phone) {
    String result = smsService.sendSms(phone);
    return new DataResponse<>(result);
  }
}
