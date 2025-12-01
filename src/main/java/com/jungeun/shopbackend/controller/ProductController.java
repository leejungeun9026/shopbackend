package com.jungeun.shopbackend.controller;

import com.jungeun.shopbackend.domain.Product;
import com.jungeun.shopbackend.dto.ProductDTO;
import com.jungeun.shopbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Log4j2
public class ProductController {
  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Object> saveProduct(@RequestBody ProductDTO productDTO) {
    return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    System.out.println("getList");
    return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<Object> modifyProduct(@RequestBody ProductDTO productDTO) {
    productService.updateProduct(productDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
