package io.mjd507;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

/**
 * bean 和 json 转换工具类
 *
 * Created by mjd on 2018/3/25 11:19
 */
public class GsonUtils {

  private static Gson gson = new Gson();

  private GsonUtils() {
  }

  public static String toJsonStr(Object object) {
    return gson.toJson(object);
  }

  public static <T> T toBean(String jsonStr, Class<T> clazz) {
    return gson.fromJson(jsonStr, clazz);
  }

  public static <T> List<T> toList(String jsonStr, Class<T> clazz) {
    List<T> list = new ArrayList<>();
    JsonArray array = new JsonParser().parse(jsonStr).getAsJsonArray();
    for (final JsonElement el : array) {
      list.add(gson.fromJson(el, clazz));
    }
    return list;
  }
}
