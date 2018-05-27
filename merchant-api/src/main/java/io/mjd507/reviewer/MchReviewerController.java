package io.mjd507.reviewer;

import io.mjd507.CopyUtils;
import io.mjd507.MchMetaDto;
import io.mjd507.MchMetaService;
import io.mjd507.MchMetaVo;
import io.mjd507.common.DataResponse;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家审核者
 *
 * Created by mjd on 2018/4/21 10:22
 */
@RestController
@RequestMapping(value = "reviewer")
public class MchReviewerController {

  @Autowired
  MchMetaService mchMetaService;

  @ApiOperation(value = "查看待审核商家列表")
  @ResponseBody
  @RequestMapping(value = "getApplyMchList", method = RequestMethod.GET)
  public DataResponse<List<MchMetaVo>> getApplyMerchants() {
    List<MchMetaDto> waitActiveList = mchMetaService.getWaitActiveMerchant();
    List<MchMetaVo> metaVoList = CopyUtils.copyList(waitActiveList, MchMetaVo.class);
    return new DataResponse<>(metaVoList);
  }

  @ApiOperation(value = "同意商家入驻")
  @ResponseBody
  @RequestMapping(value = "agreeApplyMch", method = RequestMethod.POST)
  public DataResponse<String> agreeApplyMerchant(@RequestParam String mchId) {
    int count = mchMetaService.activeMerchant(mchId);
    return new DataResponse<>(count > 0 ? "审核完成" : "审核失败");
  }

}
