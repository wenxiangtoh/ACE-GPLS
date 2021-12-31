package com.service.portal.controller;

import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.portal.service.UserQueryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserQueryController {

  @Autowired private UserQueryService userQueryService;

  /**
   * Retrieve the user by name and email
   *
   * @param searchUserModel search user model
   * @return UserInfoModel user info model
   */
  @PostMapping("/interior/users/search")
  public ResponseEntity<UserInfoModel> findUserByNameAndEmail(
      @RequestBody @Valid SearchUserModel searchUserModel) {
    return ResponseEntity.ok(userQueryService.findUserByNameAndEmail(searchUserModel));
  }
}
