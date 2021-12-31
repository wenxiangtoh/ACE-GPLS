package com.service.user.service;

import com.service.user.service.model.CreateUserModel;

public interface UserCommandService {

  /**
   * Method to create user
   *
   * @param createUserModel create user model containing user informations
   */
  void createUser(CreateUserModel createUserModel);
}
