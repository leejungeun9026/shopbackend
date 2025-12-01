package com.jungeun.shopbackend.controller;

import com.jungeun.shopbackend.domain.Role;
import com.jungeun.shopbackend.security.UserPrincipal;
import com.jungeun.shopbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {
  private final UserService userService;

  @PutMapping("/change/{role}")
  public ResponseEntity<Object> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                           @PathVariable Role role) {
    userService.changeRole(userPrincipal.getUsername(), role);
    return ResponseEntity.ok(true);
  }
}
