package com.payvantage.ethicainternetbanking.data.dto.response;

public class CreateAccountResponse {
    private int statusCode;
    private String description;
    private AccountData data;

    // Getters and Setters
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

    public AccountData getData() {
        return data;
    }

    public void setData(AccountData data) {
        this.data = data;
    }

    // Nested AccountData class
    public static class AccountData {
        private int id;
        private String accountNumber;

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }
    }
}

