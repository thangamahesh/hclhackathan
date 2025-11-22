package com.hcl.wallet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "mobile_number", nullable = false, unique = true, length = 30)
    private String mobileNumber;

    @Column(name = "kyc_status", nullable = false, length = 20)
    private String kycStatus;

    // store password hash for authentication; nullable to avoid breaking existing data
    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @PrimaryKeyJoinColumn(name = "customer_transactional_currency")
    @OneToOne
    private Currency customerTransactionalCurrency;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
