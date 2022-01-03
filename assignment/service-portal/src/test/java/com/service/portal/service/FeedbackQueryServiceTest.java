package com.service.portal.service;

import com.service.library.service.model.ContactNumberModel;
import com.service.portal.Constants;
import com.service.portal.enumeration.FeedbackStatus;
import com.service.portal.repository.FeedbackRepository;
import com.service.portal.repository.UserRepository;
import com.service.portal.repository.entity.Agency;
import com.service.portal.repository.entity.Feedback;
import com.service.portal.repository.entity.User;
import com.service.portal.service.impl.FeedbackQueryServiceImpl;
import com.service.portal.service.model.FeedbackInfoModel;
import com.service.portal.service.model.FeedbackInfoRequestModel;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Unit Test for methods within Feedback Qery Service
 *
 * @author Wen Xiang
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedbackQueryServiceTest {

  @InjectMocks private FeedbackQueryServiceImpl feedbackQueryService;

  @Mock private FeedbackRepository feedbackRepository;
  @Mock private UserRepository userRepository;

  @Test
  public void findFeedbacksByEmailAndContactNumber_processing() {
    var email = "123@gmail.com";

    ContactNumberModel contactNumber = new ContactNumberModel();
    contactNumber.setCountryCode(65L);
    contactNumber.setNumber("12345678");

    FeedbackInfoRequestModel feedbackInfoRequestModel = new FeedbackInfoRequestModel();
    feedbackInfoRequestModel.setEmail(email);
    feedbackInfoRequestModel.setContactNumber(contactNumber);

    Feedback feedback = new Feedback();
    feedback.setUserId(1L);
    feedback.setStatus(FeedbackStatus.PROCESSING);
    feedback.setDescription("description");
    List<Feedback> feedbacks = Arrays.asList(feedback);

    Agency agency = new Agency();
    agency.setName("Agency 1");

    User user = new User();
    user.setName("name");
    user.setEmail(email);
    user.setAgency(agency);

    Mockito.when(
            feedbackRepository.findFeedbacksByEmailAndContactNumber(
                Mockito.anyString(), Mockito.anyLong(), Mockito.anyString()))
        .thenReturn(feedbacks);
    Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));

    List<FeedbackInfoModel> result =
        feedbackQueryService.findFeedbacksByEmailAndContactNumber(feedbackInfoRequestModel);

    Assert.assertEquals(1, result.size());
    Assert.assertEquals(FeedbackStatus.PROCESSING.getDescription(), result.get(0).getStatus());
  }

  @Test
  public void findFeedbacksByEmailAndContactNumber_accepted() {
    var email = "123@gmail.com";

    ContactNumberModel contactNumber = new ContactNumberModel();
    contactNumber.setCountryCode(65L);
    contactNumber.setNumber("12345678");

    FeedbackInfoRequestModel feedbackInfoRequestModel = new FeedbackInfoRequestModel();
    feedbackInfoRequestModel.setEmail(email);
    feedbackInfoRequestModel.setContactNumber(contactNumber);

    Feedback feedback = new Feedback();
    feedback.setUserId(1L);
    feedback.setStatus(FeedbackStatus.ACCEPTED);
    feedback.setDescription("description");
    List<Feedback> feedbacks = List.of(feedback);

    Agency agency = new Agency();
    agency.setName("Agency 1");

    User user = new User();
    user.setName("name");
    user.setEmail(email);
    user.setAgency(agency);

    Mockito.when(
            feedbackRepository.findFeedbacksByEmailAndContactNumber(
                Mockito.anyString(), Mockito.anyLong(), Mockito.anyString()))
        .thenReturn(feedbacks);
    Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));

    List<FeedbackInfoModel> result =
        feedbackQueryService.findFeedbacksByEmailAndContactNumber(feedbackInfoRequestModel);

    Assert.assertEquals(1, result.size());
    Assert.assertEquals(FeedbackStatus.ACCEPTED.getDescription(), result.get(0).getStatus());
  }

  @Test
  public void findFeedbacksByEmailAndContactNumber_noFeedback() {
    var email = "123@gmail.com";

    ContactNumberModel contactNumber = new ContactNumberModel();
    contactNumber.setCountryCode(65L);
    contactNumber.setNumber("12345678");

    FeedbackInfoRequestModel feedbackInfoRequestModel = new FeedbackInfoRequestModel();
    feedbackInfoRequestModel.setEmail(email);
    feedbackInfoRequestModel.setContactNumber(contactNumber);

    Feedback feedback = new Feedback();
    feedback.setUserId(1L);
    feedback.setStatus(FeedbackStatus.PROCESSING);
    feedback.setDescription("description");
    List<Feedback> feedbacks = List.of(feedback);

    Mockito.when(
            feedbackRepository.findFeedbacksByEmailAndContactNumber(
                Mockito.anyString(), Mockito.anyLong(), Mockito.anyString()))
        .thenReturn(feedbacks);
    Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    var exception =
        Assert.assertThrows(
            ResponseStatusException.class,
            () ->
                feedbackQueryService.findFeedbacksByEmailAndContactNumber(
                    feedbackInfoRequestModel));
    Assert.assertEquals(Constants.FEEDBACK_NOT_FOUND, exception.getReason());
    Assert.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
  }
}
