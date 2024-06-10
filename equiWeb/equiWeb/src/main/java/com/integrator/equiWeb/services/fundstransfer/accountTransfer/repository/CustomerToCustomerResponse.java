package com.integrator.equiWeb.services.fundstransfer.accountTransfer.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerToCustomerResponse {
    private Long feeTxnJournalId;
    private String feeTxnReference;
    private String fromAccountNumber;
    private String responseCode;
    private String retrievalReferenceNumber;
    private BigDecimal toAccountBalance;
    private String toAccountNumber;
    private String track2Data;
    private BigDecimal transactionAmount;
    private Long txnJournalId;
    private String txnReference;
}
