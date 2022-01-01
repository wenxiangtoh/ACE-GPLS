package com.service.portal.service.model;

import com.service.library.service.model.AbstractUserModel;
import com.service.portal.enumeration.FeedbackStatus;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Feedback Info Model
 *
 * @author Wen Xiang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class FeedbackInfoModel extends AbstractUserModel {

  @NotBlank private String agency;

  @NotBlank private FeedbackStatus status;
}
