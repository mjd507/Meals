package io.mjd507.service.impl;

import io.mjd507.dao.MerchantUserServiceMapper;
import io.mjd507.entity.MerchantUserVo;
import io.mjd507.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/21 18:31
 */
@Service
public class MerchantUserServiceImpl implements IUserService<MerchantUserVo> {

  @Autowired
  MerchantUserServiceMapper userServiceMapper;

  @Override
  public MerchantUserVo findUserById(String userId) {
    return userServiceMapper.findUserByUserId(userId);
  }

  @Override
  public MerchantUserVo findUserByPhone(String phone) {
    return userServiceMapper.findUserByPhone(phone);
  }

  @Override
  public boolean updateUserById(String userId, MerchantUserVo userVo) {
    return userServiceMapper.updateUserByUserId(userVo, userId) == 1;
  }

  @Override
  public boolean addUser(MerchantUserVo userVo) {
    return userServiceMapper.addUser(userVo) == 1;
  }

  @Override
  public boolean deleteUserById(String userId) {
    return userServiceMapper.deleteUserByUserId(userId) == 1;
  }

  public boolean setUserActiveStatus(String userId, String status) {
    return userServiceMapper.setUserActiveStatus(status, userId) == 1;
  }

}