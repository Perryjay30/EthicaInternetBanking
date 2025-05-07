package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;

@Data
public class CablePaymentRequest {
    private String customerId;
    private String billerId;
    private String amount;
    private String cablePlan;
    private String productCode;
    private String pin;
}
