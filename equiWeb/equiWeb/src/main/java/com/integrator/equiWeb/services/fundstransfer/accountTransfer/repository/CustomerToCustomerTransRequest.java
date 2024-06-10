package com.integrator.equiWeb.services.fundstransfer.accountTransfer.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerToCustomerTransRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal transactionAmount;
    private Long channelId;
    private String transactionReference;
    private String errorCode;
    private boolean reversalFlg;

}
