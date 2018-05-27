package io.mjd507;

import com.google.common.base.Strings;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by mjd on 2018/5/27 09:54
 */
public class MchMetaServiceMapSql {

  public String updateSelective(MchMetaDo metaDo) {
    return new SQL() {{
      UPDATE("tb_mch_meta");
      if (!Strings.isNullOrEmpty(metaDo.getMchName())) {
        SET("mch_name = #{mchName}");
      }
      if (!Strings.isNullOrEmpty(metaDo.getMchLogo())) {
        SET("mch_logo = #{mchLogo}");
      }
      if (!Strings.isNullOrEmpty(metaDo.getMchDesc())) {
        SET("mch_desc = #{mchDesc}");
      }
      if (!Strings.isNullOrEmpty(metaDo.getMchLocation())) {
        SET("mch_location = #{mchLocation}");
      }
      if (metaDo.getIsActive() != null) {
        SET("is_active = #{isActive}");
      }
      WHERE("mch_user_id = #{mchUserId}");
    }}.toString();
  }
}
