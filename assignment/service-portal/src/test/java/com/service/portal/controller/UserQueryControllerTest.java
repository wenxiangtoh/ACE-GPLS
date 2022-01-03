package com.service.portal.controller;

import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.portal.service.UserQueryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit Test for methods within User Query Controller
 *
 * @author Wen Xiang
 */
@RunWith(MockitoJUnitRunner.class)
public class UserQueryControllerTest {

  @InjectMocks UserQueryController userQueryController;

  @Mock private UserQueryService userQueryService;

  @Test
  public void findUserByNameAndEmail() {
    UserInfoModel userInfoModel = new UserInfoModel();
    SearchUserModel searchUserModel = new SearchUserModel();

    Mockito.when(userQueryService.findUserByNameAndEmail(searchUserModel))
        .thenReturn(userInfoModel);

    var response = userQueryController.findUserByNameAndEmail(searchUserModel);

    Assert.assertEquals(200, response.getStatusCode().value());
    Assert.assertEquals(userInfoModel, response.getBody());
  }
}
