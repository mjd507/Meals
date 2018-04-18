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
import io.mjd507.service.impl.MerchantServiceImpl;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mjd
 * @date 2018/4/18 19:40
 */
@RestController
@RequestMapping(value = "merchant")
public class MerchantsServiceController {

  @Autowired
  MerchantServiceImpl merchantService;

  @ApiOperation(value = "添加商家", notes = "接入新的商家")
  @ResponseBody
  @RequestMapping(value = "addMerchant", method = RequestMethod.POST)
  public DataResponse<String> addMerchant(@RequestBody MerchantVo merchantVo) {
    int i = merchantService.addMerchant(merchantVo);
    String data = i == 1 ? "添加成功" : "添加失败";
    return new DataResponse<>(data);
  }

  @ApiOperation(value = "删除商家", notes = "删除接入的商家")
  @ResponseBody
  @RequestMapping(value = "deleteMerchant", method = RequestMethod.POST)
  public DataResponse<String> deleteMerchant(int merId) {
    int i = merchantService.deleteMerchant(merId);
    String data = i == 1 ? "删除成功" : "删除失败";
    return new DataResponse<>(data);
  }

  @ApiOperation(value = "修改商家", notes = "修改接入的商家信息")
  @ResponseBody
  @RequestMapping(value = "modifyMerchant", method = RequestMethod.POST)
  public DataResponse<String> modifyMerchant(@RequestBody MerchantVo merchantVo) {
    int i = merchantService.updateMerchant(merchantVo);
    String data = i == 1 ? "删除成功" : "删除失败";
    return new DataResponse<>(data);
  }

  @ApiOperation(value = "商家列表", notes = "查看所有商家列表")
  @ResponseBody
  @RequestMapping(value = "getMerchantList", method = RequestMethod.GET)
  public DataResponse<List<MerchantVo>> modifyMerchant() {
    List<MerchantVo> allMerchants = merchantService.getAllMerchants();
    return new DataResponse<>(allMerchants);
  }

}
