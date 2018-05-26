package io.mjd507.module.user;

/**
 * 用户服务 Created by mjd on 2018/3/24 13:37
 */
public interface UserService {

  UserVo loginByPhone(String phone, String verifyCode);

  /**
   * 根据 userId 找出 UserDto
   */
  UserDto findUserById(String userId);

  /**
   * 更新用户信息
   */
  int updateUserById(UserDo userDo);

  /**
   * 删除用户
   */
  int deleteUserById(String userId);

}
