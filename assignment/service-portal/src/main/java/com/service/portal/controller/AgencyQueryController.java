package com.service.portal.controller;

import com.service.library.service.model.DropdownListModel;
import com.service.portal.service.AgencyQueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feedback Query Controller to retrieve agency records
 *
 * @author Wen Xiang
 */
@RestController
@Validated
public class AgencyQueryController {

  @Autowired private AgencyQueryService agencyQueryService;

  @GetMapping("/agencies")
  public ResponseEntity<List<DropdownListModel>> findAllAgencies() {
    return ResponseEntity.ok(agencyQueryService.findAllAgencies());
  }
}
