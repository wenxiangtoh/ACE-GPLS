package com.service.user.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * Contact Number Model containing contact information
 *
 * @author Wen Xiang
 */
public class ContactNumberModel {
  @Positive private Long countryCode;

  @NotBlank private String number;
}
