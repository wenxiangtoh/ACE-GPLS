package com.service.user.service.impl;

import com.service.user.Constants;
import com.service.user.repository.UserRepository;
import com.service.user.repository.entity.User;
import com.service.user.service.UserQueryService;
import com.service.user.service.model.SearchUserModel;
import com.service.user.service.model.UserInfoModel;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserQueryServiceImpl implements UserQueryService {

  private static final Logger logger = LoggerFactory.getLogger(UserQueryServiceImpl.class);

  @Autowired private UserRepository userRepository;

  public UserInfoModel findUserByNameAndEmail(SearchUserModel searchUserModel) {
    var name = searchUserModel.getName();
    var email = searchUserModel.getEmail();

    Optional<User> optionalUser = userRepository.findUserByNameAndEmail(name, email);
    if (optionalUser.isEmpty()) {
      logger.error(Constants.USER_NOT_FOUND, name, email);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND);
    }
    var user = optionalUser.get();

    var userInfoModel = new UserInfoModel();
    userInfoModel.setId(user.getId());
    userInfoModel.setName(name);
    userInfoModel.setEmail(email);

    return userInfoModel;
  }
}
