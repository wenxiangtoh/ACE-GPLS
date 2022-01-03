package com.service.portal.controller;

import com.service.portal.service.FeedbackQueryService;
import com.service.portal.service.model.FeedbackInfoModel;
import com.service.portal.service.model.FeedbackInfoRequestModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feedback Query Controller to retrieve feedback records
 *
 * @author Wen Xiang
 */
@RestController
@Validated
public class FeedbackQueryController {

  @Autowired private FeedbackQueryService feedbackQueryService;

  /**
   * API to retrieve all feedback information pertaining to the email and contact number
   *
   * @param feedbackInfoRequestModel request model for feedback info
   * @return List<FeedbackInfoModel> list of model containing feedback information
   */
  @PostMapping("/feedbacks/search")
  public ResponseEntity<List<FeedbackInfoModel>> findFeedbackByEmailAndContactNumber(
      @RequestBody FeedbackInfoRequestModel feedbackInfoRequestModel) {
    return ResponseEntity.ok(
        feedbackQueryService.findFeedbacksByEmailAndContactNumber(feedbackInfoRequestModel));
  }
}
