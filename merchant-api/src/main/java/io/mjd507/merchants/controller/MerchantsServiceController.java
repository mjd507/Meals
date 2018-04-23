package io.mjd507.merchants.controller;

import io.mjd507.StringUtils;
import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.MerchantMetaVo;
import io.mjd507.service.MerchantService;
import io.mjd507.common.MerchantUserAttrSetter;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/18 19:40
 */
@RestController
@RequestMapping(value = "merchant")
public class MerchantsServiceController extends MerchantUserAttrSetter {

  @Autowired
  MerchantService merchantService;

  @ApiOperation(value = "申请成为商家")
  @ResponseBody
  @RequestMapping(value = "applyMerchant", method = RequestMethod.POST)
  public DataResponse<List<MerchantMetaVo>> ApplyMerchant(@RequestBody MerchantMetaVo merchantVo) {
    DataResponse<List<MerchantMetaVo>> response = null;
    boolean isVaild = vaildMerchant(merchantVo);
    if (isVaild) {
      merchantVo.setMerchantId("MER");
      merchantService.addMerchant(merchantVo);
    } else {
      response = new DataResponse<>();
      response.setFailure("请补全商户名称等信息");
    }

    List<MerchantMetaVo> waitActiveMerchant = merchantService.findWaitActiveMerchant();
    response = new DataResponse<>(waitActiveMerchant);
    response = new DataResponse<>();
    response.setFailure("暂无权限");
    return response;
  }

  private boolean vaildMerchant(MerchantMetaVo merchantVo) {
    return !StringUtils.isEmpty(merchantVo.getName());
  }
}
