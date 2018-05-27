package io.mjd507.mch;

import io.mjd507.CopyUtils;
import io.mjd507.MchMetaDto;
import io.mjd507.MchMetaService;
import io.mjd507.MchMetaVo;
import io.mjd507.MealDto;
import io.mjd507.MealService;
import io.mjd507.MealVo;
import io.mjd507.common.DataResponse;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/25 20:39
 */
@RestController
@RequestMapping(value = "api")
public class MchApiController {

  @Autowired
  MchMetaService merchantService;

  @Autowired
  MealService mealsService;

  @ApiOperation(value = "商家列表", notes = "查看所有商家列表")
  @ResponseBody
  @RequestMapping(value = "getMerchantList", method = RequestMethod.GET)
  public DataResponse<List<MchMetaVo>> getMerchantList() {
    List<MchMetaDto> allMerchants = merchantService.getActiveMerchants();
    List<MchMetaVo> mchMetaVos = CopyUtils.copyList(allMerchants, MchMetaVo.class);
    return new DataResponse<>(mchMetaVos);
  }

  @ApiOperation(value = "获取商家菜单")
  @ResponseBody
  @RequestMapping(value = "getMealsByMch", method = RequestMethod.GET)
  public DataResponse<List<MealVo>> getMealsByMch(String mchId) {
    List<MealDto> mealDtos = mealsService.getMealsByMch(mchId);
    List<MealVo> mealVos = CopyUtils.copyList(mealDtos, MealVo.class);
    return new DataResponse<>(mealVos);
  }
}
