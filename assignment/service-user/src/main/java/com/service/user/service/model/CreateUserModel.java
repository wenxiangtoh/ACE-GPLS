package com.service.user.service.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Create User Model to create user
 *
 * @author Wen Xiang
 */
public class CreateUserModel {

  @NotBlank private String name;

  @NotBlank @Email private String email;

  @Valid private ContactNumberModel contactNumber;

  @NotBlank private String agencyUuid;
}
