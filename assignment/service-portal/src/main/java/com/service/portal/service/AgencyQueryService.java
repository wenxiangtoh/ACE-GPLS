package com.service.portal.service;

import com.service.library.service.model.DropdownListModel;
import java.util.List;

/**
 * Service Interface for agency query
 *
 * @author Wen Xiang
 */
public interface AgencyQueryService {

  /**
   * Method to retrieve all agencies
   *
   * @return List<DropdownListModel> list of agencies drop down list model
   */
  List<DropdownListModel> findAllAgencies();
}
