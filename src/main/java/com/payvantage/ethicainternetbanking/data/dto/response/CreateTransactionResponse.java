package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class CreateTransactionResponse {
    private int statusCode;
    private String description;
    private TransactionData data;

    public static class TransactionData {
        private String correlationId;


        public String getCorrelationId() {
            return correlationId;
        }

        public void setCorrelationId(String correlationId) {
            this.correlationId = correlationId;
        }
    }

}

