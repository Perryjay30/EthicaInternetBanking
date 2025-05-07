package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TransactionHistoryResponse {
    private int statusCode;
    private String description;
    private List<TransactionHistoryData> data;


    @Data
    public static class TransactionHistoryData {
        private String accountNumber;
        private double amount;
        private String narration;
        private String transType;
        private String entryDate;
        private String valueDate;
        private double balance;
        private String transRef;
        private String transactionCategory;
    }
}
