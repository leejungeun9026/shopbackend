package com.jungeun.shopbackend.repository;

import com.jungeun.shopbackend.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
  @Query("select pur from Purchase pur where pur.user.username = :username")
  List<Purchase> findAllByPurchaseUser(String username);
}
