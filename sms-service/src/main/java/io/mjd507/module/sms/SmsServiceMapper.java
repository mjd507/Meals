package io.mjd507.module.sms;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * Created by mjd on 2018/4/23 20:10
 */
/*
CREATE TABLE `tb_verify_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  `v_code` varchar(6) NOT NULL DEFAULT '',
  `expired_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_phone_vcode` (`phone`,`v_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='手机号验证码映射表';
 */
@Mapper
public interface SmsServiceMapper {

  @Insert({
      "INSERT INTO tb_verify_code (phone,v_code,expired_at) VALUES (#{phone},#{vCode},#{expiredAt})"})
  int insertVCode(VCodeDo vCodeDo);

  @UpdateProvider(type = SmsServiceMapSql.class, method = "updateSelective")
  int updateBySelective(VCodeDo vCodeDo);

  @Select({
      "SELECT `id`,`phone`,`v_code`,`expired_at`,`created_at`,`updated_at` "
          + "FROM tb_verify_code WHERE phone = #{phone} AND v_code = #{vCode}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "phone", property = "phone"),
      @Result(column = "v_code", property = "vCode"),
      @Result(column = "expired_at", property = "expiredAt"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  VCodeDo findValid(@Param("phone") String phone, @Param("vCode") String vCode);

  @Select({
      "SELECT `id`,`phone`,`v_code`,`expired_at`,`created_at`,`updated_at` "
          + "FROM tb_verify_code WHERE phone = #{phone}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "phone", property = "phone"),
      @Result(column = "v_code", property = "vCode"),
      @Result(column = "expired_at", property = "expiredAt"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  VCodeDo findByPhone(@Param("phone") String phone);
}
