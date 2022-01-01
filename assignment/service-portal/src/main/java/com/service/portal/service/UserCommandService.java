package com.service.portal.service;

import com.service.library.service.model.UserModel;

/**
 * Service Interface for user command
 *
 * @author Wen Xiang
 */
public interface UserCommandService {

  /**
   * Method to create user
   *
   * @param userModel user model containing user information
   */
  void createUser(UserModel userModel);
}
