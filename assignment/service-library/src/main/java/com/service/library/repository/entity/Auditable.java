package com.service.library.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * An abstract for auditable entity.
 *
 * @author WenXiang
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
public abstract class Auditable implements Serializable {

  private static final long serialVersionUID = 1L;

  /** creation time of contactNumber */
  @Column(nullable = false, columnDefinition = "TIMESTAMP")
  @CreatedDate
  private LocalDateTime createdAt;

  /** contactNumber's creating user id */
  @Column(nullable = false)
  @CreatedBy
  private long createdByUserId;

  /** update time of contactNumber */
  @Column(nullable = false, columnDefinition = "TIMESTAMP")
  @LastModifiedDate
  private LocalDateTime updatedAt;

  /** contactNumber's updating user id */
  @Column(nullable = false)
  @LastModifiedBy
  private long updatedByUserId;
}
