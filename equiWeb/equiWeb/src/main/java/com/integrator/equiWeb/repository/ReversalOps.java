package com.integrator.equiWeb.repository;



public interface ReversalOps {
    ReversalRequest queryTransaction();
    GLRequest glTransaction();
   // AtmReversalRequest getTransactionData(String txnReference);

}
