package io.mjd507.module.login;

import com.google.common.base.Preconditions;
import io.mjd507.CopyUtils;
import io.mjd507.DateUtils;
import io.mjd507.module.sms.SmsService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/5/20 09:01
 */
@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  SmsService smsService;

  @Autowired
  LoginServiceMapper loginMapper;

  @Override
  public LoginDto loginByPhone(String phone, String verifyCode) {
    boolean vCodeValid = smsService.isVCodeValid(phone, verifyCode);
    if (vCodeValid) {
      String userId = TokenUtils.buildUserIdByPhone(phone);
      String token = TokenUtils.buildToken();
      // 删除该用户所有过期的 token
      this.deleteExpiredToken(userId);
      // 在表中记录用户token
      LoginDo loginDo = new LoginDo();
      loginDo.setUserId(userId);
      loginDo.setPhone(phone);
      loginDo.setToken(token);
      loginDo.setExpiredAt(DateUtils.getDayFromNow(30));// token 有效期为30天
      loginMapper.insertLoginInfo(loginDo);
      return CopyUtils.copyObject(loginDo, LoginDto.class);
    }
    return null;
  }

  @Override
  public LoginDto loginByWeApp(String code) {
    // https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html
    WeAppBo weAppBo = WeAppUtils.wxAppLogin(code);
    String openId = weAppBo.getOpenid();
    Preconditions.checkNotNull(openId, "微信服务异常");

    LoginDo loginDo = loginMapper.findByOpenId(openId);
    if (loginDo == null) {
      String userId = TokenUtils.buildUserIdByOpenId(openId);
      String token = TokenUtils.buildToken();
      // 删除该用户所有过期的 token
      this.deleteExpiredToken(userId);
      loginDo = new LoginDo();
      loginDo.setUserId(userId);
      loginDo.setOpenId(openId);
      loginDo.setToken(token);
      loginDo.setExpiredAt(DateUtils.getDayFromNow(30));// token 有效期为30天
      loginMapper.insertLoginInfo(loginDo);
    } else {
      loginDo.setExpiredAt(DateUtils.getDayFromNow(30));// token 有效期为30天
    }
    return CopyUtils.copyObject(loginDo, LoginDto.class);

  }

  @Override
  public LoginDto findValidByToken(String token) {
    LoginDo loginDo = loginMapper.findByToken(token);
    if (loginDo == null) {
      return null;
    }
    boolean isExpired = DateUtils.isExpired(loginDo.getExpiredAt(), 0);
    if (isExpired) {
      // 自动刷新 token 时效
      loginDo.setExpiredAt(DateUtils.getDayFromNow(30));
      loginMapper.refreshToken(loginDo);
    }
    return CopyUtils.copyObject(loginDo, LoginDto.class);
  }

  /**
   * 删除用户所有过期的 token
   */
  private void deleteExpiredToken(String userId) {
    List<LoginDo> loginDoList = loginMapper.findByUserId(userId);
    Optional.ofNullable(loginDoList).map((loginDos) -> {
      loginDos.forEach((loginDo) -> {
        boolean expired = DateUtils.isExpiredNow(loginDo.getExpiredAt());
        if (expired) {
          loginMapper.deleteById(loginDo.getId());
        }
      });
      return null;
    });
  }

}
