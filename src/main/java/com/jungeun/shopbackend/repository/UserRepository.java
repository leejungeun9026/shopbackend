package com.jungeun.shopbackend.repository;

import com.jungeun.shopbackend.domain.Role;
import com.jungeun.shopbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  @Modifying
  @Query("update User set role = :role where username = :username")
  void updateUserRole(@Param("username") String username, @Param("role") Role role);
}
