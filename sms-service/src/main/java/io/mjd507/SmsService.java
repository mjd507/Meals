package io.mjd507;

import com.google.common.base.Preconditions;
import io.mjd507.dao.SmsServiceMapper;
import io.mjd507.entity.PhoneCodeDo;
import io.mjd507.entity.SmsDto;
import io.mjd507.http.HttpRequest;
import io.mjd507.http.HttpUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信服务 (向手机用户发送短信验证码，验证手机号验证码)
 *
 * Created by mjd on 2018/4/23 19:33
 */
@Service
public class SmsService {

  @Autowired
  SmsServiceMapper smsServiceMapper;

  enum CodeMapping {
    SUCCESS("发送成功"),
    FAILURE("发送失败"),
    STILL_VALID("验证码仍有效，请勿重复发送");
    private String desc;

    CodeMapping(String desc) {
      this.desc = desc;
    }

    public String getDesc() {
      return desc;
    }
  }


  /**
   * 发送短信
   *
   * @param phone 手机号
   */
  public String sendSms(String phone) {
    String verifyCode = SmsUtils.getRandomSixNumber();

    PhoneCodeDo phoneCodeDo = smsServiceMapper.findPhone(phone);

    String result = Optional.ofNullable(phoneCodeDo).map((phoneCode) -> {
      String expireTime = phoneCode.getExpireAt();
      boolean isExpired = DateUtils.isExpired(expireTime, 30);
      if (!isExpired && phoneCode.getStatus().equals("1")) { // 30 分钟内有效
        return CodeMapping.STILL_VALID.getDesc();
      } else { // 失效 更新验证码和状态
        smsServiceMapper.updatePhoneCode(phone, verifyCode, "1");
        return sendSmsByThird(phone, verifyCode);
      }
    }).orElse("NewUser");

    if (result.equals("NewUser")) {
      smsServiceMapper.insertPhoneCode(phone, verifyCode); // 插入验证码
      return sendSmsByThird(phone, verifyCode);
    }

    return result;
  }

  /**
   * 请求聚合数据给指定手机发送指定验证码
   */
  private String sendSmsByThird(String phone, String verifyCode) {
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
      SmsDto smsDto = HttpUtils.doRequest(request, SmsDto.class);
      return smsDto.getError_code() == 0 ? CodeMapping.SUCCESS.getDesc()
          : CodeMapping.FAILURE.getDesc();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return CodeMapping.FAILURE.getDesc();
  }

  /**
   * 验证手机号 验证码
   * true 验证通过
   */
  public boolean isPhoneCodeValid(String phone, String code) {
    PhoneCodeDo phoneCodeDo = smsServiceMapper.findPhone(phone);
    Preconditions.checkNotNull(phoneCodeDo, "invalid phone");
    String expireTime = phoneCodeDo.getExpireAt();
    boolean isExpired = DateUtils.isExpired(expireTime, 30);
    if (isExpired) { //清除验证码
      smsServiceMapper.updatePhoneCode(phone, "", "0");
      return false;
    }
    PhoneCodeDo validSign = smsServiceMapper.findValidSign(phone, code);
    return validSign != null;
  }

}
