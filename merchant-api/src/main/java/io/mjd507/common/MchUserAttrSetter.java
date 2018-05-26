package io.mjd507.common;

import io.mjd507.module.login.Constants;
import io.mjd507.module.mchuser.MchUserDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by mjd on 2018/4/21 18:12
 */
public class MchUserAttrSetter {

  @ModelAttribute
  public void putAttr(HttpServletRequest request, Model mode) {
    MchUserDto userDto = (MchUserDto) request.getAttribute(Constants.HEADER_AUTH);
    mode.addAttribute(Constants.HEADER_AUTH, userDto);
  }
}
