package com.orbit.transaction.inward.model.mapper;

import com.orbit.transaction.inward.model.TransactionProperties;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TransactionPropertiesMapper implements RowMapper<TransactionProperties> {


    @Override
    public TransactionProperties mapRow(ResultSet rs, int rowNum) throws SQLException {

        return TransactionProperties.builder()
            .tran_id(rs.getLong("tran_id"))
            .accountnumber(rs.getString("accountnumber"))
            .isOcode(rs.getString("isOcode"))
            .acctname(rs.getString("acctname"))
            .drcr(rs.getString("drcr"))
            .accttype(rs.getString("acctype"))
            .transactionReference(rs.getString("transactionreference"))
            .amount(rs.getBigDecimal("amount"))
           .trandate(rs.getString("trandate"))
            .narration(rs.getString("narration"))
            .build();
           }
}
