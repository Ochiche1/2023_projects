package com.integrator.equiWeb.services.transactions.glToGL.impl;

import com.integrator.equiWeb.exceptions.ResponseConstants;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLAccountTransferResponse;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLRequest;
import com.integrator.equiWeb.services.transactions.glToGL.repository.database_results.GLToGLTransferRequest;

import com.integrator.equiWeb.services.transactions.glToGL.repository.database_results.GlToGlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GLtoGLXferImplServices implements GLtoGLXferServices {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public GlToGlResponse glToGlXfer(GLToGLTransferRequest glToGLTransferRequest) {
        Map<String, Object> result = executeStoredProcedure(glToGLTransferRequest);
        String errorCode = result.get("pnErrorCode").toString();
        System.out.println(result);
        System.out.println("Error code = " + errorCode);
        GlToGlResponse response = new GlToGlResponse();
        if (errorCode.trim().equals("0")) {
            response.setResponseCode(ResponseConstants.SUCCEESS_CODE);
            response.setResponseMessage(ResponseConstants.SUCCEESS_MESSAGE);
        }
        return response;
    }
    private Map<String, Object> executeStoredProcedure(GLToGLTransferRequest glToGLTransferRequest) {
        int channelId = 94;
        String reversalFlag = glToGLTransferRequest.isReversalFlg() ? "Y" : "N";
        System.out.println(reversalFlag);
        Map<String, Object> result;
        System.out.println(glToGLTransferRequest);
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("csp_ex_gl_to_gl");
        SqlParameterSource inputParams = new MapSqlParameterSource()
            .addValue("psFromAccountNumber", glToGLTransferRequest.getFromAccountNumber(), Types.VARCHAR)
            .addValue("psToAccountNumber", glToGLTransferRequest.getToAccountNumber(), Types.VARCHAR)
            .addValue("pnTransactionAmount", glToGLTransferRequest.getTransactionAmount(), Types.DECIMAL)
            .addValue("pnChannelId", channelId, Types.SMALLINT)
            .addValue("psTransactionReference", glToGLTransferRequest.getTransactionReference(), Types.VARCHAR)
            .addValue("psReversalFlg", reversalFlag, Types.CHAR)
            .addValue("pnErrorCode", 0, Types.INTEGER)
            .addValue("psErrorMessage", "", Types.VARCHAR);
        result = simpleJdbcCall.execute(inputParams);
        System.out.println(result);
        System.out.println(glToGLTransferRequest.getTransactionReference());
        System.out.println("Result : "+result.keySet().toString());
        return result;
    }



}
