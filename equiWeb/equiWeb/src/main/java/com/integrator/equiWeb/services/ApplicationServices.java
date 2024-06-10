package com.integrator.equiWeb.services;


import com.integrator.equiWeb.dto.ServiceRequest;
import com.integrator.equiWeb.dto.ServiceResponse;
import org.springframework.http.ResponseEntity;

public interface ApplicationServices {
    ResponseEntity<ServiceResponse> allTransactions(ServiceRequest serviceRequest);
}
