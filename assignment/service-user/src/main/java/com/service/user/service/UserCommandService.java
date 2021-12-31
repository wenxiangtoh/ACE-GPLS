package com.service.user.service;

import com.service.user.service.model.UserModel;

public interface UserCommandService {

  /**
   * Method to create user
   *
   * @param userModel user model containing user information
   */
  void createUser(UserModel userModel);
}
