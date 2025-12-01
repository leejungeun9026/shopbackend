package com.jungeun.shopbackend.controller;

import com.jungeun.shopbackend.dto.PurchaseDTO;
import com.jungeun.shopbackend.security.UserPrincipal;
import com.jungeun.shopbackend.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@Log4j2
public class PurchaseController {
  private final PurchaseService purchaseService;

  @PostMapping
  public ResponseEntity<Object> savePurchase(@RequestBody PurchaseDTO purchaseDTO) {
    log.info("savePurchase");
    return new ResponseEntity<>(purchaseService.savePurchase(purchaseDTO), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Object> getAllPurchaseOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
    System.out.println("getAllPurchase.........." + userPrincipal.getUser());
    return ResponseEntity.ok(purchaseService.getPurchasesUser(userPrincipal.getUsername()));
  }
}
