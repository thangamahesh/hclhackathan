package com.hcl.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDashboardDto {
    private MerchantProfileDto merchant;
    private BigDecimal currentBalance;
    private long transactionsLast7Days;
    private BigDecimal amountLast7Days;
    private int pendingSettlements;
    private List<TransactionSummaryDto> recentTransactions;
}
