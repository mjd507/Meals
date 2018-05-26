package io.mjd507.module.sms;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by mjd on 2018/5/26 09:45
 */
public class SmsServiceMapSql {

  public String updateSelective(VCodeDo vCodeDo) {
    return new SQL() {{
      UPDATE("tb_verify_code");
      if (vCodeDo.getVCode() != null) {
        SET("v_code = #{vCode}");
      }
      if (vCodeDo.getExpiredAt() != null) {
        SET("expired_at = #{expiredAt}");
      }
      SET("updated_at = now()");
      WHERE("phone = #{phone}");
    }}.toString();
  }
}
