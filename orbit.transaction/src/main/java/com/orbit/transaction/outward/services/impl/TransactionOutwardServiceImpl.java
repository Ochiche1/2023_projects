package com.orbit.transaction.outward.services.impl;

import com.orbit.transaction.inward.model.TransactionProperties;
import com.orbit.transaction.outward.repository.TransactionOps;
import com.orbit.transaction.outward.services.TransactionOutwardServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionOutwardServiceImpl implements TransactionOutwardServices {
    private final RestTemplate restTemplate;
    private final TransactionOps transactionOps;
    //@Override
//    public void processQueuedTransactions() {
//        List<TransactionProperties> transactions = transactionOps.queryQueuedTransaction();
//        // loop through each transaction and call the API endpoint
//        for (TransactionProperties transaction : transactions) {
//           String endpointUrl = "http://20.108.163.74:5559/api/v12/CoreBanking/PostMCliqdebitcreditpositing";
//            String jwtToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiOTMyMzY3IiwiZXhwIjoxNjc3NjgzMjc3LCJpc3MiOiJjbGlxYXV0b21hdGlvbnNlcnZpY2VzIiwiYXVkIjoiY2xpcW1pY3Jvc2VydmljZXMifQ.1ZJOtVvUdyQFlJHwuBTXIqHWaL6lBqtWtf7d6R92ftQ";
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer " + jwtToken);
//            HttpEntity<TransactionProperties> requestBody = new HttpEntity<>(transaction, headers);
//
//            // call the API endpoint using the RestTemplate
//            try {
//                ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestBody, String.class);
//                if (response.getStatusCode() == HttpStatus.OK) {
//                    // update the process_status of the transaction to 'Queued'
//                    transactionOps.updateTransactionStatus(transaction.getTran_id(), "Queued");
//                }
//            } catch (Exception e) {
//                // handle any exceptions that occur during the API call
//                e.printStackTrace();
//            }
//        }
//
//
//    }

    @Override
    @Scheduled(fixedDelay = 600000)
//    public String testTransaction(TransactionProperties transaction) {
//       List<TransactionProperties> transactions = transactionOps.queryQueuedTransaction();
//
//        for (TransactionProperties trans_property : transactions) {
//
//            trans_property.setTrandate(LocalDateTime.now().toString());
//            String endpointUrl = "http://20.108.163.74:5559/api/v0/ThirdParty/Postorbittransfer";
//            String jwtToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiOTMyMzY3IiwiZXhwIjoxNjc4ODk1ODcyLCJpc3MiOiJjbGlxYXV0b21hdGlvbnNlcnZpY2VzIiwiYXVkIjoiY2xpcW1pY3Jvc2VydmljZXMifQ.AzxXZGeUVGIn1pPgTMGNNhi_gb9tPXuzLKEHCut7tqc";
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer " + jwtToken);
//            HttpEntity<TransactionProperties> requestBody = new HttpEntity<>(trans_property, headers);
//        if (transactions.isEmpty()) {
//            log.info("No transactions found in the database.");
//            return null;
//        }
//
//            // call the API endpoint using the RestTemplate
//            try {
//                ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestBody, String.class);
////           if (response.getStatusCode() == HttpStatus.OK) {
//                log.info("Response: {}", response.getBody());
//
//                // update the process_status of the transaction to 'processed'
//                transactionOps.updateTransactionStatus(transaction.getTran_id(),  "Processed");
//                return response.getBody();
////           }
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//
//       }
//        return null;
//    }


    public String Transactions(TransactionProperties transaction) {
        List<TransactionProperties> transactions = transactionOps.queryQueuedTransaction();

        for (TransactionProperties trans_property : transactions) {

            trans_property.setTrandate(LocalDateTime.now().toString());
            String endpointUrl = "http://20.108.163.74:5559/api/v0/ThirdParty/Postorbittransfer";
            String jwtToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiOTMyMzY3IiwiZXhwIjoxNjc4ODk1ODcyLCJpc3MiOiJjbGlxYXV0b21hdGlvbnNlcnZpY2VzIiwiYXVkIjoiY2xpcW1pY3Jvc2VydmljZXMifQ.AzxXZGeUVGIn1pPgTMGNNhi_gb9tPXuzLKEHCut7tqc";
            String refreshToken = "msVJiSM1eiF3L+uJ8BeddaoCJInv/Y9kfCCxTOVDbUk=";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + jwtToken);
            HttpEntity<TransactionProperties> requestBody = new HttpEntity<>(trans_property, headers);
            if (transactions.isEmpty()) {
                log.info("No transactions found in the database.");
                return null;
            }

            // call the API endpoint using the RestTemplate

            // call the API endpoint using the RestTemplate
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestBody, String.class);
                if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    // The JWT token is expired, try refreshing it using the refresh token
                    headers.setBearerAuth(refreshToken);  // Add the refresh token to the headers
                    HttpEntity<Void> refreshTokenRequest = new HttpEntity<>(headers);
                    ResponseEntity<String> refreshTokenResponse = restTemplate.postForEntity("http://20.108.163.74:5559/api/v1/Account/jwt-appadmintoken", refreshTokenRequest, String.class);

                    if (refreshTokenResponse.getStatusCode() == HttpStatus.OK) {
                        // The refresh token succeeded, get the new JWT token and retry the API call
                        String newJwtToken = refreshTokenResponse.getBody();
                        headers.setBearerAuth(newJwtToken);  // Set the new JWT token in the headers
                        requestBody = new HttpEntity<>(trans_property, headers);
                        response = restTemplate.postForEntity(endpointUrl, requestBody, String.class);
                    }
                }

                if (response.getStatusCode() == HttpStatus.OK) {
                    log.info("Response: {}", response.getBody());

                    // update the process_status of the transaction to 'processed'
                    transactionOps.updateTransactionStatus(transaction.getTran_id(), "Processed");
                    return response.getBody();
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }

        }
        return null;
    }
}
