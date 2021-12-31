package com.service.portal.service;

import com.service.portal.service.model.CreateFeedbackModel;

public interface FeedbackCommandService {

  /**
   * Create user's feedback
   *
   * @param createFeedbackModel create feedback model
   */
  void createFeedback(CreateFeedbackModel createFeedbackModel);
}
