package io.mjd507.service;

/**
 * 用户服务
 * T 具体的用户实体
 * Created by mjd on 2018/3/24 13:37
 */
public interface IUserService<T> {

  T findUserById(String userId);

  T findUserByPhone(String phone);

  boolean updateUserById(String userId, T userVo);

  boolean addUser(T userVo);

  boolean deleteUserById(String userId);

}
