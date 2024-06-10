package com.orbit.transaction.outward.repository;

import com.orbit.transaction.inward.model.TransactionProperties;

import java.util.List;

public interface TransactionOps {
    List<TransactionProperties> queryQueuedTransaction();
    void updateTransactionStatus(Long trans_id, String status);
}
