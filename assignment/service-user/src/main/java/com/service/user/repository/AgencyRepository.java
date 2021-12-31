package com.service.user.repository;

import com.service.user.repository.entity.Agency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

  /**
   * Retrieve agency object by uuid
   *
   * @param uuid Universally Unique Identifier of Agency
   * @return Optional<Agency> optional agency object
   */
  Optional<Agency> findAgencyByUuid(String uuid);
}
