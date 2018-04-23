package io.mjd507.sms;

import io.mjd507.DateUtils;
import io.mjd507.dao.SmsServiceMapper;
import io.mjd507.http.HttpRequest;
import io.mjd507.http.HttpUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/23 19:33
 */
@Service
public class SmsService {

  @Autowired
  SmsServiceMapper smsServiceMapper;

  /**
   * 发送短信
   *
   * @param phone 手机号
   */
  public String sendSms(String phone) {
    String verifyCode = SmsUtils.getRandomSixNumber();
    try {
      // 将手机号 验证码 持久化到数据库
      PhoneCodeVo phoneCodeVo = smsServiceMapper.findPhone(phone);
      if (phoneCodeVo != null) { // 存在此号码
        String expireAt = phoneCodeVo.getExpireAt();
        boolean isExpired = DateUtils.isExpireByDateAndMin(expireAt, 30);// 30 分钟内有效
        if (!isExpired && phoneCodeVo.getStatus().equals("1")) {
          return "验证码仍有效，请勿重复发送";
        } else {
          smsServiceMapper.updatePhoneCode(phone, verifyCode, "1"); // 更新验证码
        }
      } else {
        smsServiceMapper.insertPhoneCode(phone, verifyCode); // 插入验证码
      }
      // 发送验证码给用户
      HttpRequest request = new HttpRequest();
      request.setUrl("http://v.juhe.cn/sms/send");
      Map<String, String> params = new HashMap<>();
      params.put("mobile", phone);
      params.put("tpl_id", "73553"); // 短信模板 ID
      params.put("tpl_value", URLEncoder.encode("#code#=", "UTF-8") + verifyCode); // 自定义的验证码
      params.put("key", "6ecffa3f9a772f2e7a1402bd0835280f");
      request.setParams(params);
      SmsVo smsVo = HttpUtils.doRequest(request, SmsVo.class);
      if (smsVo.getError_code() == 0) {
        return "发送成功";
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      smsServiceMapper.updatePhoneCode(phone, verifyCode, "0"); // 更新验证码状态
    }
    return "发送失败";
  }

  public boolean hasPhoneAndCode(String phone, String code) {
    PhoneCodeVo validSign = smsServiceMapper.findValidSign(phone, code);
    return validSign != null;
  }

}
