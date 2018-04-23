package io.mjd507.service.impl;

import io.mjd507.dao.LoginServiceMapper;
import io.mjd507.entity.LoginVo;
import io.mjd507.entity.MerchantUserVo;
import io.mjd507.entity.WeAppSession;
import io.mjd507.service.ILoginService;
import io.mjd507.sms.SmsService;
import io.mjd507.utils.LoginUtils;
import io.mjd507.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/21 18:53
 */
@Service
public class MerchantLoginServiceImpl implements ILoginService<MerchantUserVo> {

  @Autowired
  private LoginServiceMapper loginServiceMapper;

  @Autowired
  private MerchantUserServiceImpl userService;

  @Autowired
  private SmsService smsService;

  @Override
  public MerchantUserVo loginByPhone(String phone, String verifyCode) {
    boolean validSuccess = smsService.hasPhoneAndCode(phone, verifyCode);
    // 短信验证通过
    if (validSuccess) {
      MerchantUserVo userByPhone = userService.findUserByPhone(phone);
      if (userByPhone != null && userByPhone.getUserId() != null) { // 用户存在
        return userByPhone;
      } else {
        String userId = TokenUtils.buildUserId(phone);
        MerchantUserVo user = new MerchantUserVo();
        user.setUserId(userId);
        LoginVo loginVo = new LoginVo();
        loginVo.setUserId(userId);
        loginVo.setPhone(phone);
        loginServiceMapper.insertLoginVo(loginVo);
        userService.addUser(user);
        return user;
      }
    }
    return null;
  }

  @Override
  public MerchantUserVo loginByWeApp(String code) {
    // https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html
    WeAppSession session = LoginUtils.wxAppLogin(code);
    String openId = session.getOpenid();
    if (openId != null) { // 获取到微信用户
      LoginVo loginVo = loginServiceMapper.findUserIdByOpenId(openId);
      if (loginVo != null && loginVo.getUserId() != null) { // 用户存在
        return userService.findUserById(loginVo.getUserId());
      } else { //用户不存在，创建新用户
        // 根据 openId 生成 userId
        String userId = TokenUtils.buildUserId(openId);
        MerchantUserVo user = new MerchantUserVo();
        user.setUserId(userId);
        loginVo = new LoginVo();
        loginVo.setOpenId(openId);
        loginVo.setUserId(userId);
        loginServiceMapper.insertLoginVo(loginVo);
        userService.addUser(user);
        return user;
      }
    }
    return null;
  }

  @Override
  public boolean deleteUserToken(String userId) {
    return loginServiceMapper.deleteUserToken(userId) == 1;
  }
}
