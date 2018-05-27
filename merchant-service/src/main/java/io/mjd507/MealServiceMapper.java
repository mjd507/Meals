package io.mjd507;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * Created by mjd on 2018/4/26 11:37
 */
/*
CREATE TABLE `tb_meal` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mch_id` varchar(50) NOT NULL DEFAULT '',
  `meal_name` varchar(50) NOT NULL DEFAULT '',
  `meal_pic` varchar(100) NOT NULL DEFAULT '',
  `meal_desc` varchar(200) DEFAULT NULL,
  `meal_price` varchar(10) NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Mapper
public interface MealServiceMapper {

  @Select({"SELECT `id`,`mch_id`,`meal_name`,`meal_pic`,`meal_desc`,`meal_price`,`created_at`,"
      + "`updated_at` FROM tb_meal WHERE mch_id = #{mchId}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "mch_id", property = "mchId"),
      @Result(column = "meal_name", property = "mealName"),
      @Result(column = "meal_pic", property = "mealPic"),
      @Result(column = "meal_desc", property = "mealDesc"),
      @Result(column = "meal_price", property = "mealPrice"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  List<MealDo> getMealsByMch(String mchId);

  @Insert({
      "INSERT INTO tb_meal (mch_id,meal_name,meal_pic,meal_desc,meal_price,created_at,updated_at) "
          + "VALUES (#{mchId},#{mealName},#{mealPic},#{mealDesc},#{mealPrice},now(),now())"})
  int addMeal(MealDo mealDo);

  @UpdateProvider(type = MealServiceMapSql.class, method = "updateSelective")
  int updateSelective(MealDo mealDo);

  @Delete({"DELETE FROM tb_meal WHERE id = #{id}"})
  int deleteMeal(String id);

}
