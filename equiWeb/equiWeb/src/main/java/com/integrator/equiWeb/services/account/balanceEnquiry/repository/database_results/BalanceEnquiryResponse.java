package com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceEnquiryResponse {
    private String authorizationCode;
    private BigDecimal availBal;
    private String channelCode;
    private String destinationInstitutionCode;
    private String responseCode;
    private String responseMessage;
    private String sessionId;
    private String targetAccountName;
    private String targetAccountNumber;
    private String targetBankVerificationNumber;
}
