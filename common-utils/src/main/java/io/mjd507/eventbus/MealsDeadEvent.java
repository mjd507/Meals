package io.mjd507.eventbus;

import com.google.common.eventbus.DeadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mjd on 2018/6/13 19:41
 */
public class MealsDeadEvent extends DeadEvent {

  private static final Logger logger = LoggerFactory.getLogger(MealsDeadEvent.class);

  public MealsDeadEvent(Object source, Object event) {
    super(source, event);
    logger.warn("MealsDeadEvent 死亡事件：{}", toString());
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
