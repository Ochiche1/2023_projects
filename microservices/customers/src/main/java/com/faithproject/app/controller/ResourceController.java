package com.faithproject.app.controller;

import com.faithproject.app.dto.CustomerRequestRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
public record ResourceController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRequestRegistration customerRequest){
    log.info("new customer registration {} ", customerRequest);
    }
}
