package com.service.feedback.repository.entity;

import com.service.library.repository.entity.Auditable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Feedback Entity.
 *
 * @author WenXiang
 */
@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 36, unique = true)
  private String uuid;

  @Column private Long userId;

  @Column(nullable = false, length = 2000)
  private String description;

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(id); // NOSONAR
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object feedback) {
    if (this == feedback) {
      return true;
    }
    if (null == feedback) {
      return false;
    }
    if (getClass() != feedback.getClass()) {
      return false;
    }
    Feedback other = (Feedback) feedback;
    return Objects.equals(id, other.id);
  }
}
