package com.orbit.transaction.outward.services.impl;

import com.orbit.transaction.inward.model.Credentials;
import com.orbit.transaction.inward.model.EndpointResponse;
import com.orbit.transaction.inward.model.TransactionProperties;
import com.orbit.transaction.inward.model.TransferProperties;
import com.orbit.transaction.outward.repository.TransactionOps;
import com.orbit.transaction.outward.services.TransactionOutwardServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionOutwardServiceImpl implements TransactionOutwardServices {
    private final TransactionOps transactionOps;
    private final WebClient webClientBuilder;

    private final String JWT_ENDPOINT = "http://20.108.163.74:5559/api/v1/Account/jwt-appadmintoken";
    private final String POST_TRANSACTION_ENDPOINT = "http://20.108.163.74:5559/api/v0/ThirdParty/Postorbittransfer";
    private final int delaTime = 600000;

    @Override
    @Scheduled(fixedDelay = delaTime)
    public void Transactions() {
        EndpointResponse endpointResponse = webClientBuilder.post()
            .uri(JWT_ENDPOINT)
            .body(Mono.just(Credentials.builder()
                .userName("932367")
                .password("ksrt4670OJ")
                .build()), Credentials.class)
            .retrieve()
            .bodyToMono(EndpointResponse.class)
            .block();
        List<TransactionProperties> transactions = transactionOps.queryQueuedTransaction();
        if (transactions.isEmpty()) {
            log.error( "There is no record on queue in the database.");
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(""
            + "[yyyy-MM-dd HH:mm:ss.SSS]"
            + "[yyyy-MM-dd HH:mm:ss.SS]"
            + "[yyyy-MM-dd HH:mm:ss.S]"
            + "[yyyy-MM-dd HH:mm:ss]"
        );
        // 2023-03-07 10:41:47.6
        for (TransactionProperties trans_property : transactions) {
           TransferProperties transferProperties = new TransferProperties();
            transferProperties.setTrandate(LocalDateTime.parse(trans_property.getTrandate(), formatter).toString());
            transferProperties.setAccountnumber(trans_property.getAccountnumber());
            transferProperties.setAcctname(trans_property.getAcctname());
            transferProperties.setAcctype(trans_property.getAccttype());
            transferProperties.setTransactionreference(trans_property.getTransactionReference());
            transferProperties.setAmount(trans_property.getAmount());
            transferProperties.setDrcr(trans_property.getDrcr());
            transferProperties.setIsOcode(trans_property.getIsOcode());
            transferProperties.setNarration(trans_property.getNarration());

            System.out.println("This is transaction code " +trans_property.getIsOcode());

            trans_property.setTrandate(LocalDateTime.now().toString());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            assert endpointResponse != null;
            endpointResponse.getRefreshToken().getExpireAt();
            headers.set("Authorization", "Bearer " + endpointResponse.getAccessToken());
            HttpEntity<TransferProperties> requestBody = new HttpEntity<>(transferProperties, headers);
             // call the API endpoint using the RestTemplate
            try {
                String response = webClientBuilder.post()
                    .uri(POST_TRANSACTION_ENDPOINT)
                    .header("Authorization", "Bearer " + endpointResponse.getAccessToken())
                    .body(Mono.just(transferProperties), TransferProperties.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
//           if (response.getStatusCode() == HttpStatus.OK) {
                log.info("Response: {},  Trans_ID: {}", response, trans_property.getTran_id());

                // update the process_status of the transaction to 'pro'
                assert response != null;
                if (response.equals("00")) {
                    transactionOps.updateTransactionStatus(trans_property.getTran_id(), "Processed");
                }
//           }cessed
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }

        }
    }

}
