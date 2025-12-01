package com.jungeun.shopbackend.security;

import com.jungeun.shopbackend.domain.Role;
import com.jungeun.shopbackend.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
// 모든 요청 url이 스프링 시큐리티의 제어를 받도록 설정
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final CustomUserDetailsService userDetailsService;
  private final CorsConfigurationSource corsConfigurationSource;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // jwt사용 시 작성해야 함 (session만 사용할 경우 필요 없음)
  // AuthenticationManager : 시큐리티 인증 절차를 수행함
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }


  // 인가 설정, 로그인 및 로그아웃 설정
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        // csrf 역할 -> 크로스 플랫폼 사용 시 탈취 및 위조 방지
        // disable처리를 해야 rest api 요청을 주고 받을 수 있음
        .csrf(AbstractHttpConfigurer::disable)
        // cors -> 다른 출처에서 오는 요청을 어느정도 허용할 지 설정
        // corsConfigurationSource <- 함수로 만들어서 적용함
        .cors(httpSecurityCorsConfigurer -> corsConfigurationSource())
        // session정책 설정
        // web은 세션 상태 저장하는게 기본이지만 jwt이용 시 필요없으므로(STATELESS) 설정함
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/api/authentication/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/product/**").permitAll()
            .requestMatchers("/api/product/**").hasRole(Role.ADMIN.name())
            .anyRequest().authenticated())
        .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
  }


  // cross-origin resource sharing
  // 웹브라우저에서 다른 출처의 리소스에 접근할수 있도록 허용하는 보안 메커니즘
  // 리액트, 안드로이드 등 다른곳에서 요청할때 접근을 허용하는 설정
  @Bean
  CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration configuration = new CorsConfiguration();
    // 어느 포트에서 오는걸 접근 허용할 지 설정
    configuration.setAllowedOrigins(Arrays.asList("*"));
    // 어떤 타입의 요청을 접근 허용할 지 설정
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    // 어떤 헤더 정보를 접근 허용할 지 설정
    configuration.setAllowedHeaders(Arrays.asList("*"));
    // 어떤 url로 시작하는걸 접근 허용할 지 설정
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public JwtAuthorizationFilter jwtAuthorizationFilter() {
    return new JwtAuthorizationFilter();
  }
}
