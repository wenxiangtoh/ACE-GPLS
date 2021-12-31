package com.service.user.service.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Create User Model to create user
 *
 * @author Wen Xiang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreateUserModel {

  @NotBlank private String name;

  @NotBlank @Email private String email;

  @Valid private ContactNumberModel contactNumber;

  @NotBlank private String agencyUuid;
}
