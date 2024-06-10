package com.integrator.equiWeb.services.transactions.depositToGL.impl;

import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLRequest;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLAccountTransferResponse;

public interface DepositToGLService {
    DepositToGLAccountTransferResponse depositToGL (DepositToGLRequest depositToGLRequest);

}
