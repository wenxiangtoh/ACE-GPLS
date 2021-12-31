package com.service.portal.service;

import com.service.library.repository.config.FeignConfig;
import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.library.service.model.UserModel;
import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "USER-SERVICE", configuration = FeignConfig.class)
public interface UserConfigFeignClient {

  @PostMapping("/interior/users")
  void createUser(@RequestBody @Valid UserModel userModel);

  @PostMapping("/interior/users/search")
  UserInfoModel findUserByNameAndEmail(@RequestBody @Valid SearchUserModel searchUserModel);
}
