package com.hcl.wallet.dto.wallet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResponse {
    private WalletResponse fromWallet;
    private WalletResponse toWallet;
}

