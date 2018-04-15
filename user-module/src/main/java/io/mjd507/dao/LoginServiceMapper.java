package io.mjd507.dao;

import io.mjd507.entity.LoginVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 登录模块服务数据持久层
 *
 * @author mjd
 * @date 2018/3/24 15:05
 */
@Mapper
public interface LoginServiceMapper {

  @Select({"SELECT * FROM tb_login_info WHERE openId = #{openId}"})
  LoginVo findUserIdByOpenId(String openId);

  /**获取参数以 @Param("") 中字符串为准*/
  @Insert({"INSERT INTO tb_login_info (userId, phone, openId) VALUES (#{LoginVo.userId}, #{LoginVo.phone}, #{LoginVo.openId})"})
  int insertLoginVo(@Param("LoginVo") LoginVo loginVo);

}
