/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.merchants.controller;

import io.mjd507.base.DataResponse;
import io.mjd507.entity.MerchantVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mjd
 * @date 2018/4/18 20:15
 */
@RestController
@RequestMapping(value = "merchant")
public class MealServiceController {

  @ApiOperation(value = "添加菜品", notes = "需要提供商家 id")
  @ResponseBody
  @RequestMapping(value = "addMeal", method = RequestMethod.POST)
  public DataResponse<String> addMeal(@RequestBody MerchantVo merchantVo) {

    return null;
  }
}
