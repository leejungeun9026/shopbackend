package com.jungeun.shopbackend.service;

import com.jungeun.shopbackend.domain.Purchase;
import com.jungeun.shopbackend.dto.PurchaseDTO;

import java.util.List;

public interface PurchaseService {
  PurchaseDTO savePurchase(PurchaseDTO purchaseDTO);
  List<PurchaseDTO> getPurchasesUser(String username);
  List<PurchaseDTO> getPurchasesAll();
  void deletePurchase(Long id);

  default Purchase dtoToEntity(PurchaseDTO purchaseDTO) {
    return Purchase.builder()
        .id(purchaseDTO.getId())
        .quantity(purchaseDTO.getQuantity())
        .build();
  }

  default PurchaseDTO entityToDto(Purchase purchase) {
    return PurchaseDTO.builder()
        .id(purchase.getId())
        .quantity(purchase.getQuantity())
        .productId(purchase.getProduct().getId())
        .productName(purchase.getProduct().getName())
        .productPrice(purchase.getProduct().getPrice())
        .purchaseTime(purchase.getPurchaseTime())
        .username(purchase.getUser().getUsername())
        .build();
  }
}
