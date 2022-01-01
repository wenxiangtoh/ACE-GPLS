package com.service.portal.service;

import com.service.portal.service.model.FeedbackInfoModel;
import com.service.portal.service.model.FeedbackInfoRequestModel;
import java.util.List;

/**
 * Service Interface for feedback query
 *
 * @author Wen Xiang
 */
public interface FeedbackQueryService {

  /**
   * Method to retrieve all feedback information pertaining to the email and contact number
   *
   * @param feedbackInfoRequestModel request model for feedback info
   * @return List<FeedbackInfoModel> list of model containing feedback information
   */
  List<FeedbackInfoModel> findFeedbacksByEmailAndContactNumber(
      FeedbackInfoRequestModel feedbackInfoRequestModel);
}
