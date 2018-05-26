package io.mjd507.merchants.controller;

import io.mjd507.common.MchUserAttrSetter;
import io.mjd507.module.login.Constants;
import io.mjd507.common.DataResponse;
import io.mjd507.entity.MealVo;
import io.mjd507.module.mchuser.MchUserDto;
import io.mjd507.service.MealsService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/18 20:15
 */
@RestController
public class MealServiceController extends MchUserAttrSetter {

  @Autowired
  MealsService mealsService;

  @ApiOperation(value = "添加菜品")
  @ResponseBody
  @RequestMapping(value = "addMeal", method = RequestMethod.POST)
  public DataResponse<String> addMeal(@ModelAttribute(Constants.HEADER_AUTH) MchUserDto user,
      @RequestBody MealVo mealVo) {
    mealVo.setMerchantId("MER" + user.getUserId());
    boolean isAdd = mealsService.addMeal(mealVo);
    return new DataResponse<>(isAdd ? "添加成功" : "添加失败");
  }

  @ApiOperation(value = "获取商家已添加菜单")
  @ResponseBody
  @RequestMapping(value = "getMealsByMerchant", method = RequestMethod.GET)
  public DataResponse<List<MealVo>> getMealsByMerchant(
      @ModelAttribute(Constants.HEADER_AUTH) MchUserDto user) {
    List<MealVo> meals = mealsService.getMealsByMerchant("MER" + user.getUserId());
    return new DataResponse<>(meals);
  }

  @ResponseBody
  @RequestMapping(value = "updateMeal", method = RequestMethod.POST)
  public DataResponse<String> updateMeal(@ModelAttribute(Constants.HEADER_AUTH) MchUserDto user,
      @RequestBody MealVo mealVo) {
    mealVo.setMerchantId("MER" + user.getUserId());
    boolean isUpdate = mealsService.updateMeal(mealVo);
    return new DataResponse<>(isUpdate ? "修改成功" : "修改失败");
  }


  @ResponseBody
  @RequestMapping(value = "deleteMeal", method = RequestMethod.POST)
  public DataResponse<String> deleteMeal(String mealId) {
    boolean isDelete = mealsService.delMeal(mealId);
    return new DataResponse<>(isDelete ? "删除成功" : "删除失败");
  }

}
