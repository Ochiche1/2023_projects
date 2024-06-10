package com.integrator.equiWeb.services.transactions.glToGL.repository.database_results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GLToGLTransferRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal transactionAmount;
    private Long channelId;
    private String transactionReference;
    private String errorCode;
    private boolean reversalFlg;

}
