package io.mjd507;

import com.google.common.base.Strings;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by mjd on 2018/5/27 13:48
 */
public class MealServiceMapSql {

  public String updateSelective(MealDo mealDo) {
    return new SQL() {{
      UPDATE("tb_meal");
      if (!Strings.isNullOrEmpty(mealDo.getMealName())) {
        SET("meal_name = #{mealName}");
      }
      if (!Strings.isNullOrEmpty(mealDo.getMealPic())) {
        SET("meal_pic = #{mealPic}");
      }
      if (!Strings.isNullOrEmpty(mealDo.getMealDesc())) {
        SET("meal_desc = #{mealDesc}");
      }
      if (!Strings.isNullOrEmpty(mealDo.getMealPrice())) {
        SET("meal_price = #{mealPrice}");
      }
      SET("updated_at = now()");
      WHERE("id = #{id}");
    }}.toString();
  }
}
