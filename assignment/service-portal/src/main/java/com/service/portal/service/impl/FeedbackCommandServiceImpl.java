package com.service.portal.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.library.Constants;
import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.portal.converter.FeedbackConverter;
import com.service.portal.converter.UserModelConverter;
import com.service.portal.enumeration.FeedbackStatus;
import com.service.portal.repository.FeedbackRepository;
import com.service.portal.repository.entity.Feedback;
import com.service.portal.service.FeedbackCommandService;
import com.service.portal.service.UserCommandService;
import com.service.portal.service.UserQueryService;
import com.service.portal.service.model.CreateFeedbackModel;
import com.service.portal.service.model.FeedbackStatusResponseModel;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service Implementation for feedback command
 *
 * @author Wen Xiang
 */
@Service
public class FeedbackCommandServiceImpl implements FeedbackCommandService {

  private static final Logger logger = LoggerFactory.getLogger(FeedbackCommandServiceImpl.class);

  @Autowired private UserQueryService userQueryService;
  @Autowired private UserCommandService userCommandService;

  @Autowired private FeedbackRepository feedbackRepository;

  @Value("${feedback.status.url}")
  private String feedbackStatusUrl;

  private RestTemplate restTemplate = new RestTemplate();
  private ObjectMapper mapper = new ObjectMapper();

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

  /** {@inheritDoc} */
  @Override
  public void processFeedbackStatus() throws JsonProcessingException {
    List<Feedback> feedbacks = feedbackRepository.findFeedbackByStatus(FeedbackStatus.PROCESSING);

    logger.info(com.service.portal.Constants.PROCESS_FEEDBACK_STATUS_START);
    for (Feedback feedback : feedbacks) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(feedbackStatusUrl);
      stringBuilder.append(feedback.getDescription());

      ResponseEntity<String> response =
          restTemplate.getForEntity(stringBuilder.toString(), String.class);
      var feedbackStatusResponseModel =
          mapper.readValue(response.getBody(), FeedbackStatusResponseModel.class);
      var sentiment = feedbackStatusResponseModel.getSentiment();

      feedback.setStatus(FeedbackStatus.REJECTED);
      if (sentiment.equals(com.service.portal.Constants.POSITIVE)) {
        feedback.setStatus(FeedbackStatus.ACCEPTED);
      }
    }
    feedbackRepository.saveAll(feedbacks);
    logger.info(com.service.portal.Constants.PROCESS_FEEDBACK_STATUS_END);
  }
}
