package io.mjd507.dao;

import io.mjd507.entity.MealVo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by mjd on 2018/4/26 11:37
 */
@Mapper
public interface MealsServiceMapper {

  @Select({"SELECT * FROM tb_meals WHERE merchantId = #{merchantId}"})
  List<MealVo> getMealsByMerchant(String merchantId);

  @Insert({"INSERT INTO tb_meals (merchantId,name,pic,`desc`,price,createAt,updateAt) VALUES "
      + "(#{MealVo.merchantId},#{MealVo.name},#{MealVo.pic},#{MealVo.desc},#{MealVo.price},now(),now())"})
  int addMeal(@Param("MealVo") MealVo mealVo);

  @Update({"UPDATE tb_meals set `name` = #{MealVo.name},`pic` = #{MealVo.pic}, `desc` = #{MealVo.desc},"
      + "`price` = #{MealVo.price}, `updateAt` = now() WHERE id = #{MealVo.id}"})
  int updateMeal(@Param("MealVo") MealVo mealVo);

  @Delete({"DELETE FROM tb_meals WHERE id = #{id}"})
  int deleteMeal(String id);

}
