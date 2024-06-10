package com.integrator.equiWeb.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.integrator.equiWeb.repository.Query.*;

@RequiredArgsConstructor
@Repository
public class ReversalRepository implements ReversalOps{
private final JdbcTemplate jdbcTemplate;
    @Override
    public ReversalRequest queryTransaction() {
        return jdbcTemplate.queryForObject(SELECT_QUERY_FROM_PC_SERVICE, new ReversalMapper());
    }

    @Override
    public GLRequest glTransaction() {
        return jdbcTemplate.queryForObject(GL_QUERY, new GLMapper());
    }

//    @Override
//    public List<AtmReversalRequest> getTransactionData() {
//        return jdbcTemplate.query(SQL_TXN_REVERSAL, new TXNReversalMapper());
//    }

//    public AtmReversalRequest getTransactionData(String txnReference) {
//
//        List<AtmReversalRequest> records;
//        AtmReversalRequest response = new AtmReversalRequest();
//
//        records = jdbcTemplate.query(SQL_TXN_REVERSAL, new TXNReversalMapper(), new Object[] { txnReference });
//
//        if (records.size() == 0) {
//            return null;
//        } else {
//            response.setAccountNo(records.get(0).getAccountNo());
//            response.setContraAcctNo(records.get(0).getContraAcctNo());
//            response.setSystemReference(records.get(0).getSystemReference());
//            response.setTxnAmount(records.get(0).getTxnAmount());
//            response.setTxnCurrencyCode(records.get(0).getTxnCurrencyCode());
//            response.setTxnDate(records.get(0).getTxnDate());
//            response.setTxnJournalId(records.get(0).getTxnJournalId());
//            response.setTxnReference(records.get(0).getTxnReference());
//            ;
//
//        }
//        return response;
//    }



}
