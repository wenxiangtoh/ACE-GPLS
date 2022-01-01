package com.service.portal.repository;

import com.service.portal.repository.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Feedback
 *
 * @author Wen Xiang
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}
