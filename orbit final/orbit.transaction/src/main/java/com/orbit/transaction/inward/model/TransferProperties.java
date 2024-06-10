package com.orbit.transaction.inward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferProperties {
    private String accountnumber;
    private String isOcode;
    private String acctname;
    private String acctype;
    private String drcr;
    private String transactionreference;
    private BigDecimal amount;
    private String trandate;
    private String narration;
}
