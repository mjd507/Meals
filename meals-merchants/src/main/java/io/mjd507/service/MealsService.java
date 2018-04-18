/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.service;

import io.mjd507.entity.MealVo;
import java.util.List;

/**
 * @author mjd
 * @date 2018/4/16 21:20
 */
public interface MealsService {

  int addMeal(MealVo mealVo);

  int delMeal(MealVo mealVo);

  int updateMeal(MealVo mealVo);

  List<MealVo> getAllMeals();
}
