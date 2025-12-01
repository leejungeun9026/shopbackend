package com.jungeun.shopbackend.controller;

import com.jungeun.shopbackend.domain.User;
import com.jungeun.shopbackend.service.AuthenticationService;
import com.jungeun.shopbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;
  private final UserService userService;

  @PostMapping("/sign-up")
  public ResponseEntity<Object> signUp(@RequestBody User user){
    if(userService.findByUsername(user.getUsername())!=null){
      return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 인증 실패
    }
    return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<Object> signIn(@RequestBody User user){
    return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
  }
}
