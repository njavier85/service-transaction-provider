package com.orion.servicetransactionprovider.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TransactionGeneratorServiceImpl implements TransactionGeneratorService{

    public Optional<String> generateTransaction(String account, String amount) {
        log.info("generateTransaction: account: {}, amount: {}",account,amount);

        /*Internally Generates a Transaction Id*/
        UUID uuid = UUID.randomUUID();
        return Optional.of(uuid.toString());
    }
}
