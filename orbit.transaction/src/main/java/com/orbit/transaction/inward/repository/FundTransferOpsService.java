//package com.orbit.transaction.inward.repository;
//
//import com.orbit.transaction.inward.dto.response.FundTransferDirectCreditResponse;
//import com.orbit.transaction.inward.model.NIPRequestType;
//
//import java.math.BigDecimal;
//
//public interface FundTransferOpsService {
//    boolean isAccountBlocked(String debitAccountNumber);
//    boolean saveIntoFundTransferDebit(FundTransferDirectCreditResponse response, NIPRequestType requestType);
//    BigDecimal getMandateAmount(String mandateReferenceNumber);
//    boolean isFundTransferSaved(FundTransferDirectCreditResponse response);
//
//}
