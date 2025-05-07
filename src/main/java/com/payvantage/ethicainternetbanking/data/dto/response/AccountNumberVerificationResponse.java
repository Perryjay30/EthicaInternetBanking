package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class AccountNumberVerificationResponse {
    private int statusCode;
    private String description;
    private Data data;

    // Getters and setters
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

    // Inner class for the 'data' field
    public static class Data {
        private String accountName;

        // Getter and Setter
        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }
    }
}