package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;

@Data
public class CreateTransactionRequest {
    private String amount;
    private String creditAccountNumber;
    private String narration;
    private String pin;

    // Getters and Setters
}

