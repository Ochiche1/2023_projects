//package com.integrator.equiWeb.repository;
//
//
//
//import com.integrator.equiWeb.services.transactions.atmReversal.AtmReversalRequest;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class TXNReversalMapper implements RowMapper<AtmReversalRequest> {
//    @Override
//    public AtmReversalRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return AtmReversalRequest.builder()
//            .accountNo(rs.getString("ACCT_NO"))
//            .contraAcctNo(rs.getString("CONTRA_ACCT_NO"))
//            .systemReference(rs.getString("SYSTEM_REF"))
//            .txnAmount(rs.getBigDecimal("TRAN_AMT"))
//            .txnCurrencyCode(rs.getString("CRNCY_CD_ISO"))
//            .txnDate(rs.getString("TRAN_DT"))
//            .txnJournalId(rs.getLong("TRAN_JOURNAL_ID"))
//            .txnReference(rs.getString("TRAN_REF_TXT"))
//            .build();
//    }
//    }
//
