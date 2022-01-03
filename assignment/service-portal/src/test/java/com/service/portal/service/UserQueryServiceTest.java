package com.service.portal.service;

import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.portal.repository.UserRepository;
import com.service.portal.repository.entity.User;
import com.service.portal.service.impl.UserQueryServiceImpl;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit Test for methods within User Query Service
 *
 * @author Wen Xiang
 */
@RunWith(MockitoJUnitRunner.class)
public class UserQueryServiceTest {

  @InjectMocks private UserQueryServiceImpl userQueryService;

  @Mock private UserRepository userRepository;

  @Test
  public void findUserByNameAndEmail() {
    SearchUserModel searchUserModel = new SearchUserModel();
    searchUserModel.setName("name");
    searchUserModel.setEmail("123@gmail.com");

    User user = new User();
    user.setId(1L);

    Mockito.when(userRepository.findUserByNameAndEmail(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Optional.of(user));

    UserInfoModel result = userQueryService.findUserByNameAndEmail(searchUserModel);

    Assert.assertNotNull(result);
    Assert.assertEquals(user.getId(), result.getId());
  }

  @Test
  public void findUserByNameAndEmail_userNotFound() {
    SearchUserModel searchUserModel = new SearchUserModel();
    searchUserModel.setName("name");
    searchUserModel.setEmail("123@gmail.com");

    User user = new User();

    Mockito.when(userRepository.findUserByNameAndEmail(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Optional.empty());

    UserInfoModel result = userQueryService.findUserByNameAndEmail(searchUserModel);

    Assert.assertNotNull(result);
    Assert.assertEquals(user.getId(), result.getId());
  }
}
