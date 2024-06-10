package com.integrator.equiWeb.services.fundstransfer.accountTransfer.impl;



import com.integrator.equiWeb.exceptions.ResponseConstants;
import com.integrator.equiWeb.services.fundstransfer.accountTransfer.repository.CustomerToCustomerResponse;
import com.integrator.equiWeb.services.fundstransfer.accountTransfer.repository.CustomerToCustomerTransRequest;
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
public class CustomerToCustomerTransferImplService implements CustomerToCustomerTransferService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public CustomerToCustomerResponse customerToCustomerTransfer(CustomerToCustomerTransRequest customerToCustomerRequest) {
        Map<String, Object> result = executeStoredProcedure(customerToCustomerRequest);
        String errorCode = result.get("pnErrorCode").toString();
        System.out.println(result);
        System.out.println("Error code = " + errorCode);
        CustomerToCustomerResponse response = new CustomerToCustomerResponse();
        if (errorCode.trim().equals("0")) {
            response.setResponseCode(ResponseConstants.SUCCEESS_CODE);
        }
        return response;
    }
    private Map<String, Object> executeStoredProcedure(CustomerToCustomerTransRequest customerToCustomerRequest) {
        int channelId = 7;
        String reversalFlag = customerToCustomerRequest.isReversalFlg() ? "Y" : "N";
        Map<String, Object> result;
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("csp_ex_customer_to_customer");

        SqlParameterSource inputParams = new MapSqlParameterSource()
            .addValue("psFromAccountNumber", customerToCustomerRequest.getFromAccountNumber(), Types.VARCHAR)
            .addValue("psToAccountNumber", customerToCustomerRequest.getToAccountNumber(), Types.VARCHAR)
            .addValue("pnTransactionAmount", customerToCustomerRequest.getTransactionAmount(), Types.DECIMAL)
            .addValue("pnChannelId", channelId, Types.SMALLINT)
            .addValue("psTransactionReference", customerToCustomerRequest.getTransactionReference(), Types.VARCHAR)
            .addValue("psReversalFlg", reversalFlag, Types.CHAR)
            .addValue("pnErrorCode", 0, Types.INTEGER)
            .addValue("psErrorMessage", "", Types.VARCHAR);
        result = simpleJdbcCall.execute(inputParams);
        System.out.println("Result : "+result.keySet().toString());
        return result;
    }}
