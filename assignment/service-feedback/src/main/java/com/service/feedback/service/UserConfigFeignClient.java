package com.service.feedback.service;

import com.service.library.repository.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "USER-SERVICE", configuration = FeignConfig.class)
public interface UserConfigFeignClient {}
