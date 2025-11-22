package com.hcl.wallet.repository;

import com.hcl.wallet.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByMerchant_MerchantId(Long merchantId);
}

