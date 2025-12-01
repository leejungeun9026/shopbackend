package com.jungeun.shopbackend.service;

import com.jungeun.shopbackend.domain.User;

public interface AuthenticationService {
  public User signInAndReturnJWT(User signInRequest);
}
