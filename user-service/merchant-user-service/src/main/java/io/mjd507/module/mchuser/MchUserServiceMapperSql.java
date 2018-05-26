package io.mjd507.module.mchuser;

import com.google.common.base.Strings;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by mjd on 2018/5/25 16:19
 */
public class MchUserServiceMapperSql {

  public String updateBySelective(MchUserDo mchUserDo) {
    return new SQL() {{
      UPDATE("tb_merchant_user");
      if (!Strings.isNullOrEmpty(mchUserDo.getUserName())) {
        SET("user_name = #{userName}");
      }
      if (!Strings.isNullOrEmpty(mchUserDo.getNickName())) {
        SET("nick_name = #{nickName}");
      }
      if (!Strings.isNullOrEmpty(mchUserDo.getAvatar())) {
        SET("avatar = #{avatar}");
      }
      SET("updated_at = now()");
      WHERE("user_id = #{userId}");
    }}.toString();
  }

}
