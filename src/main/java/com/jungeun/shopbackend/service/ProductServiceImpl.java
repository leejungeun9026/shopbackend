package com.jungeun.shopbackend.service;

import com.jungeun.shopbackend.domain.Product;
import com.jungeun.shopbackend.dto.ProductDTO;
import com.jungeun.shopbackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public ProductDTO saveProduct(ProductDTO productDTO) {
    Product product = dtoToEntity(productDTO);
    product = productRepository.save(product);
    return entityToDto(product);
  }

  @Override
  public List<ProductDTO> getProductList() {
    List<Product> productList = productRepository.findAll();
    List<ProductDTO> productDTOList = new ArrayList<>();
    for (Product product : productList) {
      productDTOList.add(entityToDto(product));
    }
    return productDTOList;
  }

  @Override
  public ProductDTO getProduct(Long id) {
    Product product = productRepository.findById(id).orElse(null);
    return entityToDto(product);
  }

  @Override
  public ProductDTO getProductByName(String name) {
    return null;
  }

  @Override
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public void updateProduct(ProductDTO productDTO) {
    Product product = productRepository.findById(productDTO.getId()).orElse(null);
    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setPrice(productDTO.getPrice());
    productRepository.save(product);
  }
}
