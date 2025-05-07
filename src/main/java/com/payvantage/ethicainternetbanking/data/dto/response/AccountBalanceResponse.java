package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class AccountBalanceResponse {
    private int statusCode;
    private String description;
    private Data data;

    // Getter and setter methods
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private double balance;
        private double ledgerBalance;
        private String accountNumber;

        // Getter and setter methods
        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public double getLedgerBalance() {
            return ledgerBalance;
        }

        public void setLedgerBalance(double ledgerBalance) {
            this.ledgerBalance = ledgerBalance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }
    }
}
