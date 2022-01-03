package com.service.portal.service;

import com.service.library.service.model.DropdownListModel;
import com.service.portal.repository.AgencyRepository;
import com.service.portal.repository.entity.Agency;
import com.service.portal.service.impl.AgencyQueryServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit Test for methods within Agency Query Service
 *
 * @author Wen Xiang
 */
@RunWith(MockitoJUnitRunner.class)
public class AgencyQueryServiceTest {

  @InjectMocks private AgencyQueryServiceImpl agencyQueryService;

  @Mock private AgencyRepository agencyRepository;

  @Before
  public void setupMock() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void findAllAgencies() {
    Agency agency = new Agency();
    agency.setUuid(UUID.randomUUID().toString());
    agency.setName("Agency 1");

    Agency agency1 = new Agency();
    agency1.setUuid(UUID.randomUUID().toString());
    agency1.setName("Agency 2");
    List<Agency> agencies = Arrays.asList(agency, agency1);

    Mockito.when(agencyRepository.findAll()).thenReturn(agencies);

    List<DropdownListModel> result = agencyQueryService.findAllAgencies();

    Assert.assertEquals(2, result.size());
    Assert.assertEquals("Agency 2", result.get(1).getDescription());
  }
}
