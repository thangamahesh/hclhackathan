package com.hcl.wallet.dao;

import com.hcl.wallet.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private Long merchantId;
    private String name;
    private String description;
    private String price;
    private Currency currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

