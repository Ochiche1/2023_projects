package com.orbit.transaction.inward.model;

import lombok.Builder;
import lombok.Data;
import net.sourceforge.jtds.jdbc.DateTime;

import java.math.BigDecimal;
import java.util.Date;


@Data
@Builder
public class TransactionProperties {
    private Long tran_id;
    private int channelId;
    private String accountnumber;
    private String isOcode;
    private String accountstatus;
    private String acctname;
    private String acctype;
    private BigDecimal amount;
    private BigDecimal dailylimit;
    private BigDecimal currentbalance;
    private String narration;
    private String transactionReference;
    private String drcr;
    private String trandate;

}
