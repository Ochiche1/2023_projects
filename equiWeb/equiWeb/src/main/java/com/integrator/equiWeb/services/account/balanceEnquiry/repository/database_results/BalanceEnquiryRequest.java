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
public class BalanceEnquiryRequest {
private String acctNo;
private BigDecimal availBal;
}
