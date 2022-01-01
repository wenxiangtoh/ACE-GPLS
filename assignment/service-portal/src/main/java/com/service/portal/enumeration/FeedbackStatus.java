package com.service.portal.enumeration;

/**
 * Enum of status used for feedback processing
 *
 * @author Wen Xiang
 */
public enum FeedbackStatus {
  PROCESSING("PROCESSING", "Processing"),
  ACCEPTED("ACCEPTED", "Accepted"),
  REJECTED("REJECTED", "Rejected");

  String value;
  String description;

  FeedbackStatus(String value, String description) {
    this.value = value;
    this.description = description;
  }

  public String getValue() {
    return this.value;
  }

  public String getDescription() {
    return this.description;
  }
}
