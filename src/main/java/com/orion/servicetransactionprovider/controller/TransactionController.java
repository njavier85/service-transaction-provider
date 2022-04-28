package com.orion.servicetransactionprovider.controller;

import com.orion.servicetransactionprovider.dto.ErrorResponseDTO;
import com.orion.servicetransactionprovider.dto.TransactionRequestDTO;
import com.orion.servicetransactionprovider.dto.TransactionResponseDTO;
import com.orion.servicetransactionprovider.properties.MessagesReturnProperties;
import com.orion.servicetransactionprovider.service.TransactionGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionGeneratorService transactionGeneratorService;
    private MessagesReturnProperties messagesReturnProperties;

    public TransactionController(TransactionGeneratorService transactionGeneratorService, MessagesReturnProperties messagesReturnProperties){
        this.transactionGeneratorService = transactionGeneratorService;
        this.messagesReturnProperties = messagesReturnProperties;
    }

    @PostMapping("/create-transaction")
    public ResponseEntity createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {

        log.info("createTransaction:" + transactionRequestDTO.getAccountId());

        if (isAnInValidRequest(transactionRequestDTO)) {
            log.error("m=createTransaction, Invalid TransactionRequest: {}", transactionRequestDTO);
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
            errorResponseDTO.setDescription(messagesReturnProperties.getMessageparamtererror());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setAccountId(transactionRequestDTO.getAccountId());
        transactionResponseDTO.setAmount(transactionRequestDTO.getAmount());

        Optional<String> optional = transactionGeneratorService.generateTransaction(transactionRequestDTO.getAccountId(),transactionRequestDTO.getAmount());
        if(optional.isEmpty()){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
            errorResponseDTO.setDescription(messagesReturnProperties.getError());
            return ResponseEntity.internalServerError().body(errorResponseDTO);
        }else{
            transactionResponseDTO.setTransactionId(optional.get());
            transactionResponseDTO.setDescription(messagesReturnProperties.getMessageok());
            transactionResponseDTO.setStatus(messagesReturnProperties.getStatusok());
            return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponseDTO);
        }
    }

    private boolean isAnInValidRequest(TransactionRequestDTO transactionRequestDTO) {
        if(StringUtils.isEmpty(transactionRequestDTO.getAccountId()) ||
                StringUtils.isEmpty(transactionRequestDTO.getAmount())){
            return true;
        }
        return false;
    }
}
