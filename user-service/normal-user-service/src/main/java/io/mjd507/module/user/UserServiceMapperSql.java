package io.mjd507.module.user;

import com.google.common.base.Strings;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by mjd on 2018/5/25 16:19
 */
public class UserServiceMapperSql {

  public String updateBySelective(UserDo userDo) {
    return new SQL() {{
      UPDATE("tb_user");
      if (!Strings.isNullOrEmpty(userDo.getUserName())) {
        SET("user_name = #{userName}");
      }
      if (!Strings.isNullOrEmpty(userDo.getNickName())) {
        SET("nick_name = #{nickName}");
      }
      if (!Strings.isNullOrEmpty(userDo.getAvatar())) {
        SET("avatar = #{avatar}");
      }
      if (!Strings.isNullOrEmpty(userDo.getDepartment())) {
        SET("department = #{department}");
      }
      if (!Strings.isNullOrEmpty(userDo.getUserGroup())) {
        SET("user_group = #{userGroup}");
      }
      if (userDo.getUserRole() != null) {
        SET("user_role = #{userRole}");
      }
      SET("updated_at = now()");
      WHERE("user_id = #{userId}");
    }}.toString();
  }

}
