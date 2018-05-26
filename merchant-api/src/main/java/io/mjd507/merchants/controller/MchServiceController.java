package io.mjd507.merchants.controller;

import io.mjd507.StringUtils;
import io.mjd507.common.DataResponse;
import io.mjd507.common.MchUserAttrSetter;
import io.mjd507.module.login.Constants;
import io.mjd507.entity.MerchantMetaVo;
import io.mjd507.module.mchuser.MchUserDto;
import io.mjd507.service.MerchantService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/18 19:40
 */
@RestController
public class MchServiceController extends MchUserAttrSetter {

  @Autowired
  MerchantService merchantService;

  @ApiOperation(value = "申请成为商家")
  @ResponseBody
  @RequestMapping(value = "applyMerchant", method = RequestMethod.POST)
  public DataResponse<String> ApplyMerchant(
      @ModelAttribute(Constants.HEADER_AUTH) MchUserDto user,
      @RequestBody MerchantMetaVo merchantVo) {
    DataResponse<String> response = null;
    boolean isVaild = vaildMerchant(merchantVo);
    if (isVaild) {
      merchantVo.setMerchantId("MER" + user.getUserId());
      merchantService.addMerchant(merchantVo);
      response = new DataResponse<>("申请成功，一般两天内给出审核结果");
    } else {
      response = new DataResponse<>();
      response.setFailure("请补全商户名称等信息");
    }
    return response;
  }

  private boolean vaildMerchant(MerchantMetaVo merchantVo) {
    return !StringUtils.isEmpty(merchantVo.getName());
  }

  @ApiOperation(value = "删除商家", notes = "删除接入的商家")
  @ResponseBody
  @RequestMapping(value = "deleteMerchant", method = RequestMethod.POST)
  public DataResponse<String> deleteMerchant(@ModelAttribute(Constants.HEADER_AUTH) MchUserDto user) {
    boolean i = merchantService.deleteMerchant("MER" + user.getUserId());
    String data = i ? "删除成功" : "删除失败";
    return new DataResponse<>(data);
  }

  @ApiOperation(value = "修改商家", notes = "修改接入的商家信息")
  @ResponseBody
  @RequestMapping(value = "modifyMerchant", method = RequestMethod.POST)
  public DataResponse<String> modifyMerchant(
      @ModelAttribute(Constants.HEADER_AUTH) MchUserDto user,
      @RequestBody MerchantMetaVo merchantVo) {
    merchantVo.setMerchantId("MER" + user.getUserId());
    boolean i = merchantService.updateMerchant(merchantVo);
    String data = i ? "修改成功" : "修改失败";
    return new DataResponse<>(data);
  }


}
