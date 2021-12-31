package com.service.feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EntityScan("com.service")
@EnableJpaRepositories(basePackages = "com.service.feedback.repository")
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.service.feedback.service"})
public class FeedbackApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeedbackApplication.class, args);
  }
}
