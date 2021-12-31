package com.service.user.repository.entity;

import com.service.library.repository.entity.Auditable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * User Entity.
 *
 * @author WenXiang
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class User extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 36, unique = true)
  private String uuid;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, length = 321)
  private String email;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "contact_number_id")
  private ContactNumber contactNumber;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "agency_id")
  private Agency agency;

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(id); // NOSONAR
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object user) {
    if (this == user) {
      return true;
    }
    if (null == user) {
      return false;
    }
    if (getClass() != user.getClass()) {
      return false;
    }
    User other = (User) user;
    return Objects.equals(id, other.id);
  }
}
