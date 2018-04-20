package io.mjd507.service;

import io.mjd507.entity.UserVo;

/**
 * Created by mjd on 2018/3/24 13:37
 */
public interface IUserService {

  UserVo findUserById(String userId);

  boolean updateUserById(String userId, UserVo userVo);

  boolean addUser(UserVo userVo);

  boolean deleteUserById(String userId);

  boolean setUserType(String userId, String userType);

}
