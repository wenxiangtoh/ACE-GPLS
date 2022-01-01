package com.service.portal.repository;

import com.service.portal.repository.entity.Feedback;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Feedback
 *
 * @author Wen Xiang
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

  /**
   * Retrieve the feedback by email and contact number
   *
   * @param email email of user
   * @param countryCode country code of a contact number
   * @param number number of a contact number
   * @return List<Feedback> list of feedback object
   */
  @Query(
      "FROM Feedback fb, User u, ContactNumber cn WHERE fb.userId = u.id AND u.contactNumber.id = cn.id AND u.email = ?1 AND cn.countryCode = ?2 AND cn.number = ?3")
  List<Feedback> findFeedbacksByEmailAndContactNumber(
      String email, long countryCode, String number);
}
