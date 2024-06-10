package com.integrator.equiWeb.services.transactions.glToDeposit.impl;

import com.integrator.equiWeb.exceptions.ResponseConstants;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLAccountTransferResponse;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLRequest;
import com.integrator.equiWeb.services.transactions.glToDeposit.repository.database_results.GLToDepositRequest;

import com.integrator.equiWeb.services.transactions.glToDeposit.repository.database_results.GlToDepositResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


import java.sql.Types;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GLToDepositXferImplService implements GLToDepositXferService {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public GlToDepositResponse glToDeposit(GLToDepositRequest glToDepositRequest) {
        Map<String, Object> result = executeStoredProcedure(glToDepositRequest);
        String errorCode = result.get("pnErrorCode").toString();
        System.out.println(result);
        System.out.println("Error code = " + errorCode);
        GlToDepositResponse response = new GlToDepositResponse();
        if (errorCode.trim().equals("0")) {
            response.setResponseCode(ResponseConstants.SUCCEESS_CODE);
        }
        return response;

    }
    private Map<String, Object> executeStoredProcedure(GLToDepositRequest glToDepositRequest) {

        int channelId = 94;
        String reversalFlag = glToDepositRequest.isReversalFlg() ? "Y" : "N";
        Map<String, Object> result;
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("csp_ex_gl_to_deposit");

        SqlParameterSource inputParams = new MapSqlParameterSource()
            .addValue("psFromAccountNumber", glToDepositRequest.getFromAccountNumber(), Types.VARCHAR)
            .addValue("psToAccountNumber", glToDepositRequest.getToAccountNumber(), Types.VARCHAR)
            .addValue("pnTransactionAmount", glToDepositRequest.getTransactionAmount(), Types.DECIMAL)
            .addValue("pnChannelId", channelId, Types.SMALLINT)
            .addValue("psTransactionReference", glToDepositRequest.getTransactionReference(), Types.VARCHAR)
            .addValue("psReversalFlg", reversalFlag, Types.CHAR)
            .addValue("pnErrorCode", 0, Types.INTEGER)
            .addValue("psErrorMessage", "", Types.VARCHAR);
        result = simpleJdbcCall.execute(inputParams);
        System.out.println("Result : "+result.keySet().toString());
        return result;
    }

}
