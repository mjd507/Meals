package io.mjd507.dao;

import io.mjd507.sms.PhoneCodeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by mjd on 2018/4/23 20:10
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

  @Select({"SELECT * FROM tb_verifyCode WHERE phone = #{phone} AND verifyCode = #{verifyCode}"})
  PhoneCodeVo findValidSign(@Param("phone") String phone, @Param("verifyCode") String verifyCode);

  @Select({"SELECT * FROM tb_verifyCode WHERE phone = #{phone}"})
  PhoneCodeVo findPhone(@Param("phone") String phone);
}
