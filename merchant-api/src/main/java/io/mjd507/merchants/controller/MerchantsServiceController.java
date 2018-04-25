package io.mjd507.merchants.controller;

import io.mjd507.StringUtils;
import io.mjd507.common.constants.Constants;
import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.MerchantMetaVo;
import io.mjd507.entity.MerchantUserVo;
import io.mjd507.service.MerchantService;
import io.mjd507.common.MerchantUserAttrSetter;
import io.mjd507.service.impl.MerchantUserServiceImpl;
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
 * Created by mjd on 2018/4/18 19:40
 */
@RestController
public class MerchantsServiceController extends MerchantUserAttrSetter {

  @Autowired
  MerchantService merchantService;

  @ApiOperation(value = "申请成为商家")
  @ResponseBody
  @RequestMapping(value = "applyMerchant", method = RequestMethod.POST)
  public DataResponse<String> ApplyMerchant(
      @ModelAttribute(Constants.USER_ATTR) MerchantUserVo user,
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
  public DataResponse<String> deleteMerchant(String merchantId) {
    boolean i = merchantService.deleteMerchant(merchantId);
    String data = i ? "删除成功" : "删除失败";
    return new DataResponse<>(data);
  }

  @ApiOperation(value = "修改商家", notes = "修改接入的商家信息")
  @ResponseBody
  @RequestMapping(value = "modifyMerchant", method = RequestMethod.POST)
  public DataResponse<String> modifyMerchant(
      @ModelAttribute(Constants.USER_ATTR) MerchantUserVo user,
      @RequestBody MerchantMetaVo merchantVo) {
    merchantVo.setMerchantId("MER" + user.getUserId());
    boolean i = merchantService.updateMerchant(merchantVo);
    String data = i ? "修改成功" : "修改失败";
    return new DataResponse<>(data);
  }

  @ApiOperation(value = "商家列表", notes = "查看所有商家列表")
  @ResponseBody
  @RequestMapping(value = "getMerchantList", method = RequestMethod.GET)
  public DataResponse<List<MerchantMetaVo>> getMerchantList() {
    List<MerchantMetaVo> allMerchants = merchantService.getAllMerchants();
    return new DataResponse<>(allMerchants);
  }

}
