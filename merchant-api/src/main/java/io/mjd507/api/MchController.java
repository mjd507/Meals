package io.mjd507.api;

import io.mjd507.common.DataResponse;
import io.mjd507.entity.MealVo;
import io.mjd507.entity.MerchantMetaVo;
import io.mjd507.service.MealsService;
import io.mjd507.service.MerchantService;
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
public class MchController {

  @Autowired
  MerchantService merchantService;

  @Autowired
  MealsService mealsService;

  @ApiOperation(value = "商家列表", notes = "查看所有商家列表")
  @ResponseBody
  @RequestMapping(value = "getMerchantList", method = RequestMethod.GET)
  public DataResponse<List<MerchantMetaVo>> getMerchantList() {
    List<MerchantMetaVo> allMerchants = merchantService.getAllMerchants();
    return new DataResponse<>(allMerchants);
  }

  @ApiOperation(value = "获取商家菜单")
  @ResponseBody
  @RequestMapping(value = "getMealsByMerchant", method = RequestMethod.GET)
  public DataResponse<List<MealVo>> getMealsByMerchant(String merchantId) {
    List<MealVo> meals = mealsService.getMealsByMerchant(merchantId);
    return new DataResponse<>(meals);
  }
}
