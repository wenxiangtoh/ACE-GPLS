package com.service.library.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contact Number Model containing contact information
 *
 * @author Wen Xiang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactNumberModel {
  @Positive private Long countryCode;

  @NotBlank
  @Size(min = 1, max = 255)
  private String number;
}
