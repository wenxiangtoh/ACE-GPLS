package com.service.portal.converter;

import com.service.library.service.model.ContactNumberModel;
import com.service.library.service.model.UserModel;

/**
 * Converter class to convert User Model object
 *
 * @author Wen Xiang
 */
public class UserModelConverter {

  private UserModelConverter() {}

  /**
   * Method to convert User Model object
   *
   * @param name name of the user
   * @param email email of the user
   * @param contactNumber contact number object of the user
   * @param agencyUuid Universally Unique Identifier of Agency
   * @return UserModel user model object
   */
  public static UserModel convertFrom(
      String name, String email, ContactNumberModel contactNumber, String agencyUuid) {
    return com.service.library.service.model.UserModel.builder()
        .name(name)
        .email(email)
        .contactNumber(contactNumber)
        .agencyUuid(agencyUuid)
        .build();
  }
}
