package com.orbit.transaction.inward.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferDirectCreditRequest {
    private String sessionId;
    private String nameEnquiryRef;
    private String destinationInstitutionCode;
    private int channelCode;
    private String beneficiaryAccountName;
    private String beneficiaryAccountNumber;
    private String beneficiaryBankVerificationNumber;
    private String beneficiaryKYCLevel;
    private String originatorAccountName;
    private String originatorAccountNumber;
    private String originatorBankVerificationNumber;
    private int originatorKYCLevel;
    private String transactionLocation;
    private String narration;
    private String paymentReference;
    private BigDecimal amount;
}
