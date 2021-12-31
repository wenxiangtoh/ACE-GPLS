package com.service.user.repository;

import com.service.user.repository.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Retrieve the user by name and email
   *
   * @param name name of the user
   * @param email email of the user
   * @return Optional<User> user object
   */
  Optional<User> findUserByNameAndEmail(String name, String email);
}
