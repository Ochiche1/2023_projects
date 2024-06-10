package com.integrator.equiWeb.services.transactions.depositToGL.impl;

import com.integrator.equiWeb.exceptions.ResponseConstants;
import com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results.BalanceEnquiryResponse;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLRequest;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLAccountTransferResponse;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class DepositToGLImplService implements DepositToGLService {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public DepositToGLAccountTransferResponse depositToGL(DepositToGLRequest depositToGLRequest) {
        Map<String, Object> result = executeStoredProcedure(depositToGLRequest);
        String errorCode = result.get("pnErrorCode").toString();
        System.out.println(result);
        System.out.println("Error code = " + errorCode);
        DepositToGLAccountTransferResponse response = new DepositToGLAccountTransferResponse();
        if (errorCode.trim().equals("0")) {
            response.setResponseCode(ResponseConstants.SUCCEESS_CODE);
        }
        return response;
//        DepositToGLAccountTransferResponse response = new DepositToGLAccountTransferResponse();
//        response.setPrimaryAccountNumber((String) result.get("psFromAccountNumber"));
//        response.setTransactionAmount((BigDecimal) result.get("pnTransactionAmount"));
//        response.setTxnReference((String) result.get("psTransactionReference"));
//        response.setTransmissionTime((Long) result.get("psTransactionReference"));
//        response.setResponseCode((String) result.get("pnResponseCode"));
//        System.out.println("Response :"+ response);
//        return response;
    }


    private Map<String, Object> executeStoredProcedure(DepositToGLRequest depositToGLRequest) {

        int channelId = 94;
        String reversalFlag = depositToGLRequest.isReversalFlg() ? "Y" : "N";

        Map<String, Object> result;
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("csp_ex_deposit_to_gl");

        SqlParameterSource inputParams = new MapSqlParameterSource()
            .addValue("psFromAccountNumber", depositToGLRequest.getFromAccountNumber(), Types.VARCHAR)
            .addValue("psToAccountNumber", depositToGLRequest.getToAccountNumber(), Types.VARCHAR)
            .addValue("pnTransactionAmount", depositToGLRequest.getTransactionAmount(), Types.DECIMAL)
            .addValue("pnChannelId", channelId, Types.SMALLINT)
            .addValue("psTransactionReference", depositToGLRequest.getTransactionReference(), Types.VARCHAR)
            .addValue("psReversalFlg", reversalFlag, Types.CHAR)
            .addValue("pnErrorCode", 0, Types.INTEGER)
           .addValue("psErrorMessage", "", Types.VARCHAR);
        result = simpleJdbcCall.execute(inputParams);
        System.out.println("Result : "+result.keySet().toString());
        return result;
    }

}
