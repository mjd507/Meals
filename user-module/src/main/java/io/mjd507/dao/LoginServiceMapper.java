package io.mjd507.dao;

import io.mjd507.entity.LoginVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 登录模块服务数据持久层
 *
 * Created by mjd on 2018/4/19 19:41
 */
@Mapper
public interface LoginServiceMapper {

  @Select({"SELECT * FROM tb_login_info WHERE openId = #{openId}"})
  LoginVo findUserIdByOpenId(String openId);

  /**获取参数以 @Param("") 中字符串为准*/
  @Insert({"INSERT INTO tb_login_info (userId, phone, openId) VALUES (#{LoginVo.userId}, #{LoginVo.phone}, #{LoginVo.openId})"})
  int insertLoginVo(@Param("LoginVo") LoginVo loginVo);

  @Delete({"DELETE FROM tb_login_info WHERE userId = #{userId}"})
  int deleteUserToken(String userId);

}
