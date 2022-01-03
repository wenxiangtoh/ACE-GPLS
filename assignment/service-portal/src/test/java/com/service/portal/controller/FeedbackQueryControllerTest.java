package com.service.portal.controller;

import com.service.portal.service.FeedbackQueryService;
import com.service.portal.service.model.FeedbackInfoModel;
import com.service.portal.service.model.FeedbackInfoRequestModel;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit Test for methods within Feedback Query Controller
 *
 * @author Wen Xiang
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedbackQueryControllerTest {

  @InjectMocks FeedbackQueryController feedbackQueryController;

  @Mock private FeedbackQueryService feedbackQueryService;

  @Test
  public void findFeedbackByEmailAndContactNumber() {
    FeedbackInfoModel feedbackInfoModel = new FeedbackInfoModel();
    List<FeedbackInfoModel> feedbackInfoModels = List.of(feedbackInfoModel);

    FeedbackInfoRequestModel feedbackInfoRequestModel = new FeedbackInfoRequestModel();

    Mockito.when(
            feedbackQueryService.findFeedbacksByEmailAndContactNumber(feedbackInfoRequestModel))
        .thenReturn(feedbackInfoModels);

    var response =
        feedbackQueryController.findFeedbackByEmailAndContactNumber(feedbackInfoRequestModel);

    Assert.assertEquals(200, response.getStatusCode().value());
    Assert.assertEquals(feedbackInfoModels, response.getBody());
  }
}
