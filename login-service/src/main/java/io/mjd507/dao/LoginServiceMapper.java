package io.mjd507.dao;

import io.mjd507.entity.LoginPo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 登录服务
 *
 * Created by mjd on 2018/4/19 19:41
 */
/*
CREATE TABLE `tb_login_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(32) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `openId` varchar(100) DEFAULT NULL COMMENT '微信用户唯一标识',
  `expireAt` datetime DEFAULT NULL COMMENT '过期时间',
  `createAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='登录信息表，包含第三方授权登录唯一性的字段'
 */
@Mapper
public interface LoginServiceMapper {

  @Select({"SELECT * FROM tb_login_token WHERE token = #{token}"})
  LoginPo findLoginPoByToken(String token);

  /* 一个用户 id 可对应多个 token */
  @Select({"SELECT * FROM tb_login_token WHERE userId = #{userId}"})
  List<LoginPo> findLoginPoByUserId(String userId);

  @Select({"SELECT * FROM tb_login_token WHERE openId = #{openId}"})
  LoginPo findLoginPoByOpenId(String openId);

  @Insert({
      "INSERT INTO tb_login_token (userId, token, phone, openId, expireAt, createAt, updateAt) VALUES "
          + "(#{LoginPo.userId}, #{LoginPo.token}, #{LoginPo.phone}, #{LoginPo.openId}, #{LoginPo.expireAt}, now(), now())"})
  int insertLoginPo(@Param("LoginPo") LoginPo loginPo);

  @Update({
      "UPDATE tb_login_token set expireAt = #{LoginPo.expireAt} WHERE openId = #{LoginPo.openId}"})
  void updateLoginPoExpiredTime(@Param("LoginPo") LoginPo loginPo);

  @Delete({"DELETE FROM tb_login_token WHERE id = #{id}"})
  int deleteLoginPoById(long id);


}
