package com.hcl.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSummaryDto {
    private String txId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime timestamp;
}
