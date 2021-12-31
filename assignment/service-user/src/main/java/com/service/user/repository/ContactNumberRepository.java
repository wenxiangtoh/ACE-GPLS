package com.service.user.repository;

import com.service.user.repository.entity.ContactNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactNumberRepository extends JpaRepository<ContactNumber, Long> {}
