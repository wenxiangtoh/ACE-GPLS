package com.service.user.service.impl;

import com.service.user.Constants;
import com.service.user.repository.AgencyRepository;
import com.service.user.repository.ContactNumberRepository;
import com.service.user.repository.UserRepository;
import com.service.user.repository.entity.Agency;
import com.service.user.repository.entity.ContactNumber;
import com.service.user.repository.entity.User;
import com.service.user.service.UserCommandService;
import com.service.user.service.model.ContactNumberModel;
import com.service.user.service.model.UserModel;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserCommandServiceImpl implements UserCommandService {

  private static final Logger logger = LoggerFactory.getLogger(UserCommandServiceImpl.class);

  @Autowired private UserRepository userRepository;
  @Autowired private AgencyRepository agencyRepository;
  @Autowired private ContactNumberRepository contactNumberRepository;

  /** {@inheritDoc} */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void createUser(UserModel createUserModel) {
    var agencyUuid = createUserModel.getAgencyUuid();
    var agency = checkAgencyExistsByUuid(agencyUuid);
    var userUuid = UUID.randomUUID().toString();
    var name = createUserModel.getName();
    var email = createUserModel.getEmail();

    ContactNumber savedContactNumber =
        retrieveSavedContactNumber(createUserModel.getContactNumber());

    User user =
        User.builder()
            .uuid(userUuid)
            .name(name)
            .email(email)
            .contactNumber(savedContactNumber)
            .agency(agency)
            .createdByUserId(Constants.ANNOYMOUS_USER_ID)
            .createdAt(LocalDateTime.now())
            .updatedByUserId(Constants.ANNOYMOUS_USER_ID)
            .updatedAt(LocalDateTime.now())
            .build();

    userRepository.save(user);
  }

  private Agency checkAgencyExistsByUuid(String uuid) {
    Optional<Agency> optionalAgency = agencyRepository.findAgencyByUuid(uuid);

    if (optionalAgency.isEmpty()) {
      logger.error(Constants.AGENCY_NOT_FOUND, uuid);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.AGENCY_NOT_FOUND);
    }
    return optionalAgency.get();
  }

  private ContactNumber retrieveSavedContactNumber(ContactNumberModel contactNumberModel) {
    ContactNumber contactNumber = new ContactNumber();
    contactNumber.setCountryCode(contactNumberModel.getCountryCode());
    contactNumber.setNumber(contactNumberModel.getNumber());
    contactNumber.setCreatedByUserId(Constants.ANNOYMOUS_USER_ID);
    contactNumber.setCreatedAt(LocalDateTime.now());
    contactNumber.setUpdatedAt(LocalDateTime.now());
    contactNumber.setUpdatedByUserId(Constants.ANNOYMOUS_USER_ID);

    return contactNumberRepository.save(contactNumber);
  }
}
