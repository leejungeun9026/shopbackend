package com.jungeun.shopbackend.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

@Log4j2
public class SecurityUtils {
  public static final String ROLE_PREFIX = "ROLE_";
  public static final String AUTH_HEADER = "Authorization";
  public static final String AUTH_TOKEN_TYPE = "Bearer";
  public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";

  public static SimpleGrantedAuthority convertToAuthority(String role) {
    String formatRole = role.startsWith(ROLE_PREFIX) ? role: ROLE_PREFIX + role;
    return new SimpleGrantedAuthority(formatRole);
  }

  public static String extraAuthTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTH_HEADER);
    if(StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
      log.info(bearerToken.substring(7));
      return bearerToken.substring(7);
    }
    return null;
  }
}
