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
 * Contact Number Entity.
 *
 * @author WenXiang
 */
@Entity
@Table(name = "contact_numbers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactNumber extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column private Long countryCode;

  @Column private String number;

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(id); // NOSONAR
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object contactNumber) {
    if (this == contactNumber) {
      return true;
    }
    if (null == contactNumber) {
      return false;
    }
    if (getClass() != contactNumber.getClass()) {
      return false;
    }
    ContactNumber other = (ContactNumber) contactNumber;
    return Objects.equals(id, other.id);
  }
}
