package com.service.portal.service;

import com.service.library.service.model.ContactNumberModel;
import com.service.library.service.model.UserModel;
import com.service.portal.Constants;
import com.service.portal.repository.AgencyRepository;
import com.service.portal.repository.ContactNumberRepository;
import com.service.portal.repository.UserRepository;
import com.service.portal.repository.entity.Agency;
import com.service.portal.repository.entity.ContactNumber;
import com.service.portal.repository.entity.User;
import com.service.portal.service.impl.UserCommandServiceImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Unit Test for methods within User Command Service
 *
 * @author Wen Xiang
 */
@RunWith(MockitoJUnitRunner.class)
public class UserCommandServiceTest {

  @InjectMocks private UserCommandServiceImpl userCommandService;

  @Mock private AgencyRepository agencyRepository;
  @Mock private ContactNumberRepository contactNumberRepository;
  @Mock private UserRepository userRepository;

  @Test
  public void createUser_agencyNotFound() {
    ContactNumberModel contactNumber = new ContactNumberModel();
    contactNumber.setCountryCode(65L);
    contactNumber.setNumber("12345678");

    UserModel userModel =
        UserModel.builder()
            .agencyUuid(UUID.randomUUID().toString())
            .name("name")
            .email("123@gmail.com")
            .contactNumber(contactNumber)
            .build();

    Agency agency = new Agency();

    Mockito.when(agencyRepository.findAgencyByUuid(Mockito.anyString()))
        .thenReturn(Optional.of(agency));

    userCommandService.createUser(userModel);

    Mockito.verify(contactNumberRepository, Mockito.times(1))
        .save(Mockito.any(ContactNumber.class));
    Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
  }

  @Test
  public void createUser() {
    ContactNumberModel contactNumber = new ContactNumberModel();
    contactNumber.setCountryCode(65L);
    contactNumber.setNumber("12345678");

    UserModel userModel =
        UserModel.builder()
            .agencyUuid(UUID.randomUUID().toString())
            .name("name")
            .email("123@gmail.com")
            .contactNumber(contactNumber)
            .build();

    Mockito.when(agencyRepository.findAgencyByUuid(Mockito.anyString()))
        .thenReturn(Optional.empty());

    var exception =
        Assert.assertThrows(
            ResponseStatusException.class, () -> userCommandService.createUser(userModel));
    Assert.assertEquals(Constants.AGENCY_NOT_FOUND, exception.getReason());
    Assert.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
  }
}
