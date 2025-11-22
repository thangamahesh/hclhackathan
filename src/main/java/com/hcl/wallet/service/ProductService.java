package com.hcl.wallet.service;

import com.hcl.wallet.dao.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto dto);
    ProductDto updateProduct(Long productId, ProductDto dto);
    void deleteProduct(Long productId);
    ProductDto getProduct(Long productId);
    List<ProductDto> listProductsByMerchant(Long merchantId);
}

