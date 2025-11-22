package com.hcl.wallet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fees {

    @Id
    @Column(name = "fee_id")
    private Long feeId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transaction_id")
    private Transactions transaction;

    @Column(name = "txn_amount", nullable = false)
    private Long txnAmount;

    @Column(name = "fee_type", nullable = false, length = 50)
    private String feeType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
