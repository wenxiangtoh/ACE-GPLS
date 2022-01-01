package com.service.portal.service.impl;

import com.service.library.Constants;
import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.portal.converter.FeedbackConverter;
import com.service.portal.converter.UserModelConverter;
import com.service.portal.enumeration.FeedbackStatus;
import com.service.portal.repository.FeedbackRepository;
import com.service.portal.service.FeedbackCommandService;
import com.service.portal.service.UserCommandService;
import com.service.portal.service.UserQueryService;
import com.service.portal.service.model.CreateFeedbackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackCommandServiceImpl implements FeedbackCommandService {

  @Autowired private UserQueryService userQueryService;
  @Autowired private UserCommandService userCommandService;

  @Autowired private FeedbackRepository feedbackRepository;

  /** {@inheritDoc} */
  @Override
  public void createFeedback(CreateFeedbackModel createFeedbackModel) {
    var name = createFeedbackModel.getName();
    var email = createFeedbackModel.getEmail();
    var contactNumber = createFeedbackModel.getContactNumber();
    var agencyUuid = createFeedbackModel.getAgencyUuid();

    var searchUserModel = new SearchUserModel(name, email);
    UserInfoModel userInfoModel = userQueryService.findUserByNameAndEmail(searchUserModel);
    var userId = userInfoModel.getId();

    if (userId == Constants.ANNOYMOUS_USER_ID) {
      var userModel = UserModelConverter.convertFrom(name, email, contactNumber, agencyUuid);

      userCommandService.createUser(userModel);

      userInfoModel = userQueryService.findUserByNameAndEmail(searchUserModel);
      userId = userInfoModel.getId();
    }
    var description = createFeedbackModel.getText();
    var feedback = FeedbackConverter.convertFrom(userId, description, FeedbackStatus.PROCESSING);

    feedbackRepository.save(feedback);
  }
}
