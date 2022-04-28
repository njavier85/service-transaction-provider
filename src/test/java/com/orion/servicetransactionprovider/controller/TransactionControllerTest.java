package com.orion.servicetransactionprovider.controller;

import com.orion.servicetransactionprovider.dto.ErrorResponseDTO;
import com.orion.servicetransactionprovider.dto.TransactionRequestDTO;
import com.orion.servicetransactionprovider.dto.TransactionResponseDTO;
import com.orion.servicetransactionprovider.properties.MessagesReturnProperties;
import com.orion.servicetransactionprovider.service.TransactionGeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionGeneratorService transactionGeneratorService;

    @Mock
    private MessagesReturnProperties messagesReturnProperties;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    public void createTransaction_OK_Test(){

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountId("123465");
        transactionRequestDTO.setAmount("100");

        Optional<String> optionalReturn=Optional.of("123-456-789");
        when(transactionGeneratorService.generateTransaction(anyString(),anyString())).thenReturn(optionalReturn);
        when(messagesReturnProperties.getMessageok()).thenReturn("Transaction Created Correctly");
        when(messagesReturnProperties.getStatusok()).thenReturn("CREATED");


        ResponseEntity<TransactionResponseDTO> responseEntity =transactionController.createTransaction(transactionRequestDTO);
        Assertions.assertNotNull(responseEntity.getBody().getTransactionId());
        Assertions.assertEquals("123-456-789",responseEntity.getBody().getTransactionId());
        Assertions.assertEquals("123465",responseEntity.getBody().getAccountId());
        Assertions.assertEquals("100",responseEntity.getBody().getAmount());
        Assertions.assertEquals("Transaction Created Correctly",responseEntity.getBody().getDescription());
        Assertions.assertEquals("CREATED",responseEntity.getBody().getStatus());

    }


    @Test
    public void createTransaction_FAILED_Test(){

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountId("123465");
        transactionRequestDTO.setAmount("100");

        Optional<String> optionalReturn=Optional.empty();
        when(transactionGeneratorService.generateTransaction(anyString(),anyString())).thenReturn(optionalReturn);
        when(messagesReturnProperties.getError()).thenReturn("The provider was not able to process transaction");


        ResponseEntity<ErrorResponseDTO> responseEntity =transactionController.createTransaction(transactionRequestDTO);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(500,responseEntity.getStatusCodeValue());
        Assertions.assertEquals("The provider was not able to process transaction",responseEntity.getBody().getDescription());

    }


    @Test
    public void createTransaction_INVALIDPARAMETERS_FAILED_Test(){

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();

        Optional<String> optionalReturn=Optional.empty();
        when(transactionGeneratorService.generateTransaction(anyString(),anyString())).thenReturn(optionalReturn);
        when(messagesReturnProperties.getMessageparamtererror()).thenReturn("Invalid Parameter Inserted");


        ResponseEntity<ErrorResponseDTO> responseEntity =transactionController.createTransaction(transactionRequestDTO);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(400,responseEntity.getStatusCodeValue());
        Assertions.assertEquals("Invalid Parameter Inserted",responseEntity.getBody().getDescription());

    }
}
