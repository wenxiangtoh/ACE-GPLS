package com.service.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@EntityScan("com.service")
@EnableJpaRepositories(basePackages = "com.service.portal.repository")
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.service.portal.service"})
public class PortalServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PortalServiceApplication.class, args);
  }
}
