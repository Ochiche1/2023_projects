package com.integrator.equiWeb.services.transactions.glToDeposit.repository.database_results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GLToDepositRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal transactionAmount;
    private Long channelId;
    private String transactionReference;
    private String errorCode;
    private boolean reversalFlg;
}
