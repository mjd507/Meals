package io.mjd507.module.user;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.mjd507.CopyUtils;
import io.mjd507.module.login.LoginDto;
import io.mjd507.module.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/15 17:08
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  LoginService loginService;

  @Autowired
  UserServiceMapper userServiceMapper;

  // 登录服务
  public UserVo loginByPhone(String phone, String verifyCode) {
    LoginDto loginDto = loginService.loginByPhone(phone, verifyCode);
    if (loginDto != null) {
      return getOrCreateUser(loginDto);
    }
    return null;
  }

  private UserVo getOrCreateUser(LoginDto loginDto) {
    Preconditions.checkNotNull(loginDto);
    UserDo userDo = userServiceMapper.findUserByUserId(loginDto.getUserId());
    if (userDo != null) { //老用户
      UserVo userVo = CopyUtils.copyObject(userDo, UserVo.class);
      userVo.setExtra(loginDto.getToken());
      return userVo;
    } else {
      userDo = new UserDo();
      userDo.setUserId(loginDto.getUserId());
      if (loginDto.getPhone() != null) {
        userDo.setPhone(loginDto.getPhone());
      }
      userDo.setUserName(getMaskPhone(loginDto.getPhone()));
      userServiceMapper.addUser(userDo);
      UserVo userVo = CopyUtils.copyObject(userDo, UserVo.class);
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

  @Override
  public UserDto findUserById(String userId) {
    UserDo userDo = userServiceMapper.findUserByUserId(userId);
    return CopyUtils.copyObject(userDo, UserDto.class);
  }

  @Override
  public int updateUserById(UserDo userDo) {
    return userServiceMapper.updateBySelective(userDo);
  }

  @Override
  public int deleteUserById(String userId) {
    return userServiceMapper.deleteUserByUserId(userId);
  }

}
