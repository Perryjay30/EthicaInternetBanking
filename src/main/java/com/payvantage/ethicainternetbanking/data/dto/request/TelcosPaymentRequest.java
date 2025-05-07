package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;

@Data
public class TelcosPaymentRequest {
    private String customerReference;
    private String billerId;
    private String amount;
    private String packageId;
    private String pin;
}
