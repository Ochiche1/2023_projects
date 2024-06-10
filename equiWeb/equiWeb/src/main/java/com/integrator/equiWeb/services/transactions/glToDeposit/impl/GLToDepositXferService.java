package com.integrator.equiWeb.services.transactions.glToDeposit.impl;

import com.integrator.equiWeb.services.transactions.glToDeposit.repository.database_results.GLToDepositRequest;
import com.integrator.equiWeb.services.transactions.glToDeposit.repository.database_results.GlToDepositResponse;

public interface GLToDepositXferService {
    GlToDepositResponse glToDeposit(GLToDepositRequest glToDepositRequest);
}
