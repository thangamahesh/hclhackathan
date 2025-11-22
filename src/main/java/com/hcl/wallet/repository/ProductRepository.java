package com.hcl.wallet.repository;

import com.hcl.wallet.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByMerchant_MerchantId(Long merchantId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Products p where p.productId = :id")
    Optional<Products> findByIdForUpdate(@Param("id") Long id);
}
