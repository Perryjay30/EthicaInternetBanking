package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;

@Data
public class DiscoPaymentRequest {
    private String accountType;
    private String discoIdentifier;
    private String customerReference;
    private String amount;
    private String pin;
}
