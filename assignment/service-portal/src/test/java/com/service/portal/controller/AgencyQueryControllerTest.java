package com.service.portal.controller;

import com.service.library.service.model.DropdownListModel;
import com.service.portal.service.AgencyQueryService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit Test for methods within Agency Query Controller
 *
 * @author Wen Xiang
 */
@RunWith(MockitoJUnitRunner.class)
public class AgencyQueryControllerTest {

  @InjectMocks AgencyQueryController agencyQueryController;

  @Mock private AgencyQueryService agencyQueryService;

  @Test
  public void findAllAgencies() {
    DropdownListModel dropdownListModel = new DropdownListModel();
    List<DropdownListModel> dropdownListModelList = List.of(dropdownListModel);

    Mockito.when(agencyQueryService.findAllAgencies()).thenReturn(dropdownListModelList);

    var response = agencyQueryController.findAllAgencies();

    Assert.assertEquals(200, response.getStatusCode().value());
    Assert.assertEquals(dropdownListModelList, response.getBody());
  }
}
