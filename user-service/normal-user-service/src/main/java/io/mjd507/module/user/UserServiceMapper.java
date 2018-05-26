package io.mjd507.module.user;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * Created by mjd on 2018/4/15 17:05
 */
/*
CREATE TABLE `tb_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `avatar` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `department` varchar(100) DEFAULT NULL COMMENT '所在部门',
  `user_group` varchar(100) DEFAULT NULL COMMENT '所在组别',
  `user_role` smallint(4) DEFAULT '1' COMMENT '1-普通用户，2-小组长',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
 */
@Mapper
public interface UserServiceMapper {

  @Insert({
      "INSERT INTO tb_user (`user_id`, `user_name`, `nick_name`, `phone`, `avatar`, `department`,"
          + " `user_group`, `user_role`, `created_at`, `updated_at`) VALUES (#{userId}, #{userName}, "
          + "#{nickName}, #{phone}, #{avatar}, #{department}, #{userGroup}, #{userRole}, now(), now())"})
  int addUser(UserDo user);

  @Select({
      "SELECT `id`,`user_id`,`user_name`,`nick_name`,`phone`,`avatar`,`department`,`user_group`,"
          + "`user_role`,`created_at`,`updated_at` FROM tb_user WHERE `user_id` = #{userId}"
  })
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "user_name", property = "userName"),
      @Result(column = "nick_name", property = "nickName"),
      @Result(column = "phone", property = "phone"),
      @Result(column = "avatar", property = "avatar"),
      @Result(column = "department", property = "department"),
      @Result(column = "user_group", property = "userGroup"),
      @Result(column = "user_role", property = "userRole"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  UserDo findUserByUserId(String userId);

  @Select({
      "SELECT `id`,`user_id`,`user_name`,`nick_name`,`phone`,`avatar`,`department`,`user_group`,"
          + "`user_role`,`created_at`,`updated_at` FROM tb_user WHERE `phone` = #{phone}"
  })
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "user_name", property = "userName"),
      @Result(column = "nick_name", property = "nickName"),
      @Result(column = "phone", property = "phone"),
      @Result(column = "avatar", property = "avatar"),
      @Result(column = "department", property = "department"),
      @Result(column = "user_group", property = "userGroup"),
      @Result(column = "user_role", property = "userRole"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  UserDo findUserByPhone(String phone);

  @UpdateProvider(type = UserServiceMapperSql.class, method = "updateBySelective")
  int updateBySelective(UserDo user);

  @Delete({"DELETE FROM tb_user WHERE user_id = #{userId}"})
  int deleteUserByUserId(String userId);

}
