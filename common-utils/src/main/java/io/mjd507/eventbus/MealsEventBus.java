package io.mjd507.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Created by mjd on 2018/6/13 19:32
 */
public class MealsEventBus {

  private static final EventBus bus = new EventBus();

  private MealsEventBus() {
  }

  public static EventBus getInstance() {
    return bus;
  }
}
