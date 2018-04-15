package io.mjd507.service.impl;

import io.mjd507.dao.UserServiceMapper;
import io.mjd507.entity.UserVo;
import io.mjd507.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mjd
 * @date 2018/4/15 17:08
 */
@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  UserServiceMapper userServiceMapper;

  @Override
  public UserVo findUserById(String userId) {
    return userServiceMapper.findUserByUserId(userId);
  }

  @Override
  public int updateUserById(String userId, UserVo userVo) {
    return userServiceMapper.updateUserByUserId(userVo, userId);
  }

  @Override
  public int addUser(UserVo userVo) {
    return userServiceMapper.addUser(userVo);
  }

  @Override
  public int deleteUserById(String userId) {
    return userServiceMapper.deleteUserByUserId(userId);
  }
}
