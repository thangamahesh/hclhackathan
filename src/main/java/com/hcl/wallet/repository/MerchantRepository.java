package com.hcl.wallet.repository;

import com.hcl.wallet.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Integer>  {
}
