package com.service.portal.service.model;

/**
 * Feedback Status Response Model
 *
 * @author Wen Xiang
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Create Feedback Model
 *
 * @author Wen Xiang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackStatusResponseModel {

  private String sentiment;

  private String time;

  private String feedback;
}
