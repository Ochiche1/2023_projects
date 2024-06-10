package com.integrator.equiWeb.services.account.balanceEnquiry.repository.Impl;

import com.integrator.equiWeb.services.account.balanceEnquiry.repository.BalanceEnquiryServices;
import com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results.BalanceEnquiryRequest;
import com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results.BalanceEnquiryResponse;
import lombok.RequiredArgsConstructor;
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
public class BalanceEnquiryImplServices implements BalanceEnquiryServices {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public BalanceEnquiryResponse balanceEnquiries(BalanceEnquiryRequest balanceEnquiryRequest) {
        Map<String, Object> result = executeStoredProcedure(balanceEnquiryRequest);
        BalanceEnquiryResponse response = new BalanceEnquiryResponse();
        response.setTargetAccountNumber((String) result.get("psAcctNo"));
       response.setAvailBal((BigDecimal) result.get("pnAvailBal"));
        return response;
    }

    private Map<String, Object> executeStoredProcedure(BalanceEnquiryRequest balanceEnquiryRequest) {

        int channelId = 7;
        Map<String, Object> result;
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("csp_ex_balance_inquiry");

        SqlParameterSource inputParams = new MapSqlParameterSource()
            .addValue("psAcctNo", balanceEnquiryRequest.getAcctNo(), Types.VARCHAR)
            .addValue("pnAvailBal", balanceEnquiryRequest.getAvailBal(), Types.DECIMAL)
            .addValue("channelId", channelId, Types.SMALLINT);
              result = simpleJdbcCall.execute(inputParams);
        System.out.println("Result : "+result);
        return result;
    }
}
