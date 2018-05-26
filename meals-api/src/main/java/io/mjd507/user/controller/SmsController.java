package io.mjd507.user.controller;

import io.mjd507.common.DataResponse;
import io.mjd507.module.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/23 19:50
 */
@RestController
public class SmsController {

  @Autowired
  SmsService smsService;

  @RequestMapping(value = "sendSms", method = RequestMethod.POST)
  @ResponseBody
  public DataResponse<String> sendSms(@RequestParam String phone) {
    String result = smsService.sendSms(phone);
    return new DataResponse<>(result);
  }
}
