package io.mjd507;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by mjd on 2018/6/25 19:38
 */
@EnableConfigServer
@SpringBootApplication
public class AppMain {

  public static void main(String[] args) {
    SpringApplication.run(AppMain.class, args);
  }
}
