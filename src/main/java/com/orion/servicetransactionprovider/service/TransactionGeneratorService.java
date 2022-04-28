package com.orion.servicetransactionprovider.service;

import java.util.Optional;
import java.util.UUID;

public interface TransactionGeneratorService {

    public Optional<String> generateTransaction(String account, String amount);
}
