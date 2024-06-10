package com.integrator.equiWeb.services.fundstransfer.accountTransfer.impl;

import com.integrator.equiWeb.services.fundstransfer.accountTransfer.repository.CustomerToCustomerResponse;
import com.integrator.equiWeb.services.fundstransfer.accountTransfer.repository.CustomerToCustomerTransRequest;

public interface CustomerToCustomerTransferService {
    CustomerToCustomerResponse customerToCustomerTransfer (CustomerToCustomerTransRequest customerToCustomerRequest);
}
