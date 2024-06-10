//package com.integrator.equiWeb.services.transactions.glToDeposit.repository.database_results;
//
//import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLRequest;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class GlToDepositMapper implements RowMapper<GLToDepositRequest> {
//    @Override
//    public GLToDepositRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return GLToDepositRequest.builder()
//            .acctNo(rs.getString("acctNo"))
//            .contraAcctNo(rs.getString("contraAcctNo"))
//            .amount(rs.getBigDecimal("amount"))
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
