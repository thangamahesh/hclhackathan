package com.hcl.wallet.service.impl;

import com.hcl.wallet.model.Merchant;
import com.hcl.wallet.dao.ProductDto;
import com.hcl.wallet.model.Products;
import com.hcl.wallet.repository.ProductRepository;
import com.hcl.wallet.service.ProductService;
import com.hcl.wallet.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;

    @Override
    public ProductDto createProduct(ProductDto dto) {
        Merchant merchant = merchantRepository.findById(dto.getMerchantId()).orElseThrow(() -> new IllegalArgumentException("Merchant not found"));
        Products p = Products.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .currency(dto.getCurrency())
                .merchant(merchant)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Products saved = productRepository.save(p);
        return toDto(saved);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto dto) {
        Products p = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (dto.getName() != null) p.setName(dto.getName());
        if (dto.getPrice() != null) p.setPrice(dto.getPrice());
        if (dto.getCurrency() != null) p.setCurrency(dto.getCurrency());
        p.setUpdatedAt(LocalDateTime.now());
        Products saved = productRepository.save(p);
        return toDto(saved);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        return productRepository.findById(productId).map(this::toDto).orElse(null);
    }

    @Override
    public List<ProductDto> listProductsByMerchant(Long merchantId) {
        return productRepository.findByMerchant_MerchantId(merchantId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private ProductDto toDto(Products p) {
        ProductDto dto = new ProductDto();
        dto.setProductId(p.getProductId());
        dto.setMerchantId(p.getMerchant().getMerchantId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setCurrency(p.getCurrency());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        return dto;
    }
}

