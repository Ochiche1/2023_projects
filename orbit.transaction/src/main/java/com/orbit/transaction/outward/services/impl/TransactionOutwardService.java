//package com.orbit.transaction.outward.services.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.Map;
//@Service
//public class TransactionOutwardService implements TransactionOutServ {
//    @Autowired
//    private RestTemplate restTemplate; // To make HTTP requests
//
//    @Value("${endpoint.url}")
//    private String endpointUrl; // Endpoint URL
//
//    @Value("${transaction.interval}")
//    private Long transactionInterval; // Interval between transactions in milliseconds
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate; // To execute SQL queries
//
//    @Scheduled(fixedDelayString = "${transaction.interval}")
//    public void processTransactions() {
//        // Select queued transactions from the table
//        String selectQuery = "SELECT tran_id, accountnumber, isOcode, accountstatus, acctname, drcr, acctype, transactionreference, amount, dailylimit, currentbalance, trandate, narration, channelId "
//            + "FROM orbit_cliq_outward_trans "
//            + "WHERE process_status = 'Queued'";
//        List<Map<String, Object>> transactions = jdbcTemplate.queryForList(selectQuery);
//
//        // Process each transaction
//        for (Map<String, Object> transaction : transactions) {
//            Long tranId = (Long) transaction.get("tran_id");
//            try {
//                String jwtToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiOTMyMzY3IiwiZXhwIjoxNjc4MTE0ODU3LCJpc3MiOiJjbGlxYXV0b21hdGlvbnNlcnZpY2VzIiwiYXVkIjoiY2xpcW1pY3Jvc2VydmljZXMifQ.NxnD1BC50Fw8puz9AjUjYNis_Akb010LGCmo2pDP4r4";
//                // Make an HTTP POST request to the endpoint
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_JSON);
//                headers.set("Authorization", "Bearer " + jwtToken);
//                HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(transaction, headers);
//                ResponseEntity<String> responseEntity = restTemplate.postForEntity(endpointUrl, requestEntity, String.class);
//
//                // Check the response status code
//                if (responseEntity.getStatusCode() == HttpStatus.OK) {
//                    // Update the table with the processed transaction
//                    String updateQuery = "UPDATE orbit_cliq_outward_trans SET process_status = 'Processed' WHERE tran_id = ?";
//                    jdbcTemplate.update(updateQuery, tranId);
//                } else {
//                    // Log an error message
//                    System.out.println("Error processing transaction " + tranId + ": " + responseEntity.getBody());
//                }
//            } catch (Exception ex) {
//                // Log an error message
//                System.out.println("Error processing transaction " + tranId + ": " + ex.getMessage());
//            }
//        }
//    }
//}
//
