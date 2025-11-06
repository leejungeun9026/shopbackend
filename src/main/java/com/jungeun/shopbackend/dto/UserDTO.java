package com.jungeun.shopbackend.dto;

import com.jungeun.shopbackend.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
  private Long id;
  private String username;
  private String password;
  private String name;
  private LocalDate createTime;
  private Role role;
  private String token;
}
