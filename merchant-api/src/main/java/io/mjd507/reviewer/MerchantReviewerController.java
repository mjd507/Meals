package io.mjd507.reviewer;

import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.MerchantMetaVo;
import io.mjd507.entity.UserVo;
import io.mjd507.service.MerchantService;
import io.mjd507.common.MerchantUserAttrSetter;
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
 * 商家审核者
 *
 * Created by mjd on 2018/4/21 10:22
 */
@RestController
@RequestMapping(value = "reviewer")
public class MerchantReviewerController extends MerchantUserAttrSetter {

  @Autowired
  MerchantService merchantService;

  @ApiOperation(value = "查看待审核商家列表")
  @ResponseBody
  @RequestMapping(value = "getApplyMerchants", method = RequestMethod.GET)
  public DataResponse<List<MerchantMetaVo>> getApplyMerchants(@ModelAttribute UserVo user) {
    boolean reviewer = isReviewer(user);
    DataResponse<List<MerchantMetaVo>> response = null;
    if (reviewer) {
      List<MerchantMetaVo> waitActiveMerchant = merchantService.findWaitActiveMerchant();
      response = new DataResponse<>(waitActiveMerchant);
    } else {
      response = new DataResponse<>();
      response.setFailure("暂无权限");
    }
    return response;
  }

  @ApiOperation(value = "同意商家入驻", notes = "会为用户分配一个商户号")
  @ResponseBody
  @RequestMapping(value = "agreeApplyMerchant", method = RequestMethod.GET)
  public DataResponse<String> agreeApplyMerchant(@ModelAttribute UserVo user, String merchantId) {
    boolean reviewer = isReviewer(user);
    DataResponse<String> response = null;
    if (reviewer) {
      boolean isSuccess = merchantService.activeMerchant(merchantId);
      if (isSuccess) {
        MerchantMetaVo merchantVo = new MerchantMetaVo();
        boolean add = merchantService.addMerchant(merchantVo);
      }
      response = new DataResponse<>();
    } else {
      response = new DataResponse<>();
      response.setFailure("暂无权限");
    }
    return response;
  }


  @ApiOperation(value = "添加商家", notes = "接入新的商家")
  @ResponseBody
  @RequestMapping(value = "addMerchant", method = RequestMethod.POST)
  public DataResponse<String> addMerchant(@ModelAttribute UserVo user,
      @RequestBody MerchantMetaVo merchantVo) {
    boolean isMerchant = isMerchant(user);
    DataResponse<String> response = null;
    if (isMerchant) {
      boolean i = merchantService.addMerchant(merchantVo);
      String data = i  ? "添加成功" : "添加失败";
      response = new DataResponse<>(data);
    } else {
      response = new DataResponse<>("身份不合法");
    }
    return response;
  }

  @ApiOperation(value = "删除商家", notes = "删除接入的商家")
  @ResponseBody
  @RequestMapping(value = "deleteMerchant", method = RequestMethod.POST)
  public DataResponse<String> deleteMerchant(int merId) {
    boolean i = merchantService.deleteMerchant(merId);
    String data = i  ? "删除成功" : "删除失败";
    return new DataResponse<>(data);
  }

  @ApiOperation(value = "修改商家", notes = "修改接入的商家信息")
  @ResponseBody
  @RequestMapping(value = "modifyMerchant", method = RequestMethod.POST)
  public DataResponse<String> modifyMerchant(@RequestBody MerchantMetaVo merchantVo) {
    boolean i = merchantService.updateMerchant(merchantVo);
    String data = i  ? "删除成功" : "删除失败";
    return new DataResponse<>(data);
  }

  @ApiOperation(value = "商家列表", notes = "查看所有商家列表")
  @ResponseBody
  @RequestMapping(value = "getMerchantList", method = RequestMethod.GET)
  public DataResponse<List<MerchantMetaVo>> modifyMerchant() {
    List<MerchantMetaVo> allMerchants = merchantService.getAllMerchants();
    return new DataResponse<>(allMerchants);
  }

  private boolean isMerchant(UserVo userVo) {
    return userVo != null && userVo.getUserType().equals("merchanter");
  }

  private boolean isReviewer(UserVo userVo) {
    return userVo != null && userVo.getUserType().equals("reviewer");
  }

}
