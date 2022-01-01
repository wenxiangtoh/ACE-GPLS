package com.service.portal.service.impl;

import com.service.library.service.model.DropdownListModel;
import com.service.portal.repository.AgencyRepository;
import com.service.portal.repository.entity.Agency;
import com.service.portal.service.AgencyQueryService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for agency query
 *
 * @author Wen Xiang
 */
@Service
public class AgencyQueryServiceImpl implements AgencyQueryService {

  @Autowired private AgencyRepository agencyRepository;

  /** {@inheritDoc} */
  @Override
  public List<DropdownListModel> findAllAgencies() {
    List<DropdownListModel> agenciesDropdownList = new ArrayList<>();

    List<Agency> agencies = agencyRepository.findAll();
    for (Agency agency : agencies) {
      var code = agency.getUuid();
      var description = agency.getName();

      agenciesDropdownList.add(new DropdownListModel(code, description));
    }
    agenciesDropdownList.sort(Comparator.comparing(DropdownListModel::getDescription));

    return agenciesDropdownList;
  }
}
