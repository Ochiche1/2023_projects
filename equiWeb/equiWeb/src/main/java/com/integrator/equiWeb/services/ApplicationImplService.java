package com.integrator.equiWeb.services;


//import com.integrator.equiWeb.config.RubikonCredentials;
import com.integrator.equiWeb.dto.ServiceRequest;
import com.integrator.equiWeb.dto.ServiceResponse;
//import com.integrator.equiWeb.repository.ActivityOps;
//import com.integrator.equiWeb.repository.GLRequest;
//import com.integrator.equiWeb.repository.ReversalRepository;
//import com.integrator.equiWeb.repository.ReversalRequest;
import com.integrator.equiWeb.repository.ActivityOps;
import com.integrator.equiWeb.repository.GLRequest;
import com.integrator.equiWeb.repository.ReversalRepository;
import com.integrator.equiWeb.repository.ReversalRequest;
import com.integrator.equiWeb.services.account.balanceEnquiry.repository.BalanceEnquiryServices;
import com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results.BalanceEnquiryRequest;
import com.integrator.equiWeb.services.account.balanceEnquiry.repository.database_results.BalanceEnquiryResponse;
import com.integrator.equiWeb.services.fundstransfer.accountTransfer.impl.CustomerToCustomerTransferService;
import com.integrator.equiWeb.services.fundstransfer.accountTransfer.repository.CustomerToCustomerResponse;
import com.integrator.equiWeb.services.fundstransfer.accountTransfer.repository.CustomerToCustomerTransRequest;
import com.integrator.equiWeb.services.transactions.depositToGL.impl.DepositToGLService;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLRequest;
import com.integrator.equiWeb.services.transactions.depositToGL.repository.database_results.DepositToGLAccountTransferResponse;
import com.integrator.equiWeb.services.transactions.glToDeposit.impl.GLToDepositXferService;
import com.integrator.equiWeb.services.transactions.glToDeposit.repository.database_results.GLToDepositRequest;
import com.integrator.equiWeb.services.transactions.glToDeposit.repository.database_results.GlToDepositResponse;
import com.integrator.equiWeb.services.transactions.glToGL.impl.GLtoGLXferServices;
import com.integrator.equiWeb.services.transactions.glToGL.repository.database_results.GLToGLTransferRequest;
import com.integrator.equiWeb.services.transactions.glToGL.repository.database_results.GlToGlResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationImplService implements ApplicationServices {
    private final BalanceEnquiryServices balanceEnquiry;
    private final GLtoGLXferServices gLtoGLXferServices;
    private final GLToDepositXferService glToDepositXferService;
//    private final AtmReversalService reversalService;

   private final CustomerToCustomerTransferService customerToCustomerTransfer;
    private final DepositToGLService depositToGLService;

   // private final ReversalRepository reversalRepository;
    private final ActivityOps activityOps;
    static String terminalExist;
    public ResponseEntity<ServiceResponse> allTransactions(ServiceRequest serviceRequest){
        int processingCode;
        String initialProcessingCode = serviceRequest.getProcessingCode();

        String processingCodeStr = initialProcessingCode.substring(0, 2);

        processingCode = Integer.parseInt(processingCodeStr);
        ServiceResponse serviceResponse;
//        AtmReversalRequest originalDataRef = reversalRepository.getTransactionData(serviceRequest.getOrigReferenceNo());
//        if(originalDataRef != null && !originalDataRef.equals("")){
//            TransactionReversalResponse reversalResponse = reversalService.txnReveral(txnReversalRequestMap(serviceRequest));
//            return ResponseEntity.ok(mapToReversalResponse(reversalResponse, serviceRequest));
//        }
//        if(reversalRepository.glTransaction() != null && reversalRepository.queryTransaction() != null){
//            terminalExist = "Y";
//        }else {
//            terminalExist = "N";
//        }
        log.info(terminalExist);
//        if (processingCode >= 0 && processingCode <= 19) {
//            if(!(serviceRequest.getOrigReferenceNo().equals(null) && serviceRequest.getOrigReferenceNo().equals(""))){
//                System.out.println("This is a debit reversal !!!");
//                TransactionReversalResponse reversalResponse = reversalService.txnReveral(txnReversalRequestMap(serviceRequest));
//                return ResponseEntity.ok(mapToReversalResponse(reversalResponse, serviceRequest));
//            }else {
//                PostDepositToGLAccountTransferResponse postDepositToGLAccountTransferResponse = depositToGLService.depositToGL(processDebits(serviceRequest, terminalExist));
//                return ResponseEntity.ok(mapToDepositResponse(postDepositToGLAccountTransferResponse, serviceRequest));
//            }
//        }
        if (processingCode >= 0 && processingCode <= 19) {
            DepositToGLAccountTransferResponse postDepositToGLAccountTransferResponse = depositToGLService.depositToGL(processDebits(serviceRequest, terminalExist));
            serviceResponse = mapToDepositResponse(postDepositToGLAccountTransferResponse, serviceRequest);
            activityOps.saveRequest(serviceResponse);
           // System.out.println(serviceResponse);
            return ResponseEntity.ok(serviceResponse);

        }
        if (processingCode >= 20 && processingCode <= 29) {
            GlToDepositResponse glToDepositResponse = glToDepositXferService.glToDeposit(processCredit(serviceRequest, terminalExist));
            serviceResponse = mapToGLToDepositResponse(glToDepositResponse, serviceRequest);
            activityOps.saveRequest(serviceResponse);
            return ResponseEntity.ok(serviceResponse);
        }

        if (processingCode >= 30 && processingCode <= 39){
            BalanceEnquiryResponse balanceenquiryResponse = balanceEnquiry.balanceEnquiries(enquiryServiceRequest(serviceRequest));
          //  BalanceenquiryResponse balanceenquiryResponse = balanceEnquiry.balanceEnquiry(enquiryServiceRequest(String.valueOf(serviceRequest)));
           // System.out.println(balanceenquiryResponse);
            return ResponseEntity.ok(mapToBalanceEnquiryResponse(balanceenquiryResponse, serviceRequest));

        }
        if (processingCode >= 40 && processingCode <= 49){

            CustomerToCustomerResponse customer = customerToCustomerTransfer.customerToCustomerTransfer(customerServiceRequest(serviceRequest));
            //System.out.println(customer);
            serviceResponse = mapCustomerToCustomerResponse(customer, serviceRequest);
            activityOps.saveRequest(serviceResponse);
            return ResponseEntity.ok(serviceResponse);

        }
        if (processingCode >= 50 && processingCode <= 59){
            CustomerToCustomerResponse customer = customerToCustomerTransfer.customerToCustomerTransfer(customerServiceRequest(serviceRequest));
            serviceResponse = mapCustomerToCustomerResponse(customer, serviceRequest);
            activityOps.saveRequest(serviceResponse);
            return ResponseEntity.ok(serviceResponse);
        }

        if (processingCode >= 90 && processingCode <= 99){
           // if (processingCode == 92){
            GlToGlResponse gLtoGLXferResponse = gLtoGLXferServices.glToGlXfer(processAdminService(serviceRequest));
            serviceResponse = mapGLToGLResponse(gLtoGLXferResponse, serviceRequest);
            activityOps.saveRequest(serviceResponse);
            return ResponseEntity.ok(serviceResponse);
        }
        else if (processingCode >= 0 && processingCode <= 99){
            throw new RuntimeException("Invalid processing code");

          //  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        else {
            log.error("Processing code out of range");
            throw new RuntimeException("Processing code out of range");
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }


    }

//    private ServiceResponse mapToReversalResponse(TransactionReversalResponse reversalResponse, ServiceRequest serviceRequest) {
//        ServiceResponse serviceResponse = new ServiceResponse();
//        serviceResponse.setFromAccount(reversalResponse.getReturn().getPrimaryAccountNumber());
//        serviceResponse.setResponseCode(reversalResponse.getReturn().getResponseCode());
//        serviceResponse.setReferenceNo(reversalResponse.getReturn().getTxnReference());
//        serviceResponse.setOrigReferenceNo(serviceRequest.getOrigReferenceNo());
//        serviceResponse.setTranAmount1(reversalResponse.getReturn().getTransactionAmount());
//        serviceResponse.setProcessingCode(serviceRequest.getProcessingCode());
//        serviceResponse.setServiceId(serviceRequest.getServiceId());
//        serviceResponse.setIsoCurrency(serviceRequest.getIsoCurrency());
//        serviceResponse.setATMSwitchID(serviceRequest.getATMSwitchID());
//        serviceResponse.setTransmissionTime(System.currentTimeMillis());
//        serviceResponse.setCardNo(serviceRequest.getCardNo());
//        serviceResponse.setTranDescription(serviceRequest.getTranDescription());
//        serviceResponse.setEffectiveDt(serviceRequest.getEffectiveDt());
//        serviceResponse.setTerminalId(serviceRequest.getTerminalId());
//
//        serviceResponse.setLocationDescription(serviceRequest.getLocationDescription());
//        serviceResponse.setReversal(serviceRequest.getReversal());
//
//        return serviceResponse;
//    }
//
    private ServiceResponse mapToGLToDepositResponse(GlToDepositResponse glToDepositResponse, ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setFromAccount(glToDepositResponse.getPrimaryAccountNumber());
        serviceResponse.setToAccount(serviceRequest.getToAccount());
        serviceResponse.setResponseCode(glToDepositResponse.getResponseCode());
        serviceResponse.setReferenceNo(glToDepositResponse.getTxnReference());
        serviceResponse.setTranAmount1(serviceRequest.getTranAmount1());
        serviceResponse.setProcessingCode(serviceRequest.getProcessingCode());
        serviceResponse.setServiceId(serviceRequest.getServiceId());
        serviceResponse.setIsoCurrency(serviceRequest.getIsoCurrency());
        serviceResponse.setATMSwitchID(serviceRequest.getATMSwitchID());
        serviceResponse.setTransmissionTime(System.currentTimeMillis());
        serviceResponse.setCardNo(serviceRequest.getCardNo());
        serviceResponse.setTranDescription(serviceRequest.getTranDescription());
        serviceResponse.setEffectiveDt(serviceRequest.getEffectiveDt());
        serviceResponse.setTerminalId(serviceRequest.getTerminalId());
        serviceResponse.setLocationDescription(serviceRequest.getLocationDescription());

        return serviceResponse;
    }
    private ServiceResponse mapToDepositResponse(DepositToGLAccountTransferResponse postDepositToGLAccountTransferResponse, ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setFromAccount(serviceRequest.getFromAccount());
        serviceResponse.setToAccount(serviceRequest.getToAccount());
        serviceResponse.setResponseCode(postDepositToGLAccountTransferResponse.getResponseCode());
        serviceResponse.setReferenceNo(postDepositToGLAccountTransferResponse.getTxnReference());
        serviceResponse.setTranAmount1(serviceRequest.getTranAmount1());
//        serviceResponse.setTranAmount1(postDepositToGLAccountTransferResponse.getTransactionAmount());
        serviceResponse.setProcessingCode(serviceRequest.getProcessingCode());
        serviceResponse.setServiceId(serviceRequest.getServiceId());
        serviceResponse.setIsoCurrency(serviceRequest.getIsoCurrency());
        serviceResponse.setATMSwitchID(serviceRequest.getATMSwitchID());
        serviceResponse.setTransmissionTime(System.currentTimeMillis());
        serviceResponse.setCardNo(serviceRequest.getCardNo());
        serviceResponse.setTranDescription(serviceRequest.getTranDescription());
        serviceResponse.setEffectiveDt(serviceRequest.getEffectiveDt());
        serviceResponse.setTerminalId(serviceRequest.getTerminalId());
        serviceResponse.setLocationDescription(serviceRequest.getLocationDescription());

        return serviceResponse;
    }
//
    private ServiceResponse mapGLToGLResponse(GlToGlResponse gLtoGLXferResponse, ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setFromAccount(serviceRequest.getFromAccount());
        serviceResponse.setTransmissionTime(gLtoGLXferResponse.getTransmissionTime());
        serviceResponse.setToAccount(serviceRequest.getToAccount());
        serviceResponse.setReferenceNo(gLtoGLXferResponse.getTxnReference());
        serviceResponse.setTranAmount1(serviceRequest.getTranAmount1());
        serviceResponse.setProcessingCode(serviceRequest.getProcessingCode());
        serviceResponse.setServiceId(serviceRequest.getServiceId());
        serviceResponse.setIsoCurrency(serviceRequest.getIsoCurrency());
        serviceResponse.setCardNo(serviceRequest.getCardNo());
        serviceResponse.setTranDescription(serviceRequest.getTranDescription());
        serviceResponse.setEffectiveDt(serviceRequest.getEffectiveDt());
        serviceResponse.setTerminalId(serviceRequest.getTerminalId());
        serviceResponse.setLocationDescription(serviceRequest.getLocationDescription());
        return serviceResponse;

    }

    private ServiceResponse mapCustomerToCustomerResponse(CustomerToCustomerResponse customer, ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setFromAccount(serviceRequest.getFromAccount());
        serviceResponse.setToAccount(serviceRequest.getToAccount());
        serviceResponse.setResponseCode(customer.getResponseCode());
        serviceResponse.setReferenceNo(serviceRequest.getReferenceNo());
        serviceResponse.setTranAmount1(serviceRequest.getTranAmount1());
        serviceResponse.setProcessingCode(serviceRequest.getProcessingCode());
        serviceResponse.setServiceId(serviceRequest.getServiceId());
        serviceResponse.setIsoCurrency(serviceRequest.getIsoCurrency());
        serviceResponse.setATMSwitchID(serviceRequest.getATMSwitchID());
        serviceResponse.setTransmissionTime(System.currentTimeMillis());
        serviceResponse.setCardNo(serviceRequest.getCardNo());
        serviceResponse.setTranDescription(serviceRequest.getTranDescription());
        serviceResponse.setEffectiveDt(serviceRequest.getEffectiveDt());
        serviceResponse.setTerminalId(serviceRequest.getTerminalId());
        serviceResponse.setLocationDescription(serviceRequest.getLocationDescription());
        return serviceResponse;
    }

    private ServiceResponse mapToBalanceEnquiryResponse(BalanceEnquiryResponse balanceenquiryResponse, ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setServiceId(serviceRequest.getServiceId());
//        serviceResponse.setFromAccount(balanceenquiryResponse.getTargetAccountNumber());
        serviceResponse.setTranAmount1(balanceenquiryResponse.getAvailBal());
        serviceResponse.setTransmissionTime(System.currentTimeMillis());
        serviceResponse.setProcessingCode(serviceRequest.getProcessingCode());
        serviceResponse.setServiceId(serviceRequest.getServiceId());
        serviceResponse.setIsoCurrency(serviceRequest.getIsoCurrency());
        serviceResponse.setATMSwitchID(serviceRequest.getATMSwitchID());
        serviceResponse.setCardNo(serviceRequest.getCardNo());
        serviceResponse.setTranDescription(serviceRequest.getTranDescription());
        serviceResponse.setEffectiveDt(serviceRequest.getEffectiveDt());
        serviceResponse.setTerminalId(serviceRequest.getTerminalId());
        serviceResponse.setLocationDescription(serviceRequest.getLocationDescription());
        return serviceResponse;
    }


    private BalanceEnquiryRequest enquiryServiceRequest(ServiceRequest serviceRequest) {
        BalanceEnquiryRequest balEnqRequest = new BalanceEnquiryRequest();
        //balEnqRequest.setAvailBal(serviceRequest.getTranAmount1());
        balEnqRequest.setAcctNo(serviceRequest.getFromAccount());
        return balEnqRequest;
    }

    private GLToGLTransferRequest processAdminService(ServiceRequest serviceRequest) {
        GLToGLTransferRequest glToGLTransferRequest = new GLToGLTransferRequest();
        glToGLTransferRequest.setFromAccountNumber(serviceRequest.getFromAccount());
        glToGLTransferRequest.setToAccountNumber(serviceRequest.getToAccount());
        glToGLTransferRequest.setTransactionAmount(serviceRequest.getTranAmount1());
        glToGLTransferRequest.setTransactionReference(serviceRequest.getReferenceNo());
        return glToGLTransferRequest;
    }

    private GLToDepositRequest processCredit(ServiceRequest serviceRequest, String terminalExist) {
        GLToDepositRequest glToDepositRequest = new GLToDepositRequest();
        glToDepositRequest.setFromAccountNumber(serviceRequest.getFromAccount());
        glToDepositRequest.setTransactionAmount(serviceRequest.getTranAmount1());
        glToDepositRequest.setToAccountNumber(serviceRequest.getToAccount());
        glToDepositRequest.setTransactionReference(serviceRequest.getReferenceNo());
        //System.out.println(glToDepositRequest);
        return glToDepositRequest;
    }


    private DepositToGLRequest processDebits(ServiceRequest serviceRequest, String terminalExist) {
        DepositToGLRequest depositToGLRequest = new DepositToGLRequest();
        depositToGLRequest.setFromAccountNumber(serviceRequest.getFromAccount());
        depositToGLRequest.setToAccountNumber(serviceRequest.getToAccount());
        depositToGLRequest.setTransactionAmount(serviceRequest.getTranAmount1());
        depositToGLRequest.setTransactionReference(serviceRequest.getReferenceNo());

        //System.out.println(depositToGLRequest.toString());
        return depositToGLRequest;
    }




    private CustomerToCustomerTransRequest customerServiceRequest(ServiceRequest serviceRequest) {
        CustomerToCustomerTransRequest customerRequest = new CustomerToCustomerTransRequest();
        customerRequest.setChannelId(7L);
        customerRequest.setFromAccountNumber(serviceRequest.getFromAccount());
        customerRequest.setToAccountNumber(serviceRequest.getToAccount());
        customerRequest.setTransactionAmount(serviceRequest.getTranAmount1());
       // System.out.println(customerRequest.toString());
        return customerRequest;
    }

//
//    private AtmReversalRequest txnReversalRequestMap(ServiceRequest serviceRequest) {
//        AtmReversalRequest reversalRequest = new AtmReversalRequest();
//        reversalRequest.setXapiServiceCode("TXN094");
//        reversalRequest.setAmount(serviceRequest.getTranAmount1());
//        reversalRequest.setChannelCode(rubikonCredentials.getChannelCode());
//        reversalRequest.setChannelId(Long.valueOf(rubikonCredentials.getChannelId()));
//        reversalRequest.setTransmissionTime(System.currentTimeMillis());
//        reversalRequest.setOrigReferenceNo(serviceRequest.getOrigReferenceNo());
//        reversalRequest.setTxnReference(serviceRequest.getReferenceNo());
//        reversalRequest.setAmount(serviceRequest.getTranAmount1());
//        reversalRequest.setAccountNo(serviceRequest.getFromAccount());
//        reversalRequest.setContraAcctNo(serviceRequest.getToAccount());
//        reversalRequest.setTxnAmount(serviceRequest.getTranAmount2());
//        reversalRequest.setTxnRevFlag(true);
//        reversalRequest.setReversalIndicator("Y");
//        reversalRequest.setTxnCurrencyCode(rubikonCredentials.getTransactionCurrencyCode());
//
//       return reversalRequest;
//    }


}
