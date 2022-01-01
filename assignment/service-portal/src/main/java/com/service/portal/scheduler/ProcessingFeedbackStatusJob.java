package com.service.portal.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.portal.service.FeedbackCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessingFeedbackStatusJob {

  @Autowired private FeedbackCommandService feedbackCommandService;

  @Scheduled(cron = "${feedback.status.cron-expression}")
  public void executeJob() throws JsonProcessingException {
    feedbackCommandService.processFeedbackStatus();
  }
}
