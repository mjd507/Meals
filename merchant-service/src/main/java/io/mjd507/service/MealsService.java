package io.mjd507.service;

import io.mjd507.entity.MealVo;
import java.util.List;

/**
 * Created by mjd on 2018/4/16 21:20
 */
public interface MealsService {

  boolean addMeal(MealVo mealVo);

  boolean delMeal(String mealId);

  boolean updateMeal(MealVo mealVo);

  List<MealVo> getMealsByMerchant(String merchantId);
}
