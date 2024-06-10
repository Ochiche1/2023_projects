package com.orbit.transaction.inward.repository.Impl;

import com.orbit.transaction.inward.dto.response.TransactionStatusQueryResponse;
import com.orbit.transaction.inward.exceptions.ResponseConstants;
import com.orbit.transaction.inward.model.ResponseModel;
import com.orbit.transaction.inward.model.TransactionProperties;
//import com.orbit.transaction.outward.repository.TSQOutwardOps;
import com.orbit.transaction.inward.repository.TSQOutwardOps;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Map;

import static com.orbit.transaction.inward.repository.SqlQueries.SAVE_TRANSACTION_STATUS_QUERY;

@Service
@RequiredArgsConstructor
public class TSQOutwardOpsImpl implements TSQOutwardOps {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ResponseModel postTransactions(TransactionProperties properties) {
        Map<String, Object> result = executeStoredProcedure(properties);
        String errorCode = result.get("error_code").toString();
        System.out.println(result);
        System.out.println("Error code = " + errorCode);
        ResponseModel responseModel = new ResponseModel();
        if (errorCode.trim().equals("0")) {
            responseModel.setResponseCode(ResponseConstants.SUCCEESS_CODE);
            responseModel.setResponseMessage(ResponseConstants.SUCCEESS_MESSAGE);
        }
        return responseModel;
    }

    private Map<String, Object> executeStoredProcedure(TransactionProperties properties) {

        int channelId = 96;
//        BigDecimal chargeAmount = BigDecimal.ZERO;
//        BigDecimal taxAmount = BigDecimal.ZERO;

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("csp_orbit_cliq_TranPosting");

        SqlParameterSource inputParams = new MapSqlParameterSource()
            .addValue("isOcode", properties.getIsOcode(), Types.VARCHAR)
            .addValue("accountnumber", properties.getAccountnumber(), Types.VARCHAR)
            .addValue("acctname", properties.getAcctname(), Types.VARCHAR)
            .addValue("acctype", properties.getAcctype(), Types.VARCHAR)
            .addValue("accountstatus", properties.getAccountstatus(), Types.VARCHAR)
            .addValue("amount", properties.getAmount(), Types.DECIMAL)
            .addValue("dailylimit", properties.getDailylimit(), Types.DECIMAL)
            .addValue("currentbalance", properties.getCurrentbalance(), Types.DECIMAL)
            .addValue("narration", properties.getNarration(), Types.VARCHAR)
            .addValue("transactionreference", properties.getTransactionReference(), Types.VARCHAR)
            .addValue("channelId", channelId, Types.SMALLINT)
            .addValue("drcr", properties.getDrcr(), Types.VARCHAR)
            .addValue("trandate", properties.getTrandate(), Types.DATE)
            .addValue("error_code", 0, Types.INTEGER)
            .addValue("error_message", "", Types.VARCHAR);
        return simpleJdbcCall.execute(inputParams);
    }

    @Override
    public boolean hasSavedTSQ(TransactionStatusQueryResponse transactionStatusQueryResp) {
        jdbcTemplate.update(SAVE_TRANSACTION_STATUS_QUERY, transactionStatusQueryResp.getSessionId(), transactionStatusQueryResp.getSourceInstitutionCode(),
            transactionStatusQueryResp.getChannelCode(), transactionStatusQueryResp.getResponseCode());
        return false;

    }
}
