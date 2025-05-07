package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TransactionStatementResponse {
    private int statusCode;
    private String description;
    private List<TransactionStatement> data;
    private Object errors;

    @Data
    public static class TransactionStatement {
        private int id;
        private double amount;
        private String entryDate;
        private String valueDate;
        private String sourceAccount;
        private String destinationAccount;
        private boolean saveStatus;
        private boolean authorizedStatus;
        private String currency;
        private String createdBy;
        private String authorizedBy;
        private String senderName;
        private String recipientName;
        private String narration;
        private double previousBalance;
        private double currentBalance;
        private String channelCode;
        private String drCr;
        private String externalRef;
        private String transRef;
        private String correlationId;
        private boolean reversed;
        private String ipAddress;
        private String entryRef;
        private String accountNumber;
        private String transType;
    }
}

