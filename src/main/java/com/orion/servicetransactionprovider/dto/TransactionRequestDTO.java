package com.orion.servicetransactionprovider.dto;

public class TransactionRequestDTO {

    private String accountId;
    private String amount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
