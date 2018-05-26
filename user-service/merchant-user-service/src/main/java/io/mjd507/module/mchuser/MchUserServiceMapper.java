package io.mjd507.module.mchuser;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * Created by mjd on 2018/4/21 18:29
 */
/*
CREATE TABLE `tb_merchant_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `avatar` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商家用户信息表';
 */
@Mapper
public interface MchUserServiceMapper {

  @Insert({
      "INSERT INTO tb_merchant_user (`user_id`, `user_name`, `nick_name`, `phone`, `avatar`,`created_at`, "
          + "`updated_at`) VALUES (#{userId}, #{userName}, #{nickName}, #{phone}, #{avatar}, "
          + "now(), now())"})
  int addUser(MchUserDo mchUserDo);

  @Select({
      "SELECT `id`,`user_id`,`user_name`,`nick_name`,`phone`,`avatar`,`created_at`,`updated_at` "
          + "FROM tb_merchant_user WHERE user_id = #{userId}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "user_name", property = "userName"),
      @Result(column = "nick_name", property = "nickName"),
      @Result(column = "phone", property = "phone"),
      @Result(column = "avatar", property = "avatar"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  MchUserDo findUserByUserId(String userId);

  @UpdateProvider(type = MchUserServiceMapperSql.class, method = "updateBySelective")
  int updateBySelective(MchUserDo user);

  @Delete({"DELETE FROM tb_merchant_user WHERE user_id = #{userId}"})
  int deleteUserByUserId(String userId);

}
