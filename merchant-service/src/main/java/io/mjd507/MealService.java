package io.mjd507;

import java.util.List;

/**
 * Created by mjd on 2018/4/16 21:20
 */
public interface MealService {

  int addMeal(MealDo mealDo);

  int deleteMeal(String id);

  int updateMeal(MealDo mealDo);

  List<MealDto> getMealsByMch(String mchId);
}
