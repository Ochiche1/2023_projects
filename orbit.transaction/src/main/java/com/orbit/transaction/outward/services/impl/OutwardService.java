//package com.orbit.transaction.outward.services.impl;
//
//import com.orbit.transaction.inward.model.TransactionProperties;
//import com.orbit.transaction.outward.repository.TransactionOps;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.support.TransactionOperations;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDateTime;
//import java.util.List;
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class OutwardService implements TransactionOutServ{
//    private static final String ENDPOINT_URL = "http://20.108.163.74:5559/api/v0/ThirdParty/PostMCliqdebitcreditpositing";
//    private static final String JWT_TOKEN = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiOTMyMzY3IiwiZXhwIjoxNjc4MjAwMTMxLCJpc3MiOiJjbGlxYXV0b21hdGlvbnNlcnZpY2VzIiwiYXVkIjoiY2xpcW1pY3Jvc2VydmljZXMifQ.lZxRe2H1SbKzC7qu-5ZuIZYp03W1AWtwRCSJHF8ECjc";
//    private static final int INTERVAL_SECONDS = 60;
//    private final TransactionOps transactionOps;
//    private final RestTemplate restTemplate;
//
//
//
//    public void processTransactions() {
//
//        try {
//            List<TransactionProperties> transactions = transactionOps.queryQueuedTransaction();
//            for (TransactionProperties transaction : transactions) {
//                transaction.setTrandate(LocalDateTime.now().toString());
//                processedTransaction(transaction);
//            }
//            Thread.sleep(INTERVAL_SECONDS * 10 );
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//
//      }
//
//       private void processedTransaction(TransactionProperties transaction) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + JWT_TOKEN);
//        HttpEntity<TransactionProperties> requestBody = new HttpEntity<>(transaction, headers);
//
//        try {
//            ResponseEntity<String> response = restTemplate.postForEntity(ENDPOINT_URL, requestBody, String.class);
//            log.info("Response: {}", response.getBody());
//            transactionOps.updateTransactionStatus(transaction.getTran_id(),  "Processed");
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }
//    }
//}
