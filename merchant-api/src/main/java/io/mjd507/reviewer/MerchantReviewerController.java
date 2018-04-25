package io.mjd507.reviewer;

import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.MerchantMetaVo;
import io.mjd507.service.MerchantService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家审核者
 *
 * Created by mjd on 2018/4/21 10:22
 */
@RestController
@RequestMapping(value = "reviewer")
public class MerchantReviewerController {

  @Autowired
  MerchantService merchantService;

  @ApiOperation(value = "查看待审核商家列表")
  @ResponseBody
  @RequestMapping(value = "getApplyMerchants", method = RequestMethod.GET)
  public DataResponse<List<MerchantMetaVo>> getApplyMerchants() {
    List<MerchantMetaVo> waitActiveMerchant = merchantService.findWaitActiveMerchant();
    return new DataResponse<>(waitActiveMerchant);
  }

  @ApiOperation(value = "同意商家入驻")
  @ResponseBody
  @RequestMapping(value = "agreeApplyMerchant", method = RequestMethod.POST)
  public DataResponse<String> agreeApplyMerchant(String merchantId) {
    boolean isSuccess = merchantService.activeMerchant(merchantId);
    return new DataResponse<>(isSuccess ? "审核完成" : "审核失败");
  }

}
