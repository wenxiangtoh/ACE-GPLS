package com.service.portal.repository.entity;

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
 * Agency Entity.
 *
 * @author WenXiang
 */
@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agency extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 36, unique = true)
  private String uuid;

  @Column(nullable = false)
  private String name;

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(id); // NOSONAR
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object agency) {
    if (this == agency) {
      return true;
    }
    if (null == agency) {
      return false;
    }
    if (getClass() != agency.getClass()) {
      return false;
    }
    Agency other = (Agency) agency;
    return Objects.equals(id, other.id);
  }
}
