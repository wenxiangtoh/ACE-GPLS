package com.service.portal.service;

import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;

/**
 * Service Interface for user query
 *
 * @author Wen Xiang
 */
public interface UserQueryService {

  /**
   * Retrieve the user by name and email
   *
   * @param searchUserModel search user model
   * @return UserInfoModel user info model
   */
  UserInfoModel findUserByNameAndEmail(SearchUserModel searchUserModel);
}
