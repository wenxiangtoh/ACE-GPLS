package com.service.portal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.library.service.model.ContactNumberModel;
import com.service.library.service.model.SearchUserModel;
import com.service.library.service.model.UserInfoModel;
import com.service.library.service.model.UserModel;
import com.service.portal.Constants;
import com.service.portal.repository.FeedbackRepository;
import com.service.portal.repository.entity.Feedback;
import com.service.portal.service.impl.FeedbackCommandServiceImpl;
import com.service.portal.service.model.CreateFeedbackModel;
import com.service.portal.service.model.FeedbackStatusResponseModel;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Unit Test for methods within Feedback Command Service
 *
 * @author Wen Xiang
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedbackCommandServiceTest {

  @InjectMocks private FeedbackCommandServiceImpl feedbackCommandService;

  @Mock private UserQueryService userQueryService;
  @Mock private UserCommandService userCommandService;

  @Mock private RestTemplate restTemplate;
  @Mock private ObjectMapper mapper;

  @Mock private FeedbackRepository feedbackRepository;

  @Before
  public void setupMock() {
    MockitoAnnotations.openMocks(this);
    ReflectionTestUtils.setField(
        feedbackCommandService,
        "feedbackStatusUrl",
        "http://processfeedback.atwebpages.com/submit.php?feedback=");
  }

  @Test
  public void createFeedback_noUserRecords() {
    var name = "name";
    var email = "123@gmail.com";

    ContactNumberModel contactNumber = new ContactNumberModel();
    contactNumber.setCountryCode(65L);
    contactNumber.setNumber("12345678");

    CreateFeedbackModel createFeedbackModel =
        CreateFeedbackModel.builder()
            .name(name)
            .email(email)
            .contactNumber(contactNumber)
            .agencyUuid(UUID.randomUUID().toString())
            .text("Test Feedback Text")
            .build();

    UserInfoModel userInfoModel = new UserInfoModel();
    userInfoModel.setId(0L);

    UserInfoModel userInfoModel1 = new UserInfoModel();
    userInfoModel1.setId(1L);

    Mockito.when(userQueryService.findUserByNameAndEmail(Mockito.any(SearchUserModel.class)))
        .thenReturn(userInfoModel);
    Mockito.doNothing().when(userCommandService).createUser(Mockito.any(UserModel.class));
    Mockito.when(userQueryService.findUserByNameAndEmail(Mockito.any(SearchUserModel.class)))
        .thenReturn(userInfoModel1);

    feedbackCommandService.createFeedback(createFeedbackModel);

    Mockito.verify(feedbackRepository, Mockito.times(1)).save(Mockito.any(Feedback.class));
  }

  @Test
  public void createFeedback_existingserRecords() {
    var name = "name";
    var email = "123@gmail.com";

    ContactNumberModel contactNumber = new ContactNumberModel();
    contactNumber.setCountryCode(65L);
    contactNumber.setNumber("12345678");

    CreateFeedbackModel createFeedbackModel =
        CreateFeedbackModel.builder()
            .name(name)
            .email(email)
            .contactNumber(contactNumber)
            .agencyUuid(UUID.randomUUID().toString())
            .text("Test Feedback Text")
            .build();

    UserInfoModel userInfoModel = new UserInfoModel();
    userInfoModel.setId(1L);

    Mockito.when(userQueryService.findUserByNameAndEmail(Mockito.any(SearchUserModel.class)))
        .thenReturn(userInfoModel);

    feedbackCommandService.createFeedback(createFeedbackModel);

    Mockito.verify(feedbackRepository, Mockito.times(1)).save(Mockito.any(Feedback.class));
  }

  @Test
  public void processFeedbackStatus_feedbackAccepted() throws JsonProcessingException {
    String mockResult = "{\"sentiment\":postive, \"time\":160000000, \"feedback\":text}";
    FeedbackStatusResponseModel feedbackStatusResponseModel = new FeedbackStatusResponseModel();
    feedbackStatusResponseModel.setSentiment(Constants.POSITIVE);

    Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
        .thenReturn(ResponseEntity.ok(mockResult));
    Mockito.when(mapper.readValue(mockResult, FeedbackStatusResponseModel.class))
        .thenReturn(feedbackStatusResponseModel);

    feedbackCommandService.processFeedbackStatus();

    Mockito.verify(feedbackRepository, Mockito.times(1)).saveAll(Mockito.anyList());
  }

  @Test
  public void processFeedbackStatus_feedbackRejected() throws JsonProcessingException {
    String mockResult = "{\"sentiment\":negative, \"time\":160000000, \"feedback\":text}";
    FeedbackStatusResponseModel feedbackStatusResponseModel = new FeedbackStatusResponseModel();
    feedbackStatusResponseModel.setSentiment(Constants.NEGATIVE);

    Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
        .thenReturn(ResponseEntity.ok(mockResult));
    Mockito.when(mapper.readValue(mockResult, FeedbackStatusResponseModel.class))
        .thenReturn(feedbackStatusResponseModel);

    feedbackCommandService.processFeedbackStatus();

    Mockito.verify(feedbackRepository, Mockito.times(1)).saveAll(Mockito.anyList());
  }
}
