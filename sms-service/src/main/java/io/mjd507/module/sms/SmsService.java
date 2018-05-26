package io.mjd507.module.sms;

import com.google.common.base.Preconditions;
import io.mjd507.DateUtils;
import io.mjd507.http.HttpRequest;
import io.mjd507.http.HttpUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信服务 (向手机用户发送短信验证码，验证手机号验证码)
 *
 * Created by mjd on 2018/4/23 19:33
 */
@Service
public class SmsService {

  private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

  // 过期时间 30 分钟
  private static final int expiredTime = 30;

  @Autowired
  SmsServiceMapper smsServiceMapper;

  /**
   * 发送短信
   *
   * @param phone 手机号
   */
  public String sendSms(String phone) {
    String verifyCode = SmsUtils.getRandomSixNumber();

    VCodeDo vCodeDo = smsServiceMapper.findByPhone(phone);

    if (vCodeDo == null) { // 新用户
      SmsBo smsBo = sendSmsByThird(phone, verifyCode);
      if (smsBo == null || smsBo.getError_code() != 0) {
        return SendStatus.FAILURE.getDesc();
      }
      VCodeDo codeDo = new VCodeDo();
      codeDo.setPhone(phone);
      codeDo.setVCode(verifyCode);
      codeDo.setExpiredAt(DateUtils.getMinFromNow(expiredTime));
      smsServiceMapper.insertVCode(codeDo);
      return SendStatus.SUCCESS.getDesc();
    }

    Date expireDate = vCodeDo.getExpiredAt();
    boolean isExpired = DateUtils.isExpiredNow(expireDate);
    if (!isExpired) { // 没过期
      return SendStatus.STILL_VALID.getDesc();
    } else { // 失效，重新发送
      SmsBo smsBo = sendSmsByThird(phone, verifyCode);
      if (smsBo == null || smsBo.getError_code() != 0) {
        return SendStatus.FAILURE.getDesc();
      }
      vCodeDo.setVCode(verifyCode);
      vCodeDo.setExpiredAt(DateUtils.getMinFromNow(expiredTime));
      smsServiceMapper.updateBySelective(vCodeDo);
      return SendStatus.SUCCESS.getDesc();
    }
  }

  /**
   * 请求聚合数据给指定手机发送指定验证码
   */
  private SmsBo sendSmsByThird(String phone, String verifyCode) {
    // 发送验证码给用户
    try {
      HttpRequest request = new HttpRequest();
      request.setUrl("http://v.juhe.cn/sms/send");
      Map<String, String> params = new HashMap<>();
      params.put("mobile", phone);
      params.put("tpl_id", "73553"); // 短信模板 ID

      params.put("tpl_value", URLEncoder.encode("#code#=", "UTF-8") + verifyCode); // 自定义的验证码

      params.put("key", "6ecffa3f9a772f2e7a1402bd0835280f");
      request.setParams(params);
      return HttpUtils.doRequest(request, SmsBo.class);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 验证手机号 验证码
   *
   * true 验证通过
   */
  public boolean isVCodeValid(String phone, String code) {
    VCodeDo codeDo = smsServiceMapper.findByPhone(phone);
    Preconditions.checkNotNull(codeDo, "invalid phone");
    Date expireDate = codeDo.getExpiredAt();
    boolean isExpired = DateUtils.isExpiredNow(expireDate);
    if (isExpired) {
      return false;
    }
    VCodeDo validSign = smsServiceMapper.findValid(phone, code);
    return validSign != null;
  }

}
