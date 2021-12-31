package com.service.user.controller;

import com.service.user.service.UserCommandService;
import com.service.user.service.model.CreateUserModel;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserCommandController {

  @Autowired private UserCommandService userCommandService;

  @PostMapping("/interior/users")
  public void createUser(@RequestBody @Valid CreateUserModel createUserModel) {
    userCommandService.createUser(createUserModel);
  }
}
