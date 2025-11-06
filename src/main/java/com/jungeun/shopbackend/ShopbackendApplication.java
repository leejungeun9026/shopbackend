package com.jungeun.shopbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShopbackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShopbackendApplication.class, args);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // 여기 Bean생성할때는 @Configuration안써도 됨 
    return new BCryptPasswordEncoder();
  }
}
