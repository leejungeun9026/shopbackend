package com.jungeun.shopbackend.service;

import com.jungeun.shopbackend.domain.Product;
import com.jungeun.shopbackend.dto.ProductDTO;

import java.util.List;

public interface ProductService {
  ProductDTO saveProduct(ProductDTO productDTO);
  List<ProductDTO> getProductList();
  ProductDTO getProduct(Long id);
  ProductDTO getProductByName(String name);
  void deleteProduct(Long id);
  void updateProduct(ProductDTO productDTO);

  default Product dtoToEntity(ProductDTO productDTO) {
    return Product.builder()
        .id(productDTO.getId())
        .name(productDTO.getName())
        .price(productDTO.getPrice())
        .description(productDTO.getDescription())
        .build();
  }

  default ProductDTO entityToDto(Product product) {
    return ProductDTO.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .description(product.getDescription())
        .createTime(product.getCreateTime())
        .build();
  }
}
