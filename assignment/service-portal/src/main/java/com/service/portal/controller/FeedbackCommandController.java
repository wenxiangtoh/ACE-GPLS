package com.service.portal.controller;

import com.service.portal.service.FeedbackCommandService;
import com.service.portal.service.model.CreateFeedbackModel;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class FeedbackCommandController {

  @Autowired private FeedbackCommandService feedbackCommandService;

  @PostMapping("/feedbacks")
  public void createFeedback(@RequestBody @Valid CreateFeedbackModel createFeedbackModel) {
    feedbackCommandService.createFeedback(createFeedbackModel);
  }
}
