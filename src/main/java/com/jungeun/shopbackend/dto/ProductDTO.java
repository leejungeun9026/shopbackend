package com.jungeun.shopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
  private Long id;
  private String name;
  private String description;
  private Integer price;
  private LocalDateTime createTime;
}
