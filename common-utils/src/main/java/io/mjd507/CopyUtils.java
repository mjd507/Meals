package io.mjd507;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 * Created by mjd on 2018/5/25 13:12
 */
public class CopyUtils {

  public static <F, T> List<T> copyList(List<F> fList, Class<T> toClass) {

    List<T> tList = new ArrayList<T>();
    try {
      if (fList == null) {
        return tList;
      }

      for (F f : fList) {
        T t = toClass.newInstance();
        BeanUtils.copyProperties(f, t);
        tList.add(t);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tList;
  }

  public static <F, T> T copyObject(F f, Class<T> toClass) {

    try {
      if (f == null) {
        return null;
      }
      T t = toClass.newInstance();
      BeanUtils.copyProperties(f, t);
      return t;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
