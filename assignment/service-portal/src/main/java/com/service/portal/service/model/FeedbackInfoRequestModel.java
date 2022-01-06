package com.service.portal.service.model;

import com.service.library.service.model.ContactNumberModel;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Feedback Info Request Model
 *
 * @author Wen Xiang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackInfoRequestModel {

  @NotBlank
  @Email
  @Size(max = 321)
  private String email;

  @Valid private ContactNumberModel contactNumber;
}
