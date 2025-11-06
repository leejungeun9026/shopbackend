package com.jungeun.shopbackend.service;

import com.jungeun.shopbackend.domain.Product;
import com.jungeun.shopbackend.domain.Purchase;
import com.jungeun.shopbackend.domain.User;
import com.jungeun.shopbackend.dto.PurchaseDTO;
import com.jungeun.shopbackend.repository.ProductRepository;
import com.jungeun.shopbackend.repository.PurchaseRepository;
import com.jungeun.shopbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
  private final PurchaseRepository purchaseRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  @Override
  public PurchaseDTO savePurchase(PurchaseDTO purchaseDTO) {
    Purchase purchase = dtoToEntity(purchaseDTO);
    User user = userRepository.findByUsername(purchaseDTO.getUsername());
    Product product = productRepository.findById(purchaseDTO.getProductId()).orElse(null);
    purchase.setUser(user);
    purchase.setProduct(product);
    Purchase purchase1 = purchaseRepository.save(purchase);
    return entityToDto(purchase1);
  }

  @Override
  public List<PurchaseDTO> getPurchasesUser(String username) {
    List<Purchase> purchases = purchaseRepository.findAllByPurchaseUser(username);
    List<PurchaseDTO> purchaseDTOList = new ArrayList<>();
    for (Purchase purchase : purchases) {
      purchaseDTOList.add(entityToDto(purchase));
    }
    return purchaseDTOList;
  }

  @Override
  public List<PurchaseDTO> getPurchasesAll() {
    List<Purchase> purchases = purchaseRepository.findAll();
    List<PurchaseDTO> purchaseDTOList = new ArrayList<>();
    for (Purchase purchase : purchases) {
      purchaseDTOList.add(entityToDto(purchase));
    }
    return purchaseDTOList;
  }

  @Override
  public void deletePurchase(Long id) {
    Purchase purchase = purchaseRepository.findById(id).orElse(null);
    purchaseRepository.deleteById(id);
  }
}
