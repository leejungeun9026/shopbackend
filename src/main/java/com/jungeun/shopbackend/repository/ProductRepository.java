package com.jungeun.shopbackend.repository;

import com.jungeun.shopbackend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
