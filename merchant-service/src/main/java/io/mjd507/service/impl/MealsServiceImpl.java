package io.mjd507.service.impl;

import io.mjd507.dao.MealsServiceMapper;
import io.mjd507.service.MealsService;
import io.mjd507.entity.MealVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/16 21:50
 */
@Service
public class MealsServiceImpl implements MealsService {

  @Autowired
  MealsServiceMapper mealsService;

  @Override
  public boolean addMeal(MealVo mealVo) {
    return mealsService.addMeal(mealVo) == 1;
  }

  @Override
  public boolean delMeal(String mealId) {
    return mealsService.deleteMeal(mealId) == 1;
  }

  @Override
  public boolean updateMeal(MealVo mealVo) {
    return mealsService.updateMeal(mealVo) == 1;
  }

  @Override
  public List<MealVo> getMealsByMerchant(String merchantId) {
    return mealsService.getMealsByMerchant(merchantId);
  }
}
