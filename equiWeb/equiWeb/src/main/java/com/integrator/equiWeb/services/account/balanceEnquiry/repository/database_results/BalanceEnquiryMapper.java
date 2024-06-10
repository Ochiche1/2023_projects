package com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class BalanceEnquiryMapper implements RowMapper<BalanceEnquiryRequest> {
    @Override
    public BalanceEnquiryRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BalanceEnquiryRequest.builder()
            .acctNo(rs.getString("acctNo"))
            .availBal(rs.getBigDecimal("availBal"))
            .build();
    }
}
