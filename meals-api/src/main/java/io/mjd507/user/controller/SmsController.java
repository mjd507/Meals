package io.mjd507.user.controller;

import io.mjd507.common.ConfigProperties;
import io.mjd507.common.DataResponse;
import io.mjd507.common.IPUtils;
import io.mjd507.module.sms.SmsService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

  @RequestMapping(value = "sendSms", method = RequestMethod.POST)
  @ResponseBody
  public DataResponse<String> sendSms(HttpServletRequest request, @RequestParam String phone) {
    String result;
    // 限制 ip 访问
    String realIp = IPUtils.getRealIp(request);
    if (!ConfigProperties.IpWhiteList.contains(realIp)) {
      logger.warn("访问者 ip 为：{}", realIp);
      result = "ip 不在服务区";
    } else {
      result = smsService.sendSms(phone);
    }
    return new DataResponse<>(result);
  }
}
