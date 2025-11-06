package com.jungeun.shopbackend.service;

import com.jungeun.shopbackend.domain.Role;
import com.jungeun.shopbackend.domain.User;

import java.util.List;

public interface UserService {
  User saveUser(User user);
  User findByUsername(String username);
  void changeRole(Role nowRole, String username);
  void deleteUser(Long id);
  List<User> findAllUsers();
}
