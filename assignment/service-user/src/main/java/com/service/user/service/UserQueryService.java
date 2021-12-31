package com.service.user.service;

import com.service.user.service.model.SearchUserModel;
import com.service.user.service.model.UserInfoModel;

public interface UserQueryService {

  /**
   * Retrieve the user by name and email
   *
   * @param searchUserModel search user model
   * @return UserInfoModel user info model
   */
  UserInfoModel findUserByNameAndEmail(SearchUserModel searchUserModel);
}
