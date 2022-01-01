package com.service.portal.service.impl;

import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.portal.Constants;
import com.service.portal.repository.UserRepository;
import com.service.portal.repository.entity.User;
import com.service.portal.service.UserQueryService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQueryServiceImpl implements UserQueryService {

  private static final Logger logger = LoggerFactory.getLogger(UserQueryServiceImpl.class);

  @Autowired private UserRepository userRepository;

  /** {@inheritDoc} */
  @Override
  public UserInfoModel findUserByNameAndEmail(SearchUserModel searchUserModel) {
    var name = searchUserModel.getName();
    var email = searchUserModel.getEmail();

    Optional<User> optionalUser = userRepository.findUserByNameAndEmail(name, email);
    if (optionalUser.isEmpty()) {
      logger.warn(Constants.USER_NOT_FOUND, name, email);
      return new UserInfoModel();
    }
    var user = optionalUser.get();

    var userInfoModel = new UserInfoModel();
    userInfoModel.setId(user.getId());
    userInfoModel.setName(name);
    userInfoModel.setEmail(email);

    return userInfoModel;
  }
}
