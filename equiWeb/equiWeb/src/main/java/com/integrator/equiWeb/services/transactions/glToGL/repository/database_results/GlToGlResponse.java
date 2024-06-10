package com.integrator.equiWeb.services.transactions.glToGL.repository.database_results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlToGlResponse {
    private String primaryAccountNumber;
    private String responseCode;
    private String retrievalReferenceNumber;
    private String track2Data;
    private BigDecimal transactionAmount;
    private String transactionCurrencyCode;
    private Long transmissionTime;
    private Long txnJournalId;
    private String txnReference;
    private String responseMessage;
}
