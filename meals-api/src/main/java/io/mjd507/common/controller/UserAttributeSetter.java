package io.mjd507.common.controller;

import io.mjd507.common.constants.Constants;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by mjd on 2018/4/20 22:25
 */
public class UserAttributeSetter {

  @ModelAttribute
  public void putRequest(HttpServletRequest request, Model model) {
    String userId = (String) request.getAttribute(Constants.USER_ATTR);
    model.addAttribute(Constants.USER_ATTR, userId);
  }
}
