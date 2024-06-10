//package com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DepositToGlMapper implements RowMapper<DepositToGLRequest> {
//    @Override
//    public DepositToGLRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return DepositToGLRequest.builder()
//            .fromAccountNumber(rs.getString("acctNo"))
//            .contraAcctNo(rs.getString("contraAcctNo"))
//            .transactionAmount(rs.getBigDecimal("transactionAmount"))
//            .txnCurrencyCode(rs.getString("txnCurrencyCode"))
//            .channelId(rs.getLong("channelId"))
//            .txnReference(rs.getString("txnReference"))
//            .xapiServiceCode(rs.getString("xapiServiceCode"))
//            .transmissionTime(rs.getLong("transmissionTime"))
//            .build();
//
//    }
//}
