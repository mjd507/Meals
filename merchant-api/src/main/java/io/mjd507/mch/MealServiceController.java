package io.mjd507.mch;

import com.google.common.base.Strings;
import io.mjd507.CopyUtils;
import io.mjd507.MchMetaDto;
import io.mjd507.MchMetaService;
import io.mjd507.MealDo;
import io.mjd507.MealDto;
import io.mjd507.MealService;
import io.mjd507.MealVo;
import io.mjd507.common.DataResponse;
import io.mjd507.common.MchUserAttrSetter;
import io.mjd507.module.login.Constants;
import io.mjd507.module.mchuser.MchUserDto;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/18 20:15
 */
@RestController
public class MealServiceController extends MchUserAttrSetter {

  @Autowired
  MealService mealsService;

  @Autowired
  MchMetaService mchMetaService;

  @ApiOperation(value = "添加菜品")
  @ResponseBody
  @RequestMapping(value = "addMeal", method = RequestMethod.POST)
  public DataResponse<String> addMeal(@ModelAttribute(Constants.HEADER_AUTH) MchUserDto userDto,
      @RequestBody MealVo mealVo) {
    DataResponse<String> response;
    boolean isValid = checkValidMeal(mealVo);
    if (isValid) {
      MchMetaDto mchMetaDto = mchMetaService.getMchByUserId(userDto.getUserId());
      MealDo mealDo = CopyUtils.copyObject(mealVo, MealDo.class);
      mealDo.setMchId(mchMetaDto.getMchId());
      int count = mealsService.addMeal(mealDo);
      response = new DataResponse<>(count > 0 ? "添加成功" : "添加失败");
    } else {
      response = new DataResponse<>();
      response.setFailure("请补全菜品信息");
    }
    return response;

  }

  private boolean checkValidMeal(MealVo mealVo) {
    return mealVo != null &&
        !Strings.isNullOrEmpty(mealVo.getMealName()) &&
        !Strings.isNullOrEmpty(mealVo.getMealPic()) &&
        !Strings.isNullOrEmpty(mealVo.getMealPrice());
  }

  @ApiOperation(value = "获取商家已添加菜单")
  @ResponseBody
  @RequestMapping(value = "getMealsByMerchant", method = RequestMethod.GET)
  public DataResponse<List<MealVo>> getMealsByMerchant(
      @ModelAttribute(Constants.HEADER_AUTH) MchUserDto userDto) {
    MchMetaDto mchMetaDto = mchMetaService.getMchByUserId(userDto.getUserId());
    List<MealDto> mealDtos = mealsService.getMealsByMch(mchMetaDto.getMchId());
    List<MealVo> mealVoList = CopyUtils.copyList(mealDtos, MealVo.class);
    return new DataResponse<>(mealVoList);
  }

  @ResponseBody
  @RequestMapping(value = "updateMeal", method = RequestMethod.POST)
  public DataResponse<String> updateMeal(@ModelAttribute(Constants.HEADER_AUTH) MchUserDto user,
      @RequestBody MealVo mealVo) {
    DataResponse<String> response;
    boolean isValid = checkValidMeal(mealVo);
    if (isValid) {
      MealDo mealDo = CopyUtils.copyObject(mealVo, MealDo.class);
      int count = mealsService.updateMeal(mealDo);
      response = new DataResponse<>(count > 0 ? "修改成功" : "修改失败");
    } else {
      response = new DataResponse<>();
      response.setFailure("请补全菜品信息");
    }
    return response;
  }

  @ResponseBody
  @RequestMapping(value = "deleteMeal", method = RequestMethod.POST)
  public DataResponse<String> deleteMeal(@RequestParam String id) {
    int count = mealsService.deleteMeal(id);
    return new DataResponse<>(count > 0 ? "删除成功" : "删除失败");
  }

}
