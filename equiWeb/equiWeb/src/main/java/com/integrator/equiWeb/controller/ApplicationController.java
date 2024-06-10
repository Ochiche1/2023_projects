package com.integrator.equiWeb.controller;


import com.integrator.equiWeb.dto.ServiceRequest;
import com.integrator.equiWeb.dto.ServiceResponse;
import com.integrator.equiWeb.services.ApplicationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ApplicationController {

    private final ApplicationServices applicationService;
    @PostMapping("transfer/transactions")
    public ResponseEntity<ServiceResponse> postTransactions(@RequestBody ServiceRequest serviceRequest) {
        return applicationService.allTransactions(serviceRequest);
    }
}
