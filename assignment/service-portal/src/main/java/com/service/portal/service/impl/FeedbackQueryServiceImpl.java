package com.service.portal.service.impl;

import com.service.library.service.model.ContactNumberModel;
import com.service.portal.Constants;
import com.service.portal.repository.FeedbackRepository;
import com.service.portal.repository.UserRepository;
import com.service.portal.repository.entity.Feedback;
import com.service.portal.repository.entity.User;
import com.service.portal.service.FeedbackQueryService;
import com.service.portal.service.model.FeedbackInfoModel;
import com.service.portal.service.model.FeedbackInfoRequestModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service Implementation for feedback query
 *
 * @author Wen Xiang
 */
@Service
public class FeedbackQueryServiceImpl implements FeedbackQueryService {

  private static final Logger logger = LoggerFactory.getLogger(FeedbackQueryServiceImpl.class);

  @Autowired private FeedbackRepository feedbackRepository;
  @Autowired private UserRepository userRepository;

  /** {@inheritDoc} */
  @Override
  public List<FeedbackInfoModel> findFeedbacksByEmailAndContactNumber(
      FeedbackInfoRequestModel feedbackInfoRequestModel) {
    var email = feedbackInfoRequestModel.getEmail();
    var contactNumber = feedbackInfoRequestModel.getContactNumber();
    var countryCode = contactNumber.getCountryCode();
    var number = contactNumber.getNumber();

    List<Feedback> feedbacks =
        feedbackRepository.findFeedbacksByEmailAndContactNumber(email, countryCode, number);

    if (feedbacks.isEmpty()) {
      return new ArrayList<>();
    }
    var feedbackInfoModelMap = constructFeedbackInfoModelMap(feedbacks, contactNumber);

    List<FeedbackInfoModel> result = new ArrayList<>(feedbackInfoModelMap.size());
    for (Map.Entry<Long, List<FeedbackInfoModel>> entry : feedbackInfoModelMap.entrySet()) {
      Long userId = entry.getKey();
      var user = checkUserExistsById(userId, email, countryCode, number);
      var feedbackInfoModels = feedbackInfoModelMap.get(userId);

      for (FeedbackInfoModel feedbackInfoModel : feedbackInfoModels) {
        feedbackInfoModel.setName(user.getName());
        feedbackInfoModel.setEmail(user.getEmail());
        feedbackInfoModel.setAgency(user.getAgency().getName());
        result.add(feedbackInfoModel);
      }
    }
    return result;
  }

  private User checkUserExistsById(long userId, String email, Long countryCode, String number) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isEmpty()) {
      logger.error(Constants.FEEDBACK_NOT_FOUND, email, countryCode, number);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.FEEDBACK_NOT_FOUND);
    }
    return optionalUser.get();
  }

  private Map<Long, List<FeedbackInfoModel>> constructFeedbackInfoModelMap(
      List<Feedback> feedbacks, ContactNumberModel contactNumber) {
    Map<Long, List<FeedbackInfoModel>> feedbackInfoModelMap = new HashMap<>(feedbacks.size());

    for (Feedback feedback : feedbacks) {
      var feedbackInfoModel = new FeedbackInfoModel();
      feedbackInfoModel.setStatus(feedback.getStatus().getDescription());
      feedbackInfoModel.setText(feedback.getDescription());
      feedbackInfoModel.setContactNumber(contactNumber);

      var userId = feedback.getUserId();
      List<FeedbackInfoModel> feedbackInfoModels =
          !feedbackInfoModelMap.containsKey(userId)
              ? new ArrayList<>()
              : feedbackInfoModelMap.get(userId);

      feedbackInfoModels.add(feedbackInfoModel);
      feedbackInfoModelMap.put(userId, feedbackInfoModels);
    }
    return feedbackInfoModelMap;
  }
}
