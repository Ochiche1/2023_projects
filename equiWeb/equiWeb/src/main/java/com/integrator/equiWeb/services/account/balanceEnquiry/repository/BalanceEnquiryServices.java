package com.integrator.equiWeb.services.account.balanceEnquiry.repository;

import com.integrator.equiWeb.dto.ServiceResponse;
import com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results.BalanceEnquiryRequest;
import com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results.BalanceEnquiryResponse;

public interface BalanceEnquiryServices {
    BalanceEnquiryResponse balanceEnquiries(BalanceEnquiryRequest balanceEnquiryRequest);

}
