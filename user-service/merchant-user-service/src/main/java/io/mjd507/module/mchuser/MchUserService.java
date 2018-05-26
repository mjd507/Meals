package io.mjd507.module.mchuser;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.mjd507.CopyUtils;
import io.mjd507.module.login.LoginDto;
import io.mjd507.module.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/21 18:31
 */
@Service
public class MchUserService {

  @Autowired
  MchUserServiceMapper userServiceMapper;

  @Autowired
  LoginService loginService;

  // 登录服务
  public MchUserVo loginByPhone(String phone, String verifyCode) {
    LoginDto loginDto = loginService.loginByPhone(phone, verifyCode);
    if (loginDto != null) {
      return getOrCreateUser(loginDto);
    }
    return null;
  }

  private MchUserVo getOrCreateUser(LoginDto loginDto) {
    Preconditions.checkNotNull(loginDto);
    MchUserDo userDo = userServiceMapper.findUserByUserId(loginDto.getUserId());
    if (userDo != null) {
      MchUserVo userVo = CopyUtils.copyObject(userDo, MchUserVo.class);
      userVo.setExtra(loginDto.getToken());
      return userVo;
    } else {
      userDo = new MchUserDo();
      userDo.setUserId(loginDto.getUserId());
      if (loginDto.getPhone() != null) {
        userDo.setPhone(loginDto.getPhone());
      }
      userDo.setUserName(getMaskPhone(loginDto.getPhone()));
      userServiceMapper.addUser(userDo);
      MchUserVo userVo = CopyUtils.copyObject(userDo, MchUserVo.class);
      userVo.setExtra(loginDto.getToken());
      return userVo;
    }
  }

  private String getMaskPhone(String phone) {
    if (!Strings.isNullOrEmpty(phone)) {
      return phone.substring(0, 3) + "****" + phone.substring(7);
    }
    return "";
  }

  public MchUserDto findUserById(String userId) {
    MchUserDo mchUserDo = userServiceMapper.findUserByUserId(userId);
    return CopyUtils.copyObject(mchUserDo, MchUserDto.class);
  }

  public int updateUserById(MchUserDo userVo) {
    return userServiceMapper.updateBySelective(userVo);
  }

  public int deleteUserById(String userId) {
    return userServiceMapper.deleteUserByUserId(userId);
  }

}