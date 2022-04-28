package com.orion.servicetransactionprovider.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TransactionGeneratorServiceImplTest {

    @InjectMocks
    private TransactionGeneratorServiceImpl transactionGeneratorService;

    @Test
    public void generateTransactionTest(){

       Optional<String> optional= transactionGeneratorService.generateTransaction("123465","78");

        Assertions.assertNotNull(optional.get());
    }
}
