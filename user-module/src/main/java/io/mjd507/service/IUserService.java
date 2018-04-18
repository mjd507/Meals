package io.mjd507.service;

import io.mjd507.entity.UserVo;

/**
 * @author mjd
 * @date: 2018/3/24 13:37
 */
public interface IUserService {

  UserVo findUserById(String userId);

  int updateUserById(String userId, UserVo userVo);

  int addUser(UserVo userVo);

  int deleteUserById(String userId);

  int setUserToMerchant(String userId);

}
