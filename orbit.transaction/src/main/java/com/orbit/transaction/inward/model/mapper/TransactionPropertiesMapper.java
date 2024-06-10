package com.orbit.transaction.inward.model.mapper;

import com.orbit.transaction.inward.model.TransactionProperties;
import net.sourceforge.jtds.jdbc.DateTime;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class TransactionPropertiesMapper implements RowMapper<TransactionProperties> {


    @Override
    public TransactionProperties mapRow(ResultSet rs, int rowNum) throws SQLException {

        return TransactionProperties.builder()
            .tran_id(rs.getLong("tran_id"))
            .accountnumber(rs.getString("accountnumber"))
            .isOcode(rs.getString("isOcode"))
            .acctname(rs.getString("acctname"))
            .drcr(rs.getString("drcr"))
            .acctype(rs.getString("acctype"))
            .transactionReference(rs.getString("transactionreference"))
            .amount(rs.getBigDecimal("amount"))
           .trandate(rs.getString("trandate"))
            .narration(rs.getString("narration"))
            .build();
           }
}
