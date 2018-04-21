package io.mjd507.merchants.controller;

import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.MerchantMetaVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/18 20:15
 */
@RestController
public class MealServiceController {

  @ApiOperation(value = "添加菜品", notes = "需要提供商家 id")
  @ResponseBody
  @RequestMapping(value = "addMeal", method = RequestMethod.POST)
  public DataResponse<String> addMeal(@RequestBody MerchantMetaVo merchantVo) {

    return null;
  }
}
