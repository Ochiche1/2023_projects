package com.orbit.transaction.controller;

import com.orbit.transaction.inward.model.TransactionProperties;
import com.orbit.transaction.inward.repository.TSQOutwardOps;
import com.orbit.transaction.outward.services.TransactionOutwardServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ServiceController {
private final TSQOutwardOps tsqOutwardOps;
private final TransactionOutwardServices transactionOutwardServices;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer( @RequestBody TransactionProperties properties)  {
        return ResponseEntity.ok(tsqOutwardOps.postTransactions(properties));
    }

  @PostMapping("/postTransaction")
    public ResponseEntity<?> postTransaction(@RequestBody TransactionProperties properties)  {
        return ResponseEntity.ok(transactionOutwardServices.Transactions(properties));
    }


}
