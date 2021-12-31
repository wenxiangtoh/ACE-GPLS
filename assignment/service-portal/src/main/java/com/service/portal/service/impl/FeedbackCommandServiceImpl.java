package com.service.portal.service.impl;

import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.portal.repository.FeedbackRepository;
import com.service.portal.service.FeedbackCommandService;
import com.service.portal.service.UserCommandService;
import com.service.portal.service.UserQueryService;
import com.service.portal.service.model.CreateFeedbackModel;
import java.util.UUID;
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

    if (null == userInfoModel) {
      //      var userModel =
      //          com.service.library.service.model.UserModel.builder()
      //              .name(name)
      //              .email(email)
      //              .contactNumber(contactNumber)
      //              .agencyUuid(agencyUuid)
      //              .build();
      //      userCommandService.createUser(userModel);
    }
    var feedbackUuid = UUID.randomUUID().toString();
    var description = createFeedbackModel.getText();

    //    var feedback =
    //        Feedback.builder()
    //            .uuid(feedbackUuid)
    //            .userId(userInfoModel.getId())
    //            .description(description)
    //            .createdByUserId(Constants.ANNOYMOUS_USER_ID)
    //            .createdAt(LocalDateTime.now())
    //            .updatedByUserId(Constants.ANNOYMOUS_USER_ID)
    //            .updatedAt(LocalDateTime.now())
    //            .build();
    //    feedbackRepository.save(feedback);
  }
}
