package io.mjd507.dao;

import io.mjd507.entity.PhoneCodeDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by mjd on 2018/4/23 20:10
 */
/*
CREATE TABLE `tb_verifyCode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  `verifyCode` varchar(6) NOT NULL COMMENT '6位验证码',
  `status` tinyint(4) DEFAULT '0' COMMENT '验证码状态 0-无效 1-有效',
  `expireAt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='手机号验证码映射表'
 */
@Mapper
public interface SmsServiceMapper {

  @Insert({
      "INSERT INTO tb_verifyCode (phone,verifyCode,status,expireAt) VALUES (#{phone},#{verifyCode},'1',now())"})
  int insertPhoneCode(@Param("phone") String phone, @Param("verifyCode") String verifyCode);

  @Update({
      "UPDATE tb_verifyCode set verifyCode = #{verifyCode}, status = #{status}, expire = now() WHERE phone = #{phone}"})
  int updatePhoneCode(@Param("phone") String phone, @Param("verifyCode") String verifyCode,
      @Param("status") String status);

  @Select({
      "SELECT * FROM tb_verifyCode WHERE phone = #{phone} AND verifyCode = #{verifyCode} AND status = 1"})
  PhoneCodeDo findValidSign(@Param("phone") String phone, @Param("verifyCode") String verifyCode);

  @Select({"SELECT * FROM tb_verifyCode WHERE phone = #{phone}"})
  PhoneCodeDo findPhone(@Param("phone") String phone);
}
