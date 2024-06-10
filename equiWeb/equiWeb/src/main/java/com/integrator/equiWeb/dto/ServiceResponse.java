package com.integrator.equiWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {
    private String serviceId;
    private Date effectiveDt;
    private String terminalId;
    private String aTMSwitchID;
    private String origReferenceNo;
    private String referenceNo;
    private Long transmissionTime;;
    private boolean isReversal;
    private  String cardNo;
    private String processingCode;
    private String isoCurrency;
    private String fromAccount;
    private String toAccount;
    private BigDecimal tranAmount1;
    private BigDecimal tranAmount2;
    private String locationDescription;
    private String tranDescription;
    private String responseCode;
    private String responseMessage;

}
