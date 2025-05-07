package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class DiscoPaymentResponse {
    private int statusCode;
    private String description;
    private PaymentData data;
    private Object errors;

    @Data
    public static class PaymentData {
        private String respDescription;
        private String customerAdddress;
        private String tariff;
        private String units;
        private String respCode;
        private String customerName;
        private String receiptNumber;
        private String token;
    }
}
