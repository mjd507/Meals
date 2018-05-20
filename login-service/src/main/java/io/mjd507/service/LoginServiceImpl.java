package io.mjd507.service;

import com.google.common.base.Preconditions;
import io.mjd507.DateUtils;
import io.mjd507.SmsService;
import io.mjd507.dao.LoginServiceMapper;
import io.mjd507.entity.LoginDo;
import io.mjd507.entity.LoginPo;
import io.mjd507.entity.WeAppSession;
import io.mjd507.utils.TokenUtils;
import io.mjd507.utils.WxUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mjd on 2018/5/20 09:01
 */
public class LoginServiceImpl implements ILoginService {

  @Autowired
  SmsService smsService;

  @Autowired
  LoginServiceMapper loginMapper;

  @Override
  public boolean isTokenValid(String token) {
    LoginPo loginPo = loginMapper.findLoginPoByToken(token);
    return Optional.ofNullable(loginPo).map((po -> {
      boolean isExpired = DateUtils.isExpired(po.getExpireAt(), 0);
      return !isExpired;
    })).orElse(false);
  }

  @Override
  public LoginDo loginByPhone(String phone, String verifyCode) {
    boolean validPhoneCode = smsService.isPhoneCodeValid(phone, verifyCode);
    LoginDo loginDo = new LoginDo();
    if (validPhoneCode) {
      String userId = TokenUtils.buildUserIdByPhone(phone);
      String token = TokenUtils.buildToken();
      // 删除该用户所有过期的 token
      this.deleteExpiredToken(userId);
      // 在表中记录用户token
      LoginPo loginPo = new LoginPo();
      loginPo.setUserId(userId);
      loginPo.setToken(token);
      loginPo.setExpireAt(DateUtils.getFormatStr(DateUtils.getMonthFromNow(1)));// token 有效期为1个月
      loginMapper.insertLoginPo(loginPo);
      loginDo.setUserId(userId);
      loginDo.setToken(token);
    }
    return loginDo;
  }

  @Override
  public LoginDo loginByWeApp(String code) {
    // https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html
    WeAppSession session = WxUtils.wxAppLogin(code);
    String openId = session.getOpenid();
    Preconditions.checkNotNull(openId, "微信服务异常");

    LoginDo loginDo = new LoginDo();
    String userId = TokenUtils.buildUserIdByOpenId(openId);
    String token = TokenUtils.buildToken();
    loginDo.setUserId(userId);
    loginDo.setToken(token);

    LoginPo loginPo = loginMapper.findLoginPoByOpenId(openId);
    return Optional.ofNullable(loginPo).map((po) -> {
      String expiredTime = DateUtils.getFormatStr(DateUtils.getMonthFromNow(1));
      po.setExpireAt(expiredTime);
      loginMapper.updateLoginPoExpiredTime(po);
      loginDo.setUserId(loginPo.getUserId());
      loginDo.setToken(loginPo.getToken());
      return loginDo;
    }).orElse(loginDo);

  }

  /**
   * 删除用户所有过期的 token
   */
  private void deleteExpiredToken(String userId) {
    List<LoginPo> loginPoList = loginMapper.findLoginPoByUserId(userId);
    Optional.ofNullable(loginPoList).map((list) -> {
      list.forEach((po) -> {
        boolean expired = DateUtils.isExpired(po.getExpireAt(), 0);
        if (expired) {
          loginMapper.deleteLoginPoById(po.getId());
        }
      });
      return null;
    });
  }

}
