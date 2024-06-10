package com.orbit.transaction.outward.repository.impl;

import com.orbit.transaction.inward.model.TransactionProperties;
import com.orbit.transaction.inward.model.mapper.TransactionPropertiesMapper;
import com.orbit.transaction.outward.repository.TransactionOps;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.orbit.transaction.inward.repository.SqlQueries.SELECT_QUERY_FOR_QUEUE;
import static com.orbit.transaction.inward.repository.SqlQueries.UPDATE_QUERY_FOR_QUEUE;

@RequiredArgsConstructor
@Repository
public class TransactionOpsImpl implements TransactionOps {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<TransactionProperties> queryQueuedTransaction() {
        return jdbcTemplate.query(SELECT_QUERY_FOR_QUEUE, new TransactionPropertiesMapper());
    }

    @Override
    public void updateTransactionStatus(Long trans_id, String status) {
    int update = jdbcTemplate.update(UPDATE_QUERY_FOR_QUEUE, trans_id);
        System.out.println(+ update);
    }


}
