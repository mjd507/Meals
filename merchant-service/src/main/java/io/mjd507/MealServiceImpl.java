package io.mjd507;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/16 21:50
 */
@Service
public class MealServiceImpl implements MealService {

  @Autowired
  MealServiceMapper mealServiceMapper;

  @Override
  public int addMeal(MealDo mealDo) {
    return mealServiceMapper.addMeal(mealDo);
  }

  @Override
  public int deleteMeal(String id) {
    return mealServiceMapper.deleteMeal(id);
  }

  @Override
  public int updateMeal(MealDo mealDo) {
    return mealServiceMapper.updateSelective(mealDo);
  }

  @Override
  public List<MealDto> getMealsByMch(String mchId) {
    List<MealDo> mealList = mealServiceMapper.getMealsByMch(mchId);
    return CopyUtils.copyList(mealList, MealDto.class);
  }
}
