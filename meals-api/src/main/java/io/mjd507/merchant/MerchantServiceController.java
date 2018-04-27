package io.mjd507.merchant;

import io.mjd507.common.ConfigProperties;
import io.mjd507.common.UserAttrSetter;
import io.mjd507.common.request.DataResponse;
import io.mjd507.http.HttpRequest;
import io.mjd507.http.HttpUtils;
import io.mjd507.merchant.entity.MealsVo;
import io.mjd507.merchant.entity.MerchantVo;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
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

    HttpRequest request = new HttpRequest();
    request.setUrl(ConfigProperties.MerchantUrl + "/api/getMerchantList");
    DataResponse response = HttpUtils.doRequest(request, DataResponse.class);
    List<MerchantVo> merchantList = (List<MerchantVo>) response.getData();
    return new DataResponse<>(merchantList);
  }

  @ApiOperation(value = "根据商家获取菜单")
  @ResponseBody
  @RequestMapping(value = "getMealsByMerchant", method = RequestMethod.GET)
  public DataResponse<List<MealsVo>> getMealsByMerchant(String merchantId) {
    HttpRequest request = new HttpRequest();
    request.setUrl(ConfigProperties.MerchantUrl + "/api/getMealsByMerchant");
    HashMap<String, String> params = new HashMap<>();
    params.put("merchantId", merchantId);
    request.setParams(params);
    DataResponse response = HttpUtils.doRequest(request, DataResponse.class);
    List<MealsVo> mealsList = (List<MealsVo>) response.getData();
    return new DataResponse<>(mealsList);
  }

}
