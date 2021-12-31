package com.service.user.controller;

import com.service.library.service.model.UserModel;
import com.service.user.service.UserCommandService;
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

  /**
   * Method to create user
   *
   * @param userModel user model containing user information
   */
  @PostMapping("/interior/users")
  public void createUser(@RequestBody @Valid UserModel userModel) {
    userCommandService.createUser(userModel);
  }
}
