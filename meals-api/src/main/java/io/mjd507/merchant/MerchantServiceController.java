package io.mjd507.merchant;

import io.mjd507.common.UserAttrSetter;
import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.UserVo;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/21 10:22
 */
@RestController
public class MerchantServiceController extends UserAttrSetter {

  @ApiOperation(value = "商家列表", notes = "查看所有商家列表")
  @ResponseBody
  @RequestMapping(value = "getMerchantList", method = RequestMethod.GET)
  public DataResponse<List<MerchantVo>> getMerchantList() {

    return null;
  }

}
