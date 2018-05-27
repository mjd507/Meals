package io.mjd507.mch;

import com.google.common.base.Strings;
import io.mjd507.CopyUtils;
import io.mjd507.MchMetaDo;
import io.mjd507.MchMetaService;
import io.mjd507.common.DataResponse;
import io.mjd507.common.MchUserAttrSetter;
import io.mjd507.module.login.Constants;
import io.mjd507.module.login.TokenUtils;
import io.mjd507.module.mchuser.MchUserDto;
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
  MchMetaService merchantService;

  @ApiOperation(value = "申请成为商家")
  @ResponseBody
  @RequestMapping(value = "applyMerchant", method = RequestMethod.POST)
  public DataResponse<String> ApplyMerchant(
      @ModelAttribute(Constants.HEADER_AUTH) MchUserDto userDto,
      @RequestBody MchMetaReqVo metaReqVo) {
    DataResponse<String> response;
    boolean isValid = checkMetaInfo(metaReqVo);
    MchMetaDo mchMetaDo = CopyUtils.copyObject(metaReqVo, MchMetaDo.class);
    if (isValid) {
      mchMetaDo.setMchUserId(userDto.getUserId());
      mchMetaDo.setMchId(TokenUtils.buildToken());
      mchMetaDo.setIsActive((short) 0);
      merchantService.addMerchant(mchMetaDo);
      response = new DataResponse<>("申请成功，一般两天内给出审核结果");
    } else {
      response = new DataResponse<>();
      response.setFailure("请补全商户名称等信息");
    }
    return response;
  }

  private boolean checkMetaInfo(MchMetaReqVo mchReqVo) {
    return !Strings.isNullOrEmpty(mchReqVo.getMchName()) && !Strings
        .isNullOrEmpty(mchReqVo.getMchLocation());
  }

  @ApiOperation(value = "删除商家", notes = "删除接入的商家")
  @ResponseBody
  @RequestMapping(value = "deleteMerchant", method = RequestMethod.POST)
  public DataResponse<String> deleteMerchant(
      @ModelAttribute(Constants.HEADER_AUTH) MchUserDto userDto) {
    int count = merchantService.deleteMerchant(userDto.getUserId());
    return new DataResponse<>(count > 0 ? "删除成功" : "删除失败");
  }

  @ApiOperation(value = "修改商家", notes = "修改接入的商家信息")
  @ResponseBody
  @RequestMapping(value = "modifyMerchant", method = RequestMethod.POST)
  public DataResponse<String> modifyMerchant(
      @ModelAttribute(Constants.HEADER_AUTH) MchUserDto userDto,
      @RequestBody MchMetaReqVo metaReqVo) {
    DataResponse<String> response;
    boolean isValid = checkMetaInfo(metaReqVo);
    if (isValid) {
      MchMetaDo mchMetaDo = CopyUtils.copyObject(metaReqVo, MchMetaDo.class);
      mchMetaDo.setMchUserId(userDto.getUserId());
      int count = merchantService.updateMerchant(mchMetaDo);
      response = new DataResponse<>(count > 0 ? "修改成功" : "修改失败");
    } else {
      response = new DataResponse<>();
      response.setFailure("请补全商户名称等信息");
    }
    return response;

  }

}
