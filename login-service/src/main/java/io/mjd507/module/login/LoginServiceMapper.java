package io.mjd507.module.login;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 登录服务
 *
 * Created by mjd on 2018/4/19 19:41
 */
/*
CREATE TABLE `tb_login_token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `token` varchar(50) NOT NULL,
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信用户唯一标识',
  `expired_at` datetime DEFAULT NULL COMMENT '过期时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_token` (`token`),
  UNIQUE KEY `idx_open_id` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录信息表';
 */
@Mapper
public interface LoginServiceMapper {

  @Select({
      "SELECT `id`, `user_id`,`token`,`phone`,`open_id`,`expired_at`,`created_at`,`updated_at`"
          + " FROM tb_login_token WHERE token = #{token}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "token", property = "token"),
      @Result(column = "phone", property = "phone"),
      @Result(column = "open_id", property = "openId"),
      @Result(column = "expired_at", property = "expiredAt"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  LoginDo findByToken(String token);

  /* 一个用户 id 可对应多个 token */
  @Select({
      "SELECT `id`, `user_id`,`token`,`phone`,`open_id`,`expired_at`,`created_at`,`updated_at`"
          + " FROM tb_login_token WHERE user_id = #{userId}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "token", property = "token"),
      @Result(column = "phone", property = "phone"),
      @Result(column = "open_id", property = "openId"),
      @Result(column = "expired_at", property = "expiredAt"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  List<LoginDo> findByUserId(String userId);

  @Select({
      "SELECT `id`, `user_id`,`token`,`phone`,`open_id`,`expired_at`,`created_at`,`updated_at`"
          + " FROM tb_login_token WHERE open_id = #{openId}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "token", property = "token"),
      @Result(column = "phone", property = "phone"),
      @Result(column = "open_id", property = "openId"),
      @Result(column = "expired_at", property = "expiredAt"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  LoginDo findByOpenId(String openId);

  @Insert({
      "INSERT INTO tb_login_token (user_id, token, phone, open_id, expired_at, created_at, updated_at) VALUES "
          + "(#{userId}, #{token}, #{phone}, #{openId}, #{expiredAt}, now(), now())"})
  int insertLoginInfo(LoginDo loginDo);

  @Update({
      "UPDATE tb_login_token set expired_at = #{expiredAt} WHERE user_id = #{userId}"})
  void refreshToken(LoginDo loginDo);

  @Delete({"DELETE FROM tb_login_token WHERE id = #{id}"})
  int deleteById(long id);

}
