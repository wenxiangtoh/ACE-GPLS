package com.service.portal.converter;

import com.service.portal.enumeration.FeedbackStatus;
import com.service.portal.repository.entity.Feedback;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Converter class to convert feedback object
 *
 * @author Wen Xiang
 */
public class FeedbackConverter {

  private FeedbackConverter() {}

  /**
   * Method to convert feedback object
   *
   * @param userId id of user
   * @param description feedback description
   * @param status status of the feedback processing
   * @return Feedback feedbak object
   */
  public static Feedback convertFrom(long userId, String description, FeedbackStatus status) {
    var feedbackUuid = UUID.randomUUID().toString();

    return Feedback.builder()
        .uuid(feedbackUuid)
        .userId(userId)
        .description(description)
        .status(status)
        .createdByUserId(userId)
        .createdAt(LocalDateTime.now())
        .updatedByUserId(userId)
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
